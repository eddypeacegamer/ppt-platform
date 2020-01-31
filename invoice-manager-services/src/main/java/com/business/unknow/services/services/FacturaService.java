package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.builder.FacturaBuilderService;
import com.business.unknow.services.services.builder.PagoBuilderService;
import com.business.unknow.services.services.builder.TimbradoBuilderService;
import com.business.unknow.services.services.evaluations.FacturaEvaluatorService;
import com.business.unknow.services.services.evaluations.PagoEvaluatorService;
import com.business.unknow.services.services.evaluations.TimbradoEvaluatorService;
import com.business.unknow.services.services.executor.FacturacionModernaExecutor;
import com.business.unknow.services.services.executor.PagoExecutorService;
import com.business.unknow.services.services.executor.SwSapinsExecutorService;
import com.business.unknow.services.services.executor.TimbradoExecutorService;
import com.business.unknow.services.services.translators.FacturaTranslator;
import com.business.unknow.services.util.FacturaDefaultValues;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiService cfdiService;
	
	@Autowired
	private DevolucionService devolucionService;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private PagoMapper pagoMapper;
	
	@Autowired
	private PagoService pagoService;

	@Autowired
	private TimbradoEvaluatorService timbradoServiceEvaluator;

	@Autowired
	private FacturaEvaluatorService facturaServiceEvaluator;

	@Autowired
	private PagoEvaluatorService pagoEvaluatorService;

	@Autowired
	private FacturaBuilderService facturaBuilderService;
	
	@Autowired
	private  TimbradoBuilderService timbradoBuilderService;
	
	@Autowired
	private PagoBuilderService pagoBuilderService;
	
	@Autowired
	private FacturaTranslator facturaTranslator;
	
	@Autowired
	private PagoExecutorService pagoExecutorService;
	
	@Autowired
	private SwSapinsExecutorService swSapinsExecutorService;

	@Autowired
	private FacturacionModernaExecutor facturacionModernaExecutor;
	
	@Autowired
	private TimbradoExecutorService timbradoExecutorService;

	@Autowired
	private FacturaDefaultValues facturaDefaultValues;

	private FacturaValidator validator = new FacturaValidator();

	// FACTURAS
	public Page<FacturaDto> getFacturasByParametros(Optional<String> folio, Optional<String> solicitante,
			String lineaEmisor, Optional<String> status, Date since, Date to, String rfcEmisor, String rfcRemitente,
			int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(),
					PageRequest.of(0, 10, Sort.by("fechaCreacion").descending()));
		} else if (solicitante.isPresent()) {
			if (status.isPresent() && status.get().length() > 0) {
				result = repository.findBySolicitanteAndStatusWithParams(solicitante.get(), status.get(), start, end,
						String.format("%%%s%%", rfcEmisor), String.format("%%%s%%", rfcRemitente),
						PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
			} else {
				result = repository.findBySolicitanteWithParams(solicitante.get(), start, end,
						String.format("%%%s%%", rfcEmisor), String.format("%%%s%%", rfcRemitente),
						PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
			}
		} else {
			if (status.isPresent() && status.get().length() > 0) {
				result = repository.findByLineaEmisorAndStatusWithParams(lineaEmisor, status.get(), start, end,
						String.format("%%%s%%", rfcEmisor), String.format("%%%s%%", rfcRemitente),
						PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
			} else {
				result = repository.findByLineaEmisorWithParams(lineaEmisor, start, end,
						String.format("%%%s%%", rfcEmisor), String.format("%%%s%%", rfcRemitente),
						PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
			}

		}
		return new PageImpl<>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public FacturaDto getFacturaByFolio(String folio) {
		FacturaDto factura = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		factura.setCfdi(cfdiService.getCfdiByFolio(folio));
		return factura;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public FacturaDto insertNewFacturaWithDetail(FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validatePostFacturaWithDetail(facturaDto);
		FacturaContext facturaContext = facturaBuilderService.buildFacturaContextCreateFactura(facturaDto);
		facturaDefaultValues.assignaDefaultsFactura(facturaContext.getFacturaDto());
		FacturaDto facturaBuilded = facturaServiceEvaluator.facturaEvaluation(facturaContext).getFacturaDto();
		CfdiDto cfdi = cfdiService.insertNewCfdi(facturaDto.getCfdi());
		Factura entity = mapper.getEntityFromFacturaDto(facturaBuilded);
		entity.setIdCfdi(cfdi.getId());
		if (entity.getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
			pagoService.insertNewPayment(facturaDefaultValues.assignaDefaultsPagoPPD(cfdi));
		}
		return saveFactura(entity);
	}

	private FacturaDto saveFactura(Factura factura) {
		return mapper.getFacturaDtoFromEntity(repository.save(factura));
	}

	public FacturaDto updateFactura(FacturaDto factura, String folio) {
		if (repository.findByFolio(folio).isPresent()) {
			Factura entity = mapper.getEntityFromFacturaDto(factura);
			return mapper.getFacturaDtoFromEntity(repository.save(entity));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el folio %s no existe", folio));
		}
	}

	// CFDI
	public CfdiDto getCfdiByFolio(String folio) {
		return cfdiService.getCfdiByFolio(folio);
	}

	public CfdiDto insertNewConcepto(String folio, ConceptoDto concepto) throws InvoiceManagerException {
		cfdiService.insertNewConceptoToCfdi(folio, concepto);
		return cfdiService.getCfdiByFolio(folio);

	}

	// COMPLEMNENTOS
	public List<FacturaDto> getComplementos(String folioPadre) {
		return mapper.getFacturaDtosFromEntities(repository.findComplementosByFolioPadre(folioPadre));
	}

	// TIMBRADO
	public FacturaContext timbrarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		FacturaContext facturaContext = timbradoBuilderService.buildFacturaContextTimbrado(facturaDto, folio);
		timbradoServiceEvaluator.facturaTimbradoValidation(facturaContext);
		switch (TipoDocumentoEnum.findByDesc(facturaContext.getTipoDocumento())) {
		case FACRTURA:
			facturaContext = facturaTranslator.translateFactura(facturaContext);
			break;
		case COMPLEMENTO:
			facturaContext = facturaTranslator.translateComplemento(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaContext.getTipoDocumento()),
					HttpStatus.BAD_REQUEST.value());
		}
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.stamp(facturaContext);
			break;
		case FACTURACION_MODERNA:
			facturacionModernaExecutor.stamp(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.BAD_REQUEST.value());
		}
		timbradoExecutorService.updateFacturaAndCfdiValues(facturaContext);
		return facturaContext;
	}

	public FacturaContext cancelarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		FacturaContext facturaContext = timbradoBuilderService.buildFacturaContextCancelado(facturaDto, folio);
		timbradoServiceEvaluator.facturaCancelacionValidation(facturaContext);
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.cancelarFactura(facturaContext);
			break;
		case FACTURACION_MODERNA:
			facturacionModernaExecutor.cancelarFactura(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.BAD_REQUEST.value());
		}
		timbradoExecutorService.updateCanceladoValues(facturaContext);
		return facturaContext;
	}

	// PAGOS
	public List<PagoDto> getPagos(String folio) {
		return mapper.getPagosDtoFromEntity(pagoRepository.findByFolioPadre(folio));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto insertNewPago(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		FacturaContext facturaContext;
		FacturaDto factura = getFacturaByFolio(folio);
		pagoDto.setCreateUser(pagoDto.getUltimoUsuario());
		if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
			facturaContext = facturaBuilderService.buildFacturaContextPagoPpdCreation(pagoDto, getFacturaByFolio(folio),
					folio);
			pagoEvaluatorService.validatePagoPpdCreation(facturaContext);
			buildComplemento(facturaContext);
			pagoExecutorService.creaPagoPpdExecutor(facturaContext);
		} else if (factura.getCfdi().getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
			facturaContext = facturaBuilderService.buildFacturaContextPagoPueCreation(folio, pagoDto);
			pagoEvaluatorService.validatePagoPueCreation(facturaContext);
			if (facturaContext.getPagoCredito() != null) {
				facturaContext.getPagoCredito().setMonto(facturaContext.getPagoCredito().getMonto()
						.subtract(facturaContext.getCurrentPago().getMonto()));
				pagoExecutorService.creaPagoPueExecutor(facturaContext);
			}
		} else {
			throw new InvoiceManagerException("Metodo de pago no soportado",
					String.format("El metodo de pago %s no es valido", factura.getCfdi().getMetodoPago()),
					HttpStatus.BAD_REQUEST.value());
		}
		return pagoExecutorService.PagoCreation(facturaContext);
	}

	private void buildComplemento(FacturaContext facturaContext) throws InvoiceManagerException {
		facturaContext.setFacturaDto(facturaBuilderService.buildFacturaDtoPagoPpdCreation(facturaContext));
		facturaContext.getFacturaDto().setCfdi(facturaBuilderService.buildFacturaComplementoCreation(facturaContext));
		facturaDefaultValues.assignaDefaultsComplemento(facturaContext.getFacturaDto());
		facturaServiceEvaluator.complementoValidation(facturaContext);
		facturaContext.getCurrentPago().setFolio(facturaContext.getFacturaDto().getFolio());
		facturaContext.getCurrentPago().setFolioPadre(facturaContext.getFacturaDto().getFolioPadre());
		facturaContext.setPagos(Arrays.asList(facturaContext.getCurrentPago()));
		facturaContext.getPagoCredito().setMonto(
				facturaContext.getPagoCredito().getMonto().subtract(facturaContext.getCurrentPago().getMonto()));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(PagoDto pago, Integer id) throws InvoiceManagerException {
		Pago entity = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		pagoEvaluatorService.validatePagoCreator(pago, pagoMapper.getPagoDtoFromEntity(entity));
		pagoBuilderService.buildUpdatePagoEntity(entity, pago);
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
				devolucionService.generarDevolucionesPorPago(mapper.getFacturaDtoFromEntity(factura), pago);
			}
		}
		return mapper.getPagoDtoFromEntity(pagoRepository.save(entity));
	}

	public void deletePago(Integer id) throws InvoiceManagerException {
		Pago pago = pagoRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Metodo de pago no soportado",
						String.format("El pago con el id no existe %d", id), HttpStatus.BAD_REQUEST.value()));
		Factura factura = repository.findByFolio(pago.getFolio())
				.orElseThrow(() -> new InvoiceManagerException("No existe la factura del pago",
						String.format("Folio with the name %s not found", pago.getFolio()),
						HttpStatus.NOT_FOUND.value()));
		FacturaContext context= null;
		switch (TipoDocumentoEnum.findByDesc(factura.getTipoDocumento())) {
		case FACRTURA:
			context=pagoBuilderService.deletePagoPueBuilder(factura, pago, id);
			pagoEvaluatorService.deletePagoPueValidation(context);
			pagoEvaluatorService.deleteComplementoValidation(context);
			pagoExecutorService.deletePagoPueExecutor(context);
			break;
		case COMPLEMENTO:
			context=pagoBuilderService.deletePagoPpdBuilder(factura, pago, id);
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
