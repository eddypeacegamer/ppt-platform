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
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.builder.FacturaBuilderService;
import com.business.unknow.services.services.builder.PagoBuilderService;
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
	private PagoMapper mapper;

	@Autowired
	private PagoEvaluatorService pagoEvaluatorService;

	@Autowired
	private PagoBuilderService pagoBuilderService;

	@Autowired
	private PagoExecutorService pagoExecutorService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private DevolucionService devolucionService;

	@Autowired // TODO evaluate remove this dependency when factura context will be disabled
	private FacturaBuilderService facturaBuilderService;

	private static final Logger log = LoggerFactory.getLogger(PagoService.class);

	public Page<PagoDto> getPaginatedPayments(Optional<String> folio, String formaPago, String status, String banco,
			Date since, Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Pago> result;
		if (folio.isPresent()) {
			log.info("Searching PaymentsByFolio {}", folio.get());
			result = repository.findByFolioIgnoreCaseContaining(folio.get(),
					PageRequest.of(0, 10, Sort.by("fechaCreacion").descending()));
		} else {
			log.info("Search payments by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago,
					banco, start, end);
			result = repository.findPagosByFilterParams(String.format("%%%s%%", status),
					String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end,
					PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
		}

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	public List<PagoDto> getPagos(String folio) {
		return mapper.getPagosDtoFromEntities(repository.findByFolioPadre(folio));
	}

	public Page<PagoDto> getIngresosPaginados(String formaPago, String status, String banco, String cuenta, Date since,
			Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		log.info("Search ingresos by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago, banco,
				start, end);
		Page<Pago> result = repository.findIngresosByFilterParams(String.format("%%%s%%", status),
				String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), String.format("%%%s%%", cuenta),
				start, end, PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public Page<PagoDto> getEgresosPaginados(String formaPago, String status, String banco, String cuenta, Date since,
			Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		log.info("Search egresos by status {}, formapago {}, banco {} and start {} y end {}", status, formaPago, banco,
				start, end);
		Page<Pago> result = repository.findEgresosByFilterParams(String.format("%%%s%%", status),
				String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), String.format("%%%s%%", cuenta),
				start, end, PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));

		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public Double getSumaIngresosbyParams(String formaPago, String banco, String cuenta, Date since, Date to) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		return repository.sumIngresosByFilterParams(String.format("%%%s%%", formaPago), String.format("%%%s%%", banco),
				String.format("%%%s%%", cuenta), start, end);
	}

	public Double getSumaEgresosbyParams(String formaPago, String banco, String cuenta, Date since, Date to) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		return repository.sumEgresosByFilterParams(String.format("%%%s%%", formaPago), String.format("%%%s%%", banco),
				String.format("%%%s%%", cuenta), start, end);
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

	public PagoDto insertNewPayment(PagoDto payment) {
		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(payment)));
	}

	public PagoDto upadtePayment(Integer paymentId, PagoDto payment) throws InvoiceManagerException {
		log.info("Updating Payment : {}", payment);
		repository.findById(paymentId).orElseThrow(() -> new InvoiceManagerException("Payment Id not found",
				String.format("The payment with id %d was not found", paymentId), HttpStatus.NOT_FOUND.value()));
		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(payment)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto insertNewPago(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		pagoEvaluatorService.validatePago(pagoDto, new PagoDto());
		FacturaDto factura = facturaService.getFacturaByFolio(folio);
		if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
			FacturaContext facturaContext = facturaBuilderService.buildFacturaContextPagoPpdCreation(pagoDto,
					facturaService.getFacturaByFolio(folio), folio);
			pagoEvaluatorService.validatePagoPpdCreation(facturaContext);
			facturaService.buildComplemento(facturaContext);
			return pagoExecutorService.creaPagoPpdExecutor(facturaContext);
		} else if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
			FacturaContext facturaContext = facturaBuilderService.buildFacturaContextPagoPueCreation(folio, pagoDto);
			pagoEvaluatorService.validatePagoPueCreation(facturaContext);
			if (facturaContext.getPagoCredito() != null) {
				facturaContext.getPagoCredito().setMonto(facturaContext.getPagoCredito().getMonto()
						.subtract(facturaContext.getCurrentPago().getMonto()));
				return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(pagoDto)));
			}
		}
		throw new InvoiceManagerException("Metodo de pago no soportado",
				String.format("El metodo de pago %s no es valido", factura.getCfdi().getMetodoPago()),
				HttpStatus.BAD_REQUEST.value());

	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(String folio, Integer id, PagoDto pago) throws InvoiceManagerException {
		Pago entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));

		PagoBuilder pagoBuilder = new PagoBuilder(mapper.getPagoDtoFromEntity(entity)) // payment only update revision
																						// status
				.setRevision1(pago.getRevision1()).setRevision2(pago.getRevision2()).setRevisor1(pago.getRevisor1())
				.setRevisor2(pago.getRevisor2());
		pagoEvaluatorService.validatePago(pago, mapper.getPagoDtoFromEntity(entity));

		if (pago.getStatusPago().equals(RevisionPagosEnum.RECHAZADO.name())) {
			FacturaDto factura = facturaService.getBaseFacturaByFolio(folio);
			factura.setStatusFactura(FacturaStatusEnum.RECHAZO_TESORERIA.getValor());
			factura.setStatusDetail(pago.getComentarioPago());
			facturaService.updateFactura(factura, folio);
		} else if (pago.getRevision1() && pago.getRevision2()) {
			entity.setStatusPago(RevisionPagosEnum.ACEPTADO.name());
			FacturaDto factura = facturaService.getBaseFacturaByFolio(folio);
			factura.setStatusPago(PagoStatusEnum.PAGADA.getValor());
			factura.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
			facturaService.updateFactura(factura, folio);
			devolucionService.generarDevolucionesPorPago(factura, pago);
		}

		return mapper.getPagoDtoFromEntity(repository.save(mapper.getEntityFromPagoDto(pagoBuilder.build())));
	}

	public void deletePago(String folio, Integer id) throws InvoiceManagerException {
		Pago pago = repository.findById(id).orElseThrow(() -> new InvoiceManagerException("Metodo de pago no soportado",
				String.format("El pago con el id no existe %d", id), HttpStatus.BAD_REQUEST.value()));
		FacturaDto factura = facturaService.getBaseFacturaByFolio(pago.getFolio());

		FacturaContext context = null;
		switch (TipoDocumentoEnum.findByDesc(factura.getTipoDocumento())) {
		case FACTURA:
			context = pagoBuilderService.deletePagoPueBuilder(factura, pago, id);
			pagoEvaluatorService.deletePagoPueValidation(context);
			pagoEvaluatorService.deleteComplementoValidation(context);
			pagoExecutorService.deletePagoPueExecutor(context);
			break;
		case COMPLEMENTO:
			context = pagoBuilderService.deletePagoPpdBuilder(factura, pago, id);
			pagoEvaluatorService.deletePagoPpdValidation(context);
			pagoExecutorService.deletePagoPpdExecutor(context);
			break;
		default:
			new InvoiceManagerException("Tipo de documento not suported",
					String.format("Documento %s not suported", factura.getTipoDocumento()),
					HttpStatus.CONFLICT.value());
			break;
		}
	}

}
