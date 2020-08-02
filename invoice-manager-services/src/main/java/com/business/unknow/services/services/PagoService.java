/**
 * 
 */
package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.PagoFactura;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.PagoFacturaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.evaluations.PagoEvaluatorService;
import com.business.unknow.services.util.PagoBuilder;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class PagoService {

	@Autowired
	private PagoRepository repository;

	@Autowired
	private PagoFacturaRepository facturaPagosRepository;

	@Autowired
	private PagoMapper mapper;

	@Autowired
	private PagoEvaluatorService pagoEvaluatorService;

	@Autowired
	private FilesService filesService;

	@Autowired
	private FacturaService facturaService;



	private static final Logger log = LoggerFactory.getLogger(PagoService.class);

	public Page<PagoDto> getPaginatedPayments(Optional<String> solicitante, Optional<String> acredor,
			Optional<String> deudor, String formaPago, String status, String banco, Date since, Date to, int page,
			int size) {

		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Pago> result = null;
		if (solicitante.isPresent()) {
			result = repository.findBySolicitanteIgnoreCaseContaining(solicitante.get(),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else if (acredor.isPresent()) {
			result = repository.findPagosAcredorFilteredByParams(String.format("%%%s%%", acredor.get()),
					String.format("%%%s%%", status), String.format("%%%s%%", formaPago), String.format("%%%s%%", banco),
					start, end, PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else if (deudor.isPresent()) {
			result = repository.findPagosDeudorFilteredByParams(String.format("%%%s%%", deudor.get()),
					String.format("%%%s%%", status), String.format("%%%s%%", formaPago), String.format("%%%s%%", banco),
					start, end, PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else {
			result = repository.findPagosFilteredByParams(String.format("%%%s%%", status),
					String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		}

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public List<PagoDto> findPagosByFolio(String folio) {
		return mapper.getPagosDtoFromEntities(repository.findPagosByFolio(folio));
	}
	
	public List<PagoFacturaDto> findPagosFacturaByFolio(String folio) {
		return mapper.getPagosFacturaDtoFromEntities(facturaPagosRepository.findByFolio(folio));
	}

	public PagoDto getPaymentById(Integer id) throws InvoiceManagerException {
		Optional<Pago> payment = repository.findById(id);
		if (payment.isPresent()) {
			return mapper.getPagoDtoFromEntity(payment.get());
		} else {
			throw new InvoiceManagerException("Pago no encontrado",
					String.format("El pago con id %d no fu encontrado.", id), HttpStatus.NOT_FOUND.value());
		}
	}

	public PagoDto insertNewPaymentWithoutValidation(PagoDto payment) {
		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(payment)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto insertNewPayment(PagoDto pagoDto) throws InvoiceManagerException {
		pagoEvaluatorService.validatePayment(pagoDto);
		List<FacturaDto> facturas = new ArrayList<>();
		for (PagoFacturaDto pagoFact : pagoDto.getFacturas()) {
			FacturaDto factura = facturaService.getBaseFacturaByFolio(pagoFact.getFolio());
			facturas.add(factura);
			// Populate missing information
			pagoFact.setAcredor(factura.getRazonSocialEmisor());
			pagoFact.setDeudor(factura.getRazonSocialRemitente());
			pagoFact.setTotalFactura(factura.getTotal());
			pagoFact.setMetodoPago(factura.getMetodoPago());
			if (MetodosPagoEnum.PUE.name().equals(factura.getMetodoPago())) {
				pagoFact.setIdCfdi(factura.getIdCfdi());
			}
		}
		pagoEvaluatorService.validatePaymentCreation(pagoDto, facturas);

		List<FacturaDto> factPpd = facturas.stream().filter(f -> MetodosPagoEnum.PPD.name().equals(f.getMetodoPago()))
				.collect(Collectors.toList());

		List<FacturaDto> factPue = facturas.stream().filter(f -> MetodosPagoEnum.PUE.name().equals(f.getMetodoPago()))
				.collect(Collectors.toList());

		if (!factPpd.isEmpty()) {
			log.info("Generando complemento para : {}",
					factPpd.stream().map(f -> f.getFolio()).collect(Collectors.toList()));
			FacturaDto fact = facturaService.generateComplemento(facturas, pagoDto);
			factPpd.forEach(f -> {
				Optional<PagoFacturaDto> pagoFact = pagoDto.getFacturas().stream()
						.filter(p -> p.getFolio().equals(f.getFolio())).findAny();
				if (pagoFact.isPresent()) {
					pagoFact.get().setIdCfdi(fact.getIdCfdi());
				}
			});
		}
		if (!factPue.isEmpty()) {
			factPue.forEach(f -> {
				Optional<PagoFacturaDto> pagoFact = pagoDto.getFacturas().stream()
						.filter(p -> p.getFolio().equals(f.getFolio())).findAny();
				if (pagoFact.isPresent()) {
					pagoFact.get().setIdCfdi(f.getIdCfdi());
				}
			});
		}
		for (FacturaDto dto : facturas) {
			if (!FormaPagoEnum.CREDITO.getPagoValue().equals(pagoDto.getFormaPago())
					&& dto.getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())
					&& dto.getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
				log.info("Updating saldo pendiente factura {}", dto.getFolio());
				Optional<PagoFacturaDto> pagoFact = pagoDto.getFacturas().stream()
						.filter(p -> p.getFolio().equals(dto.getFolio())).findAny();
				if (pagoFact.isPresent()) {
					facturaService.updateTotalAndSaldoFactura(dto.getIdCfdi(), Optional.empty(), Optional.of(pagoFact.get().getMonto()));
				}
			}
		}
		if (FormaPagoEnum.CREDITO.getPagoValue().equals(pagoDto.getFormaPago())
				&&facturas.size()==1){
			Optional<FacturaDto> currentFactura= facturas.stream().findFirst();
			if(currentFactura.isPresent()&&currentFactura.get().getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
				currentFactura.get().setValidacionTeso(true);
				facturaService.updateFactura(currentFactura.get().getIdCfdi(), currentFactura.get());
				pagoDto.setStatusPago("ACEPTADO");
				pagoDto.setRevision1(true);
				pagoDto.setRevision2(true);
			}
		}
		Pago payment = repository.save(mapper.getEntityFromPagoDto(pagoDto));
		for (PagoFacturaDto fact : pagoDto.getFacturas()) {
			PagoFactura pagoFact = mapper.getEntityFromPagoFacturaDto(fact);
			pagoFact.setPago(payment);
			payment.addFactura(facturaPagosRepository.save(pagoFact));
		}
		return mapper.getPagoDtoFromEntity(payment);
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(Integer idPago, PagoDto pago) throws InvoiceManagerException {
		Pago entity = repository.findById(idPago).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", idPago)));

		PagoBuilder pagoBuilder = new PagoBuilder(mapper.getPagoDtoFromEntity(entity)) // payment only update revision
				.setRevision1(pago.getRevision1()).setRevision2(pago.getRevision2()).setRevisor1(pago.getRevisor1())
				.setRevisor2(pago.getRevisor2());
		pagoEvaluatorService.validatePayment(pago);

		List<FacturaDto> facturas = new ArrayList<>();
		for (PagoFacturaDto pagoFact : pago.getFacturas()) {
			FacturaDto factura = facturaService.getBaseFacturaByFolio(pagoFact.getFolio());
			facturas.add(factura);
		}
		pagoEvaluatorService.validatePaymentUpdate(pago, mapper.getPagoDtoFromEntity(entity), facturas);

		if (pago.getStatusPago().equals(RevisionPagosEnum.RECHAZADO.name())) {
			pagoBuilder.setStatusPago(RevisionPagosEnum.RECHAZADO.name());
			for (FacturaDto factura : facturas) {
				factura.setStatusFactura(FacturaStatusEnum.RECHAZO_TESORERIA.getValor());
				factura.setStatusDetail(pago.getComentarioPago());
				facturaService.updateFactura(factura.getIdCfdi(), factura);
			}
		} else if (entity.getRevision1() && pago.getRevision2()) {
			pagoBuilder.setStatusPago(RevisionPagosEnum.ACEPTADO.name());
			
			List<Integer> idFacts = pago.getFacturas().stream().map(f-> f.getIdCfdi()).distinct().collect(Collectors.toList());
			
			for (Integer idCfdi : idFacts) {
					FacturaDto fact = facturaService.getFacturaBaseByPrefolio(idCfdi);
					fact.setValidacionTeso(true);
					facturaService.updateFactura(idCfdi, fact);
			}
			
		}
		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(pagoBuilder.build())));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void deletePago(Integer idPago) throws InvoiceManagerException {
		PagoDto payment = mapper.getPagoDtoFromEntity(
				repository.findById(idPago).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El pago con id %d no existe", idPago))));
		List<FacturaDto> facturas = new ArrayList<>();
		for (PagoFacturaDto pagoFact : payment.getFacturas()) {
			FacturaDto factura = facturaService.getBaseFacturaByFolio(pagoFact.getFolio());
			facturas.add(factura);
		}
		pagoEvaluatorService.deletepaymentValidation(payment, facturas);
		
		
		for (FacturaDto facturaDto : facturas) {
			Optional<PagoFacturaDto> pagoFactOpt = payment.getFacturas().stream().filter(p->p.getFolio().equals(facturaDto.getFolio())).findAny();
			if(pagoFactOpt.isPresent()) {
				facturaService.updateTotalAndSaldoFactura(facturaDto.getIdCfdi(), Optional.empty(), Optional.of(pagoFactOpt.get().getMonto().negate()));
			}
		}
		for (Integer idCfdi : payment.getFacturas().stream().map(f -> f.getIdCfdi()).distinct().collect(Collectors.toList())) {
			FacturaDto fact = facturaService.getFacturaBaseByPrefolio(idCfdi);
			if (TipoDocumentoEnum.COMPLEMENTO.equals(TipoDocumentoEnum.findByDesc(fact.getTipoDocumento()))) {
				facturaService.deleteFactura(fact.getFolio());
				filesService.deleteFacturaFileByFolioAndType(fact.getFolio(), "PDF");
			}
		}
		for(PagoFactura pagoFactura:facturaPagosRepository.findByPagoId(payment.getId())) {
			facturaPagosRepository.delete(pagoFactura);
		}
		filesService.deleteResourceFileByResourceReferenceAndType("PAGO", idPago.toString(), "IMAGEN");
		repository.delete(mapper.getEntityFromPagoDto(payment));
	}

}
