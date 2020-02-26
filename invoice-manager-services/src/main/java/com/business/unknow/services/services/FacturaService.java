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
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.builder.FacturaBuilderService;
import com.business.unknow.services.services.builder.TimbradoBuilderService;
import com.business.unknow.services.services.evaluations.FacturaEvaluatorService;
import com.business.unknow.services.services.evaluations.TimbradoEvaluatorService;
import com.business.unknow.services.services.executor.FacturacionModernaExecutor;
import com.business.unknow.services.services.executor.NtinkExecutorService;
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
	private FacturaMapper mapper;

	@Autowired
	private PagoService pagoService;

	@Autowired
	private TimbradoEvaluatorService timbradoServiceEvaluator;

	@Autowired
	private FacturaEvaluatorService facturaServiceEvaluator;

	@Autowired
	private FacturaBuilderService facturaBuilderService;

	@Autowired
	private TimbradoBuilderService timbradoBuilderService;

	@Autowired
	private FacturaTranslator facturaTranslator;

	@Autowired
	private SwSapinsExecutorService swSapinsExecutorService;

	@Autowired
	private FacturacionModernaExecutor facturacionModernaExecutor;

	@Autowired
	private NtinkExecutorService ntinkExecutorService;

	@Autowired
	private TimbradoExecutorService timbradoExecutorService;

	@Autowired
	private DevolucionService devolucionService;

	@Autowired
	private FacturaDefaultValues facturaDefaultValues;

	private FacturaValidator validator = new FacturaValidator();

	// FACTURAS
	public Page<FacturaDto> getFacturasByParametros(Optional<String> folio, Optional<String> solicitante,
			String lineaEmisor, Optional<String> status, Date since, Date to, String emisor, String receptor, int page,
			int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(),
					PageRequest.of(0, 10, Sort.by("fechaActualizacion").descending()));
		} else if (solicitante.isPresent()) {
			if (status.isPresent() && status.get().length() > 0) {
				result = repository.findBySolicitanteAndStatusWithParams(solicitante.get(), status.get(), start, end,
						String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
			} else {
				result = repository.findBySolicitanteWithParams(solicitante.get(), start, end,
						String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
			}
		} else {
			if (status.isPresent() && status.get().length() > 0) {
				result = repository.findByLineaEmisorAndStatusWithParams(lineaEmisor, status.get(), start, end,
						String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
			} else {
				result = repository.findByLineaEmisorWithParams(lineaEmisor, start, end,
						String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
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

	public FacturaDto getBaseFacturaByFolio(String folio) {
		return mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
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
		FacturaDto saveFactura = saveFactura(entity);
		if (entity.getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
			pagoService.insertNewPaymentWithoutValidation(
					facturaDefaultValues.assignaDefaultsPagoPPD(facturaBuilded.getCfdi()));
		}
		return saveFactura;
	}

	private FacturaDto saveFactura(Factura factura) {
		return mapper.getFacturaDtoFromEntity(repository.save(factura));
	}

	public FacturaDto updateFactura(FacturaDto factura, String folio) {
		if (repository.findByFolio(folio).isPresent()) {
			if (factura.getStatusPago().equals(PagoStatusEnum.PAGADA.getValor())
					&& factura.getStatusFactura().equals(FacturaStatusEnum.VALIDACION_TESORERIA.getValor())) {
				factura.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
			}
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
		case FACTURA:
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
		case NTLINK:
			ntinkExecutorService.stamp(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.BAD_REQUEST.value());
		}
		timbradoExecutorService.updateFacturaAndCfdiValues(facturaContext);
		if (!(facturaContext.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())
				&& facturaContext.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PPD.name()))) {
			devolucionService.generarDevolucionesPorPago(facturaContext.getFacturaDto(),
					facturaContext.getCurrentPago());
		}
		// TODO Insertar en tabla de ingresos
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
		case NTLINK:
			facturacionModernaExecutor.cancelarFactura(facturaContext);
			break;	
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.BAD_REQUEST.value());
		}
		timbradoExecutorService.updateCanceladoValues(facturaContext);
		return facturaContext;
	}

	public void buildComplemento(FacturaContext facturaContext) throws InvoiceManagerException {
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

}
