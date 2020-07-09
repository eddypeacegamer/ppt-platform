/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.commons.validator.DevolucionValidator;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.pagos.PagoDevolucionDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.services.DevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.entities.PagoDevolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.mapper.PagoDevolucionMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.PagoDevolucionRepository;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.services.builder.DevolucionesBuilderService;
import com.business.unknow.services.services.executor.DevolucionExecutorService;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class DevolucionService {

	@Autowired
	private DevolucionRepository repository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PagoDevolucionRepository pagoDevolucionRepository;

	@Autowired
	private PagoService pagoService;

	@Autowired
	private DevolucionMapper mapper;

	@Autowired
	private PagoDevolucionMapper pagoDevolucionMapper;

	@Autowired
	private DevolucionesBuilderService devolucionesBuilderService;

	@Autowired
	private DevolucionExecutorService devolucionExecutorService;

	private DevolucionValidator devolucionValidator = new DevolucionValidator();

	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType, Optional<String> idReceptor,
			int page, int size) {
		Page<Devolucion> result = null;
		if (!receptorType.isPresent() && !idReceptor.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else {
			result = repository.findDevolucionesByParamsPage(receptorType.get(), idReceptor.get(), "D",
					PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public List<DevolucionDto> getDevolucionesPorReceptor(String tipoReceptor, String idReceptor) {
		return mapper
				.getDevolucionesDtoFromEntities(repository.findDevolucionesByParams(tipoReceptor, idReceptor, "D"));
	}

	public BigDecimal getMontoDevoluciones(String tipoReceptor, String idReceptor) {
		BigDecimal result = repository.findMontoByParams(tipoReceptor, idReceptor);

		if (result == null || result.compareTo(BigDecimal.ZERO) == 0) {
			return new BigDecimal(0.0);
		} else {
			return result.setScale(2, BigDecimal.ROUND_DOWN);
		}
	}

	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

	public List<PagoDevolucionDto> solicitudDevoluciones(List<PagoDevolucionDto> solicitudes)
			throws InvoiceManagerException {
		List<PagoDevolucionDto> pagos = new ArrayList<>();
		for (PagoDevolucionDto dto : solicitudes) {
			pagos.add(solicitudDevolucion(dto));
		}
		return pagos;
	}

	public List<DevolucionDto> getDevolucionesByFolio(String folio) {
		return mapper.getDevolucionesDtoFromEntities(repository.findByFolio(folio));
	}

	public List<DevolucionDto> upadteDevoluciones(String folio, List<DevolucionDto> devoluciones)
			throws InvoiceManagerException {
		// TODO refactor this code with new implementation
		return devoluciones;
//		PagoDto pago = pagoService.findPagosByFolio(folio).stream().filter(p->!FormaPagoEnum.CREDITO.getPagoValue().equals(p.getFormaPago()))
//			.findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No se encontraron pagos para el folio %s", folio)));
//		
//		BigDecimal newAmmount = devoluciones.stream().map(d->d.getMonto()).reduce(BigDecimal.ZERO,(d1,d2)->d1.add(d2));
//		if(newAmmount.compareTo(pago.getMonto())==0) {
//			for (DevolucionDto devolucion : devoluciones) {
//				for (Devolucion entity : repository.findByFolioAndTipoReceptor(folio, devolucion.getTipoReceptor())) {
//					entity.setMonto(devolucion.getMonto()); //update solicitud y generacion de devolucion
//					repository.save(entity);
//				}
//			}
//			return devoluciones;	
//		}else {
//			throw new InvoiceManagerException("El monto total de las devoluciones no iguala al pago dado", HttpStatus.CONFLICT.value());
//		}
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDevolucionDto solicitudDevolucion(PagoDevolucionDto dto) throws InvoiceManagerException {
		devolucionValidator.validatePostDevolucionPago(dto);
		Devolucion dev = repository.save(devolucionesBuilderService.buildPagoDevolucion(dto));
		dto.setIdDevolucion(dev.getId());
		return pagoDevolucionMapper.getPagoDevolucionDtoFromEntity(
				pagoDevolucionRepository.save(pagoDevolucionMapper.getEntityFromPagoDevolucionDto(dto)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDevolucionDto solicitudDevolucionUpdate(PagoDevolucionDto dto, Integer id)
			throws InvoiceManagerException {
		devolucionValidator.validatePostDevolucionPago(dto);
		Optional<PagoDevolucion> pagoDevolucion = pagoDevolucionRepository.findById(id);
		if (pagoDevolucion.isPresent()) {
			pagoDevolucion.get().setStatus(dto.getStatus());
			pagoDevolucion.get().setAutorizador(dto.getAutorizador());
			pagoDevolucion.get().setComentarios(dto.getComentarios());
			if ("RECHAZADO".equalsIgnoreCase(dto.getStatus())) {
				repository.deleteById(dto.getIdDevolucion()); // returns ammount to account
			} else {
				// TODO include here expenses in amounts table
				pagoDevolucion.get().setFechaPago(dto.getFechaPago());
				pagoDevolucion.get().setRfcEmpresa(dto.getRfcEmpresa());
				pagoDevolucion.get().setCuentaPago(dto.getCuentaPago());
			}
			return pagoDevolucionMapper
					.getPagoDevolucionDtoFromEntity(pagoDevolucionRepository.save(pagoDevolucion.get()));
		} else {
			throw new InvoiceManagerException("The Pago devolucion does not exist",
					String.format("The Pago devolucion does not exist %d", id), HttpStatus.BAD_REQUEST.value());
		}
	}

	public void generarDevolucionesPorPago(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		Client client = clientRepository.findByRfc(facturaDto.getRfcRemitente())
				.orElseThrow(() -> new InvoiceManagerException("The type of document not supported",
						String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
						HttpStatus.BAD_REQUEST.value()));
		FacturaContext context;
		BigDecimal baseComisiones;
		BigDecimal impuestos;
		BigDecimal realSubtotal;
		switch (TipoDocumentoEnum.findByDesc(facturaDto.getTipoDocumento())) {
		case FACTURA:
			impuestos = calculaImpuestos(facturaDto.getCfdi());
			realSubtotal = calculaImporteBaseFactura(facturaDto.getCfdi());
			context = devolucionesBuilderService.buildFacturaContextForPueDevolution(facturaDto, pagoDto);
			devolucionExecutorService.executeDevolucionForPue(context, client, facturaDto.getCfdi().getTotal(),
					impuestos, realSubtotal);
			break;
		case COMPLEMENTO:
			context = devolucionesBuilderService.buildFacturaContextForComplementoDevolution(facturaDto, pagoDto);
			impuestos = calculaImpuestos(context.getFacturaPadreDto().getCfdi());
			realSubtotal = calculaImporteBaseFactura(context.getFacturaPadreDto().getCfdi());
			baseComisiones = impuestos.divide(context.getFacturaPadreDto().getCfdi().getTotal(), 6,
					RoundingMode.HALF_UP);
			devolucionExecutorService.executeDevolucionForPpd(context, client,
					context.getFacturaPadreDto().getCfdi().getTotal(), baseComisiones, realSubtotal);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
					HttpStatus.BAD_REQUEST.value());
		}
	}

	public void updateSolicitudDevoluciones(String folio) {
		for (PagoDevolucion pagoDevolucion : pagoDevolucionRepository.findByFolioFactura(folio)) {
			pagoDevolucion.setStatus("VALIDACION");
			pagoDevolucionRepository.save(pagoDevolucion);
		}
	}

	public PagoDevolucionDto findDevolucionesByfacturaAndTipoReceptor(String folio, String tipoReceptor) {
		PagoDevolucion pagoDevolucion = pagoDevolucionRepository.findByFolioFacturaAndTipoReceptor(folio, tipoReceptor)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String
						.format("No hay solicitudes de devolucion para %s en la factura %s", tipoReceptor, folio)));
		return pagoDevolucionMapper.getPagoDevolucionDtoFromEntity(pagoDevolucion);
	}

	public Page<PagoDevolucionDto> getPagoDevolucionesByParams(Optional<String> folio, String status, String formaPago,
			String beneficiario, String tipoReceptor, String idReceptor, int page, int size) {
		Page<PagoDevolucion> result = new PageImpl<>(new ArrayList<>());
		if (folio.isPresent()) {
			result = pagoDevolucionRepository.findByFolioFactura(folio.get(), PageRequest.of(0, 10));
		} else if (tipoReceptor.length() > 0 && idReceptor.length() > 0) {
			result = pagoDevolucionRepository.findByTipoReceptorAndReceptor(tipoReceptor, idReceptor,
					PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
		} else if (status.length() > 0) {
			result = pagoDevolucionRepository.findByStatusAndParams(status, String.format("%%%s%%", formaPago),
					String.format("%%%s%%", beneficiario), String.format("%%%s%%", idReceptor),
					String.format("%%%s%%", tipoReceptor),
					PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
		} else {
			result = pagoDevolucionRepository.findByParams(String.format("%%%s%%", formaPago),
					String.format("%%%s%%", beneficiario), String.format("%%%s%%", idReceptor),
					String.format("%%%s%%", tipoReceptor),
					PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
		}
		return new PageImpl<>(pagoDevolucionMapper.getPagoDevolucionesDtoFromEntities(result.getContent()),
				result.getPageable(), result.getTotalElements());
	}

	public BigDecimal calculaImporteBaseFactura(CfdiDto cfdiDto) {
		BigDecimal subtotal = cfdiDto.getConceptos().stream().map(c -> c.getImporte())
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal retenciones = cfdiDto.getConceptos().stream()
				.map(i -> i.getRetenciones().stream().map(imp -> imp.getImporte()).reduce(BigDecimal.ZERO,
						(i1, i2) -> i1.add(i2)))
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return subtotal.subtract(retenciones);
	}

	public BigDecimal calculaImpuestos(CfdiDto cfdiDto) {
		return cfdiDto.getConceptos().stream()
				.map(i -> i.getImpuestos().stream().map(imp -> imp.getImporte()).reduce(BigDecimal.ZERO,
						(i1, i2) -> i1.add(i2)))// suma importe impuestos por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
