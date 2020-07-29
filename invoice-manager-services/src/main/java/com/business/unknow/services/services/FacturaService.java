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

import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.enums.TipoEmail;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.model.dto.PagoReportDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.CfdiPago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.facturas.CfdiPagoRepository;
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
	private CfdiPagoRepository cfdiPagoRepository;

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
	private PDFService pdfService;

	@Autowired
	private FacturaDefaultValues facturaDefaultValues;

	@Autowired
	private DateHelper dateHelper;

	private FacturaValidator validator = new FacturaValidator();

	// FACTURAS
	public Page<FacturaDto> getFacturasByParametros(Optional<Integer> prefolio, Optional<String> solicitante,
			String lineaEmisor, Optional<String> status, Date since, Date to, String emisor, String receptor, int page,
			int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		end = dateHelper.setMidNigthDate(end);
		Page<Factura> result;
		if (prefolio.isPresent()) {
			result = repository.findByIdCfdi(prefolio.get(), PageRequest.of(0, 10));
		} else if (solicitante.isPresent()) {
			if (status.isPresent() && status.get().length() > 0) {
				result = repository.findBySolicitanteAndStatusWithParams(solicitante.get(), lineaEmisor, status.get(),
						start, end, String.format("%%%s%%", emisor), String.format("%%%s%%", receptor),
						PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
			} else if (emisor != null && emisor.length() > 5 && receptor != null && receptor.length() > 5) {
				result = repository.findFacturasPPD(solicitante.get(), lineaEmisor, emisor, receptor,
						PageRequest.of(page, size, Sort.by("saldoPendiente").descending()));
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
		end = dateHelper.setMidNigthDate(end);
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
		end = dateHelper.setMidNigthDate(end);
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

	public FacturaDto getComplementoByIdCfdiAnParcialidad(String folio, Integer parcialidad) {
		List<CfdiPago> pagos = cfdiPagoRepository.findByIdCfdiAndParcialidad(folio, parcialidad);
		Optional<CfdiPago> pago = pagos.stream().findFirst();
		if (pago.isPresent()) {
			return getFacturaBaseByPrefolio(pago.get().getCfdi().getId());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el pre-folio %s no existe", folio));
		}
	}

	public FacturaDto getFacturaByFolio(String folio) {
		FacturaDto factura = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		factura.setCfdi(cfdiService.getCfdiByFolio(folio));
		return factura;
	}

	public FacturaDto getFacturaByIdCfdi(int id) {
		CfdiDto cfdiDto = cfdiService.getCfdiById(id);
		FacturaDto factura = mapper.getFacturaDtoFromEntity(repository.findByFolio(cfdiDto.getFolio())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", cfdiDto.getFolio()))));
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
		facturaDefaultValues.assignaDefaultsFactura(facturaContext.getFacturaDto(),
				facturaDao.getCantidadFacturasOfTheCurrentMonthByTipoDocumento(facturaDto.getTipoDocumento()));
		FacturaDto facturaBuilded = facturaServiceEvaluator.facturaEvaluation(facturaContext).getFacturaDto();
		CfdiDto cfdi = cfdiService.insertNewCfdi(facturaDto.getCfdi());
		Factura entity = mapper.getEntityFromFacturaDto(facturaBuilded);
		entity.setIdCfdi(cfdi.getId());
		entity.setTotal(cfdi.getTotal());
		entity.setSaldoPendiente(cfdi.getTotal());
		FacturaDto saveFactura = mapper.getFacturaDtoFromEntity(repository.save(entity));
		pdfService.generateInvoicePDF(facturaBuilded, facturaContext.getCfdi());
		saveFactura.setCfdi(cfdi);
		return saveFactura;
	}

	public FacturaDto updateTotalAndSaldoFactura(Integer idCfdi, BigDecimal total, BigDecimal saldo)
			throws InvoiceManagerException {
		validator.checkNotNegative(saldo, "Saldo pendiente");
		Factura factura = repository.findByIdCfdi(idCfdi)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el pre-folio %d no existe", idCfdi)));
		factura.setTotal(total);
		// TODO: BUSCAR PAGOS
		factura.setSaldoPendiente(saldo);
		return mapper.getFacturaDtoFromEntity(repository.save(factura));
	}

	public FacturaDto updateFacturaStatus(Integer idCfdi, FacturaStatusEnum status) {
		Factura factura = repository.findByIdCfdi(idCfdi)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el pre-folio %d no existe", idCfdi)));
		factura.setStatusFactura(status.getValor());
		return mapper.getFacturaDtoFromEntity(repository.save(factura));
	}

	public FacturaDto updateFactura(Integer idCfdi, FacturaDto facturaDto) {
		Optional<Factura> factura = repository.findByIdCfdi(idCfdi);
		if (factura.isPresent()) {
			facturaServiceEvaluator.facturaStatusValidation(facturaDto);
			factura.get().setStatusFactura(facturaDto.getStatusFactura());
			factura.get().setValidacionOper(facturaDto.getValidacionOper());
			factura.get().setValidacionTeso(facturaDto.getValidacionTeso());
			factura.get().setStatusDetail(facturaDto.getStatusDetail());
			return mapper.getFacturaDtoFromEntity(repository.save(factura.get()));
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

	public FacturaDto createComplemento(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		pagoDto.setMonto(pagoDto.getMonto().setScale(2));
		FacturaDto facturaDto = getBaseFacturaByFolio(folio);
		List<FacturaDto> facturas = new ArrayList<>();
		List<PagoFacturaDto> facturaPagos = new ArrayList<>();
		PagoFacturaDto facturaPagoDto = new PagoFacturaDto();
		facturaPagoDto.setMonto(pagoDto.getMonto());
		facturaPagoDto.setFolio(folio);
		facturaPagos.add(facturaPagoDto);
		facturas.add(facturaDto);
		pagoDto.setFacturas(facturaPagos);
		return generateComplemento(facturas, pagoDto);
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
		FacturaFileDto pdfFile = pdfService.generateInvoicePDF(facturaContext);
		facturaContext.getFacturaFilesDto().add(pdfFile);
		if ((facturaContext.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PUE.name())
				|| (facturaContext.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PPD.name()) && facturaContext
						.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())))
				&& facturaContext.getFacturaDto().getLineaEmisor().equals("A")
				&& facturaContext.getFacturaDto().getLineaRemitente().equals("CLIENTE")) {
			devolucionService.generarDevoluciones(facturaContext.getFacturaDto());
		}
		if (facturaContext.getFacturaDto().getLineaRemitente().equals("CLIENTE")) {
			try {
				timbradoExecutorService.sentEmail(facturaContext, TipoEmail.SEMEL_JACK);
			} catch (InvoiceManagerException e) {
				timbradoExecutorService.sentEmail(facturaContext, TipoEmail.GMAIL);
			}
		}
		return facturaContext;
	}

	public FacturaContext cancelarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		FacturaContext facturaContext = timbradoBuilderService.buildFacturaContextCancelado(facturaDto, folio);
		validator.validateTimbrado(facturaDto, folio);
		if (facturaDto.getTipoDocumento().equals("Factura") || facturaDto.getMetodoPago().equals("PPD")) {
			for (CfdiPagoDto cfdiPagoDto : cfdiService.getCfdiPagosByFolio(facturaDto.getFolio())) {
				FacturaDto complemento = getFacturaByIdCfdi(cfdiPagoDto.getCfdi().getId());
				if (complemento.getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor())) {
					cancelarFactura(complemento.getFolio(), complemento);
				} else {
					if (!complemento.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
						complemento.setStatusFactura(FacturaStatusEnum.CANCELADA.getValor());
						updateFactura(complemento.getIdCfdi(), complemento);
					}
				}
			}
		}

		timbradoServiceEvaluator.facturaCancelacionValidation(facturaContext);
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.cancelarFactura(facturaContext);
			break;
		case FACTURACION_MODERNA:
			facturacionModernaExecutor.cancelarFactura(facturaContext);
			break;
		case NTLINK:
			ntinkExecutorService.cancelarFactura(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.BAD_REQUEST.value());
		}
		timbradoExecutorService.updateCanceladoValues(facturaContext);
		return facturaContext;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public FacturaDto generateComplemento(List<FacturaDto> facturas, PagoDto pagoPpd) throws InvoiceManagerException {
		if (facturas.stream().anyMatch(a -> !a.getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor()))) {
			throw new InvoiceManagerException("Una factura no esta timbrada", "Una factura no esta timbrada",
					HttpStatus.BAD_REQUEST.value());
		}
		Optional<FacturaDto> primerfactura = facturas.stream().findFirst();
		if (primerfactura.isPresent()) {
			FacturaDto factura = getFacturaByFolio(primerfactura.get().getFolio());
			factura.setPackFacturacion(primerfactura.get().getPackFacturacion());
			FacturaContext factContext = facturaBuilderService.buildFacturaContextPagoPpdCreation(pagoPpd, factura,
					factura.getFolio());
			CfdiDto cfdiDto = facturaBuilderService.buildFacturaComplementoCreation(factContext);
			FacturaDto complemento = facturaBuilderService.buildFacturaDtoPagoPpdCreation(factura, pagoPpd);
			List<CfdiPagoDto> cfdiPagos = facturaBuilderService.buildFacturaComplementoPagos(factura, pagoPpd,
					facturas);
			cfdiDto.setComplemento(new ComplementoDto());
			cfdiDto.getComplemento().setPagos(cfdiPagos);
			complemento.setCfdi(cfdiDto);
			facturaDefaultValues.assignaDefaultsComplemento(complemento);
			CfdiDto cfdi = cfdiService.insertNewCfdi(complemento.getCfdi());
			Factura fact = mapper.getEntityFromFacturaDto(complemento);
			fact.setIdCfdi(cfdi.getId());
			for (FacturaDto dto : facturas) {
				Optional<PagoFacturaDto> pfDto = pagoPpd.getFacturas().stream()
						.filter(a -> a.getFolio().equals(dto.getFolio())).findFirst();
				updateTotalAndSaldoFactura(dto.getIdCfdi(), dto.getTotal(),
						dto.getSaldoPendiente().subtract(pfDto.get().getMonto()));
			}
			return mapper.getFacturaDtoFromEntity(repository.save(fact));
		} else {
			throw new InvoiceManagerException("Debe tener por lo menos un pago", "No asigno el pago a una factura",
					HttpStatus.BAD_REQUEST.value());
		}
	}

	public void recreatePdf(CfdiDto dto) {
		FacturaDto factura = mapper.getFacturaDtoFromEntity(repository.findByFolio(dto.getFolio())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", dto.getFolio()))));
		factura.setCfdi(dto);
		pdfService.generateInvoicePDF(factura, null);
	}

	// RENVIAR CORREOS
	public FacturaContext renviarCorreo(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		facturaDto = getFacturaByFolio(folio);
		FacturaContext facturaContext = facturaBuilderService.buildEmailContext(folio, facturaDto);
		timbradoExecutorService.sentEmail(facturaContext, TipoEmail.GMAIL);
		return facturaContext;
	}

}
