package com.business.unknow.services.services.evaluations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.commons.validator.PagoValidator;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.facturas.ComplementoSuite;
import com.business.unknow.rules.suites.pagos.DeletePagoSuite;
import com.business.unknow.rules.suites.pagos.PagoPpdSuite;
import com.business.unknow.rules.suites.pagos.PagoPueSuite;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.services.executor.PagoExecutorService;

@Service
public class PagoEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	private PagoPpdSuite pagoPpdSuite;

	@Autowired
	private DeletePagoSuite deletePagoSuite;

	@Autowired
	private PagoPueSuite pagoPueSuite;

	@Autowired
	private ComplementoSuite complementoSuite;

	@Autowired
	private PagoExecutorService pagoExecutorService;
	
	@Autowired
	private DevolucionEvaluatorService devolucionService;
	
	@Autowired
	private FacturaMapper facturaMapper;
	
	private PagoValidator pagoValidator=new PagoValidator();

	public void validatePagoDeleting(Integer id) throws InvoiceManagerException {
		Pago pago = pagoRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Metodo de pago no soportado",
						String.format("El pago con el id no existe %d", id), HttpStatus.BAD_REQUEST.value()));
		Factura factura = repository.findByFolio(pago.getFolio())
				.orElseThrow(() -> new InvoiceManagerException("No existe la factura del pago",
						String.format("Folio with the name %s not found", pago.getFolio()),
						HttpStatus.NOT_FOUND.value()));
		switch (TipoDocumentoEnum.findByDesc(factura.getTipoDocumento())) {
		case FACRTURA:
			deletePagoPue(factura, pago, id);
			break;
		case COMPLEMENTO:
			deletePagoPpd(factura, pago, id);
			break;
		default:
			new InvoiceManagerException("Tipo de documento not suported",
					String.format("Documento %s not suported", factura.getTipoDocumento()),
					HttpStatus.CONFLICT.value());
			break;
		}
	}

	public void deletePagoPpd(Factura factura, Pago pago, int id) throws InvoiceManagerException {
		Pago pagoPadre = pagoRepository.findByFolio(factura.getFolioPadre()).stream().findFirst()
				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
						String.format("Verificar consitencia de pagos del folio %s", factura.getFolioPadre()),
						HttpStatus.NOT_FOUND.value()));
		PagoDto pagoDto=mapper.getPagoDtoFromEntity(pago);
		FacturaContext context = new FacturaContextBuilder()
				.setPagos(Arrays.asList(pagoDto))
				.setCurrentPago(pagoDto)
				.setFacturaDto(mapper.getFacturaDtoFromEntity(factura))
				.setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre)).build();
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
		deleteComplementoValidation(context);
		pagoExecutorService.deletePagoPpdExecutor(context);
	}

	public void deletePagoPue(Factura factura, Pago pago, int id) throws InvoiceManagerException {
		List<Pago> pagos = pagoRepository.findByFolio(factura.getFolio());
		Optional<Pago> pagoCredito = pagos.stream()
				.filter(p -> p.getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())).findFirst();
		FacturaContext context = new FacturaContextBuilder().setCurrentPago(pagoMapper.getPagoDtoFromEntity(pago))
				.setPagos(pagoMapper.getPagosDtoFromEntities(pagos))
				.setFacturaDto(mapper.getFacturaDtoFromEntity(factura))
				.setPagoCredito(pagoCredito.isPresent() ? pagoMapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.build();
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
		pagoExecutorService.deletePagoPueExecutor(context);
	}

	public PagoDto validatePagoCreation(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		FacturaContext facturaContext;
		FacturaDto factura = facturaMapper.getFacturaDtoFromEntity(repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("No se encuentra la factura en el sistema",
						String.format("Folio with the name %s not found", folio), HttpStatus.NOT_FOUND.value())));
		pagoDto.setCreateUser(pagoDto.getUltimoUsuario());
		if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
			facturaContext = validatePagoPpdCreation(folio, pagoDto, factura);
		} else if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
			facturaContext = validatePagoPueCreation(folio, pagoDto);
		} else {
			throw new InvoiceManagerException("Metodo de pago no soportado",
					String.format("El metodo de pago %s no es valido", factura.getCfdi().getMetodoPago()),
					HttpStatus.BAD_REQUEST.value());
		}
		return pagoExecutorService.PagoCreation(facturaContext);
	}

	private FacturaContext validatePagoPpdCreation(String folio, PagoDto pagoDto, FacturaDto factura)
			throws InvoiceManagerException {
		FacturaContext facturaContext=buildFacturaContextPagoPpdCreation(pagoDto, folio);
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPpdSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		facturaContext.setFacturaDto(buildFacturaDtoPagoPpdCreation(facturaContext));
		facturaContext = createNewComplementoValidation(facturaContext, facturaContext.getFacturaPadreDto().getFolio());
		facturaContext.getCurrentPago().setFolio(facturaContext.getFacturaDto().getFolio());
		facturaContext.getCurrentPago().setFolioPadre(facturaContext.getFacturaDto().getFolioPadre());
		facturaContext.setPagos(Arrays.asList(facturaContext.getCurrentPago()));
		facturaContext.getPagoCredito()
				.setMonto(numberHelper.assignPrecision(
						facturaContext.getPagoCredito().getMonto() - facturaContext.getCurrentPago().getMonto(),
						Constants.DEFAULT_SCALE));
		return pagoExecutorService.creaPapoPpdExecutor(facturaContext);
	}

	private FacturaContext validatePagoPueCreation(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		FacturaContext facturaContext = buildFacturaContextPagoPueCreation(folio, pagoDto);
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPueSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		if (facturaContext.getPagoCredito() != null) {
			facturaContext.getPagoCredito().setMonto(facturaContext.getPagoCredito().getMonto() - pagoDto.getMonto());
			pagoExecutorService.creaPapoPueExecutor(facturaContext);
		}
		return facturaContext;
	}


	private FacturaContext createNewComplementoValidation(FacturaContext facturaContext, String folio)
			throws InvoiceManagerException {
		Facts facts = new Facts();
		facturaDefaultValues.assignaDefaultsComplemento(facturaContext.getFacturaDto());
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(complementoSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return facturaContext;
	}

	private FacturaContext deleteComplementoValidation(FacturaContext context) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
		return context;
	}
	
	public PagoDto validatePagoUpdate(PagoDto pago, Integer id) throws InvoiceManagerException {
		Pago entity = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		pagoValidator.validatePagoCreator(pago, pagoMapper.getPagoDtoFromEntity(entity));
		// Bank, Document, payForm and pay type can't be updated
		entity.setComentarioPago(pago.getComentarioPago());
		entity.setRevision1(pago.getRevision1());
		entity.setRevision2(pago.getRevision2());
		entity.setUltimoUsuario(pago.getUltimoUsuario());
		entity.setStatusPago(pago.getStatusPago());
		if (pago.getStatusPago().equals(RevisionPagosEnum.RECHAZADO.name())) {
			Factura factura = repository.findByFolio(pago.getFolio())
					.orElseThrow(() -> new InvoiceManagerException("El pago no tiene  asignada una factura",
							"Es necesario revisar la integridad de los pagos", HttpStatus.CONFLICT.value()));
			factura.setStatusFactura(FacturaStatusEnum.RECHAZO_TESORERIA.getValor());
			repository.save(factura);
		} else {
			if (pago.getRevision1() && pago.getRevision2()) {
				entity.setStatusPago(RevisionPagosEnum.ACEPTADO.name());
				Factura factura = repository.findByFolio(pago.getFolio())
						.orElseThrow(() -> new InvoiceManagerException("El pago no tiene  asignada una factura",
								"Es necesario revisar la integridad de los pagos", HttpStatus.CONFLICT.value()));
				factura.setStatusPago(PagoStatusEnum.PAGADA.getValor());
				factura.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
				repository.save(factura);
				
				devolucionService.generarDevolucionesPorPago(facturaMapper.getFacturaDtoFromEntity(factura),pago);
			}
		}
		return mapper.getPagoDtoFromEntity(pagoRepository.save(entity));
	}

}
