/**
 * 
 */
package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.PagoFacturaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.evaluations.PagoEvaluatorService;
import com.business.unknow.services.services.executor.PagoExecutorService;
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
	private PagoExecutorService pagoExecutorService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private CfdiService cfdiService;

	private static final Logger log = LoggerFactory.getLogger(PagoService.class);

	public Page<PagoDto> getPaginatedPayments(Optional<String> folio, Optional<String> acredor, Optional<String> deudor,
			String formaPago, String status, String banco, Date since, Date to, int page, int size) {

		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Pago> result = null;
		// if (folio.isPresent()) {
		// TODO verify if is posible make finds by folio fact
//			result = repository.findByFolioIgnoreCaseContaining(folio.get(),
//					PageRequest.of(0, 10, Sort.by("fechaActualizacion").descending()));
//		} else if (acredor.isPresent()) {
//			result = repository.findPagosAcredorFilteredByParams(String.format("%%%s%%", acredor.get()), String.format("%%%s%%", status),
//					String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
//					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
//		} else if (deudor.isPresent()) {
//			result = repository.findPagosDeudorFilteredByParams(String.format("%%%s%%", deudor.get()), String.format("%%%s%%", status),
//					String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
//					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
//		} else {
		result = repository.findPagosFilteredByParams(String.format("%%%s%%", status),
				String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
				PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public List<PagoFacturaDto> findPagosByFolio(String folio) {
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
		
		// Validate fields required
		pagoEvaluatorService.validatePayment(pagoDto);
		//  TODO Apply validation rules
		// - payments sum should be equal to payment ammount
		// - 0 or negative payments are invalid
		for (PagoFacturaDto pagoFact : pagoDto.getFacturas()) {
			// find factura by folio factura
			FacturaDto factura = facturaService.getBaseFacturaByFolio(pagoFact.getFolio());
			//Populate missing information
			pagoFact.setAcredor(factura.getRazonSocialEmisor());
			pagoFact.setDeudor(factura.getRazonSocialRemitente());
			pagoFact.setTotalFactura(factura.getTotal());
			pagoFact.setIdCfdi(factura.getIdCfdi());
			if (MetodosPagoEnum.PPD.name().equals(factura.getMetodoPago())) {
				// PPD crean en automatico complemento
				log.info("Generando complemento para CFDI : {}", factura.getIdCfdi());
				facturaService.generateComplemento(factura, pagoDto);
			}
			if (!FormaPagoEnum.CREDITO.getPagoValue().equals(pagoDto.getFormaPago())) {
				log.info("Updating saldo pendiente factura");
				factura.setSaldoPendiente(factura.getSaldoPendiente().subtract(pagoFact.getMonto()));
				facturaService.updateFactura(factura, factura.getFolio());
			}
		}
		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(pagoDto)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(String folio, Integer id, PagoDto pago) throws InvoiceManagerException {
		Pago entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		FacturaDto factura = facturaService.getFacturaByFolio(folio);
		PagoBuilder pagoBuilder = new PagoBuilder(mapper.getPagoDtoFromEntity(entity)) // payment only update revision
				.setRevision1(pago.getRevision1()).setRevision2(pago.getRevision2()).setRevisor1(pago.getRevisor1())
				.setRevisor2(pago.getRevisor2());
		pagoEvaluatorService.validatePayment(pago);

		pagoEvaluatorService.validatePaymentUpdate(pago, mapper.getPagoDtoFromEntity(entity), factura);

		if (entity.getRevision1() && entity.getRevision2()) {
			throw new InvoiceManagerException("Incongruencia en la validacion de pagos ya se valido dos veces ",
					"Incongruencia de pago.", HttpStatus.CONFLICT.value());
		} else if (pago.getStatusPago().equals(RevisionPagosEnum.RECHAZADO.name())) {
			if (entity.getRevision1() && entity.getRevision2()) {
				throw new InvoiceManagerException("No puede ser rechazado un pago que ya fue aprobado",
						"El pago ya fue aprobado por dos personas.", HttpStatus.CONFLICT.value());
			} else {
				factura.setStatusFactura(FacturaStatusEnum.RECHAZO_TESORERIA.getValor());
				factura.setStatusDetail(pago.getComentarioPago());
				facturaService.updateFactura(factura, folio);
				pagoBuilder.setStatusPago(RevisionPagosEnum.RECHAZADO.name());
			}
		} else if (entity.getRevision1() && pago.getRevision2()) {
			entity.setStatusPago(RevisionPagosEnum.ACEPTADO.name());
			factura.setStatusPago(PagoStatusEnum.PAGADA.getValor());
			if (!factura.getStatusFactura().equals(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor())) {
				factura.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
			}
			facturaService.updateFactura(factura, folio);
			pagoBuilder.setStatusPago(RevisionPagosEnum.ACEPTADO.name());
		}

		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(pagoBuilder.build())));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updateMontoPago(Integer id, PagoDto pago) {
		Pago entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		entity.setMonto(pago.getMonto());
		return mapper.getPagoDtoFromEntity(repository.save(entity));
	}

//	public void deletePago(String folio, Integer id) throws InvoiceManagerException {
//		PagoDto payment =  mapper.getPagoDtoFromEntity(repository.findById(id).orElseThrow(() -> new InvoiceManagerException("Metodo de pago no soportado",
//				String.format("El pago con el id no existe %d", id), HttpStatus.BAD_REQUEST.value())));
//		FacturaDto factura = facturaService.getBaseFacturaByFolio(payment.getFolio());
//		List<PagoDto> payments = findPagosByFolioPadre(folio);
//		pagoEvaluatorService.deletepaymentValidation(payment, factura);
//		pagoExecutorService.deletePagoExecutor(payment, payments, factura);
//	}
//
//	public void actualizarCreditoContabilidad(String folio, PagoDto pagoDto) {
//		List<Pago> pagos = repository.findByFolio(folio);
//		Optional<Pago> pago = pagos.stream().filter(a -> a.getFormaPago().equals(FormaPagoEnum.CREDITO.name()))
//				.findFirst();
//		if (pago.isPresent()) {
//			Pago entity = pago.get();
//			entity.setMonto(entity.getMonto().subtract(pagoDto.getMonto()));
//			repository.save(entity);
//		}
//	}
}
