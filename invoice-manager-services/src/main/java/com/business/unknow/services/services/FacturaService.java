package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.model.dto.PagoReportDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.facturas.FacturaDao;
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
	private FacturaDao facturaDao;

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiService cfdiService;

	@Autowired
	private FacturaMapper mapper;

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
	private FilesService fileService;

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
				result = repository.findBySolicitanteAndStatusWithParams(solicitante.get(), lineaEmisor, status.get(),
						start, end, String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
			} else {
				result = repository.findBySolicitanteWithParams(solicitante.get(), lineaEmisor, start, end,
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

	public Page<FacturaReportDto> getFacturaReportsByParams(Optional<String> status, String lineaEmisor, String emisor,
			String receptor, Date since, Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (status.isPresent()) {
			result = repository.findReportsByLineaAndStatusEmisorWithParams(TipoDocumentoEnum.FACTURA.getDescripcion(),
					status.get(), lineaEmisor, start, end, String.format("%%%s%%", emisor),
					String.format("%%%s%%", receptor),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else {
			result = repository.findReportsByLineaEmisorWithParams(TipoDocumentoEnum.FACTURA.getDescripcion(),
					lineaEmisor, start, end, String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		}
		List<String> folios = result.getContent().stream().map(f -> f.getFolio()).collect(Collectors.toList());
		if (folios.isEmpty()) {
			return new PageImpl<>(new ArrayList<>(), result.getPageable(), result.getTotalElements());
		} else {
			return new PageImpl<FacturaReportDto>(facturaDao.getInvoiceDetailsByFolios(folios), result.getPageable(),
					result.getTotalElements());
		}
	}

	public Page<PagoReportDto> getComplementoReportsByParams(Optional<String> status, String lineaEmisor, String emisor,
			String receptor, Date since, Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (status.isPresent()) {
			result = repository.findReportsByLineaAndStatusEmisorWithParams(
					TipoDocumentoEnum.COMPLEMENTO.getDescripcion(), status.get(), lineaEmisor, start, end,
					String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else {
			result = repository.findReportsByLineaEmisorWithParams(TipoDocumentoEnum.COMPLEMENTO.getDescripcion(),
					lineaEmisor, start, end, String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		}
		List<String> folios = result.getContent().stream().map(f -> f.getFolio()).collect(Collectors.toList());
		if (folios.isEmpty()) {
			return new PageImpl<>(new ArrayList<>(), result.getPageable(), result.getTotalElements());
		} else {
			return new PageImpl<PagoReportDto>(facturaDao.getComplementsDetailsByFolios(folios), result.getPageable(),
					result.getTotalElements());
		}
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
	
	public FacturaDto getFacturaBaseByPrefolio(Integer id) {
		return mapper.getFacturaDtoFromEntity(
				repository.findByIdCfdi(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el pre-folio %d no existe", id))));
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
		entity.setTotal(cfdi.getTotal());
		entity.setSaldoPendiente(cfdi.getTotal());
		FacturaDto saveFactura = mapper.getFacturaDtoFromEntity(repository.save(entity));
		fileService.generateInvoicePDF(facturaBuilded, facturaContext.getCfdi());
		saveFactura.setCfdi(cfdi);
		return saveFactura;
	}

	public FacturaDto updateFactura(Integer idCfdi,FacturaDto factura) {
		if (repository.findByIdCfdi(idCfdi).isPresent()) {
			if (factura.getStatusPago().equals(PagoStatusEnum.PAGADA.getValor())
					&& factura.getStatusFactura().equals(FacturaStatusEnum.VALIDACION_TESORERIA.getValor())) {
				factura.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
			}
			//TODO review this custom logic
//			if (factura.getStatusFactura().equals(FacturaStatusEnum.RECHAZO_OPERACIONES.getValor())) {
//				List<PagoDto> pagos = pagoService.findPagosByFolio(folio);
//				for (PagoDto pago : pagos) {
//					pago.setStatusPago(RevisionPagosEnum.RECHAZADO.name());
//					try {
//						pagoService.updatePago(pago.getFolio(), pago.getId(), pago);
//					} catch (InvoiceManagerException e) {
//						System.out.println("Error updating factura");
//					}
//				}
//			}
			Factura entity = mapper.getEntityFromFacturaDto(factura);

			return mapper.getFacturaDtoFromEntity(repository.save(entity));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el pre-folio %d no existe", idCfdi));
		}
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void deleteFactura(String folio) throws InvoiceManagerException {
		Factura fact = repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("La factura con el folio %s no existe", folio)));
		repository.delete(fact);
		cfdiService.deleteCfdi(fact.getIdCfdi());
	}

	
	



	
	// TODO REFACTOR CREATE COMPLEMENTO LOGIC
	public FacturaDto createComplemento(Integer idCfdi, PagoDto pagoDto) throws InvoiceManagerException {
		
		List<CfdiPagoDto> pagosPPD = cfdiService.getPagosPPD(idCfdi);
		
		BigDecimal saldo = new BigDecimal(0);
		saldo=saldo.add(pagoDto.getMonto());
		for (CfdiPagoDto complemento : pagosPPD) {
			CfdiDto cfdiDto=cfdiService.getCfdiByFolio(complemento.getFolio());
			if (cfdiDto != null && cfdiDto.getComplemento() != null
					&& cfdiDto.getComplemento().getPagos() != null) {
				for (CfdiPagoDto cfdiPagoDto : cfdiDto.getComplemento().getPagos()) {
					saldo = saldo.add(cfdiPagoDto.getMonto());
				}
			}
		}
		return null;
//		if (saldo.compareTo(facturaPadre.getCfdi().getTotal()) > 0) {
//			throw new InvoiceManagerException("Incosistencia en el saldo de la factura", HttpStatus.CONFLICT.value());
//		} else {
//			return generateComplemento(facturaPadre, pagoDto);
//		}
	}

	// TODO Maybe this service is not needed with last changes
	public BigDecimal getFacturaSaldo(Integer idCfdi) throws InvoiceManagerException {
		CfdiDto cfdi = cfdiService.getCfdiById(idCfdi);
//		if (cfdi.getMetodoPago().equals(MetodosPagoEnum.PPD.name())
//				&& factura.getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())) {
//			List<CfdiPagoDto> pagosPPD = cfdiService.getPagosPPD(idCfdi);
//			BigDecimal saldo = new BigDecimal(0);
//			for (CfdiPagoDto complemento : pagosPPD) {
//				CfdiDto cfdiDto = cfdiService.getCfdiByFolio(complemento.getFolio());
//				if (cfdiDto != null && cfdiDto.getComplemento() != null
//						&& cfdiDto.getComplemento().getPagos() != null) {
//					for (CfdiPagoDto cfdiPagoDto : cfdiDto.getComplemento().getPagos()) {
//						saldo = saldo.add(cfdiPagoDto.getMonto());
//					}
//				}
//			}
//			return factura.getTotal().subtract(saldo);
//		} else {
//			return new BigDecimal(0);
//		}
		return new BigDecimal(0);
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
		// PDF GENERATION
		FacturaFileDto pdfFile = fileService.generateInvoicePDF(facturaContext);
		facturaContext.getFacturaFilesDto().add(pdfFile);
		if (facturaContext.getFacturaDto().getLineaRemitente().equals("CLIENTE")) {
			timbradoExecutorService.createFilesAndSentEmail(facturaContext);
		}
		if ((facturaContext.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PUE.name())
				|| (facturaContext.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PPD.name()) && facturaContext
						.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())))
				&& facturaContext.getFacturaDto().getLineaEmisor().equals("A")
				&& facturaContext.getFacturaDto().getLineaRemitente().equals("CLIENTE")) {
			devolucionService.generarDevolucionesPorPago(facturaContext.getFacturaDto(),
					facturaContext.getCurrentPago());
			devolucionService.updateSolicitudDevoluciones(folio);
		}

		// TODO Insertar en tabla de ingresos
		return facturaContext;
	}

	//TODO review cancelation logic
	public FacturaContext cancelarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		
//		validator.validateTimbrado(facturaDto, folio);
//		if (facturaDto.getTipoDocumento().equals("Factura") || facturaDto.getMetodoPago().equals("PPD")) {
//			List<FacturaDto> complementos = getComplementos(folio);
//			for (FacturaDto complemento : complementos) {
//				if (complemento.getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor())) {
//					cancelarFactura(complemento.getFolio(), complemento);
//				} else {
//					if (!complemento.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
//						complemento.setStatusFactura(FacturaStatusEnum.CANCELADA.getValor());
//						updateFactura(complemento, complemento.getFolio());
//					}
//				}
//			}
//		}
//		FacturaContext facturaContext = timbradoBuilderService.buildFacturaContextCancelado(facturaDto, folio);
//		timbradoServiceEvaluator.facturaCancelacionValidation(facturaContext);
//		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
//		case SW_SAPIENS:
//			swSapinsExecutorService.cancelarFactura(facturaContext);
//			break;
//		case FACTURACION_MODERNA:
//			facturacionModernaExecutor.cancelarFactura(facturaContext);
//			break;
//		case NTLINK:
//			ntinkExecutorService.cancelarFactura(facturaContext);
//			break;
//		default:
//			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
//					HttpStatus.BAD_REQUEST.value());
//		}
//		timbradoExecutorService.updateCanceladoValues(facturaContext);
//		return facturaContext;
		return null;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public FacturaDto generateComplemento(FacturaDto facturaPadre, PagoDto pagoPpd) throws InvoiceManagerException {

		FacturaContext factContext = facturaBuilderService.buildFacturaContextPagoPpdCreation(pagoPpd, facturaPadre,
				facturaPadre.getFolio());
		FacturaDto complemento = facturaBuilderService.buildFacturaDtoPagoPpdCreation(facturaPadre, pagoPpd);
		complemento.setCfdi(facturaBuilderService.buildFacturaComplementoCreation(factContext));
		facturaDefaultValues.assignaDefaultsComplemento(complemento);
		factContext.setFacturaDto(complemento);
		facturaServiceEvaluator.complementoValidation(factContext);
		// Save complemento in DB
		CfdiDto cfdi = cfdiService.insertNewCfdi(complemento.getCfdi());
		Factura fact = mapper.getEntityFromFacturaDto(complemento);
		fact.setIdCfdi(cfdi.getId());
		return mapper.getFacturaDtoFromEntity(repository.save(fact));
	}

	public void recreatePdf(CfdiDto dto) {
		FacturaDto factura = mapper.getFacturaDtoFromEntity(repository.findByFolio(dto.getFolio())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", dto.getFolio()))));
		factura.setCfdi(dto);
		fileService.generateInvoicePDF(factura, null);
	}

}
