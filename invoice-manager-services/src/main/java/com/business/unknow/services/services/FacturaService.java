package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Empresa;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.facturas.PagoRepository;
import com.business.unknow.services.services.evaluations.CfdiEvaluatorService;
import com.business.unknow.services.services.evaluations.FacturaEvaluatorService;
import com.business.unknow.services.services.evaluations.PagoEvaluatorService;
import com.business.unknow.services.services.evaluations.TimbradoEvaluatorService;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private TimbradoEvaluatorService timbradoServiceEvaluator;

	@Autowired
	private FacturaEvaluatorService facturaServiceEvaluator;

	@Autowired
	private PagoEvaluatorService pagoEvaluatorService;

	@Autowired
	private CfdiEvaluatorService cfdiEvaluatorService;

	private FacturaValidator validator = new FacturaValidator();

	public List<FacturaDto> getComplementos(String folioPadre) {
		return mapper.getFacturaDtosFromEntities(repository.findComplementosByFolioPadre(folioPadre));
	}

	public Page<FacturaDto> getFacturasByParametros(Optional<String> rfcEmisor, Optional<String> rfcRemitente,
			Optional<String> folio, String status, Date since, Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(0, 10));
		} else if (rfcEmisor.isPresent()) {
			result = repository.findByRfcEmisorWithOtherParams(String.format("%%%s%%", rfcEmisor.get()),
					String.format("%%%s%%", status), start, end, PageRequest.of(page, size));
		} else if (rfcRemitente.isPresent()) {
			result = repository.findByRfcRemitenteWithOtherParams(String.format("%%%s%%", rfcRemitente.get()),
					String.format("%%%s%%", status), start, end, PageRequest.of(page, size));
		} else {
			result = repository.findAllWithStatusAndDates(String.format("%%%s%%", status), start, end,
					PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public FacturaDto getfacturaByFolio(String folio) {
		FacturaDto dto = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		Optional<Cfdi> cfdi = cfdiRepository.findByFolio(folio);
		dto.setCfdi(cfdi.isPresent() ? cfdiMapper.getCfdiDtoFromEntity(cfdi.get()) : null);
		return dto;
	}

	public CfdiDto insertNewCfdi(String folio, CfdiDto cfdi) {
		return cfdiEvaluatorService.insertNewCfdi(folio, cfdi);
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public FacturaDto insertNewFacturaWithDetail(FacturaDto dto) throws InvoiceManagerException {
		validator.validatePostFacturaWithDetail(dto);
		Empresa empresa = empresaRepository.findByRfc(dto.getRfcEmisor())
				.orElseThrow(() -> new InvoiceManagerException("Error al crear factura", "El emisor no exite",
						Constants.BAD_REQUEST));
		Client client = clientRepository.findByRfc(dto.getRfcRemitente())
				.orElseThrow(() -> new InvoiceManagerException("Error al crear factura", "El receptor no exite",
						Constants.BAD_REQUEST));
		FacturaContext facturaContext = new FacturaContextBuilder().setFacturaDto(dto)
				.setEmpresaDto(empresaMapper.getEmpresaDtoFromEntity(empresa))
				.setClientDto(clientMapper.getClientDtoFromEntity(client)).build();
		return facturaServiceEvaluator.facturaEvaluation(facturaContext).getFacturaDto();
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

	public CfdiDto getFacturaCdfi(String folio) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		return cfdiMapper.getCfdiDtoFromEntity(entity);
	}

	public CfdiDto updateFacturaCfdi(String folio, Integer id, CfdiDto dto) throws InvoiceManagerException {
		validator.validatePostCfdi(dto, folio);
		cfdiRepository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
				String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto)));
	}

	public void deleteFacturaCfdi(String folio, Integer id) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		cfdiRepository.delete(entity);
	}

	public List<PagoDto> getPagos(String folio) {
		return mapper.getPagosDtoFromEntity(pagoRepository.findByFolioPadre(folio));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto insertNewPago(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		return pagoEvaluatorService.validatePagoCreation(folio,pagoDto);
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(PagoDto pago, Integer id) throws InvoiceManagerException {

		Pago entity = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));

		if (entity.getUltimoUsuario().equalsIgnoreCase(pago.getUltimoUsuario())
				|| entity.getCreateUser().equalsIgnoreCase(pago.getUltimoUsuario())) {
			throw new InvoiceManagerException("Mismo usuario no puede actualizar el pago",
					"La actualizacion del pago no puede ser realizada por el mismo usuario de manera consecutiva",
					HttpStatus.CONFLICT.value());
		}
		// Bank, Document, payForm and pay type can't be updated
		entity.setComentarioPago(pago.getComentarioPago());
		entity.setRevision1(pago.getRevision1());
		entity.setRevision2(pago.getRevision2());
		entity.setUltimoUsuario(pago.getUltimoUsuario());
		entity.setStatusPago(pago.getStatusPago());

		if (pago.getStatusPago().equals("RECHAZADO")) {
			Factura factura = repository.findByFolio(pago.getFolio())
					.orElseThrow(() -> new InvoiceManagerException("El pago no tiene  asignada una factura",
							"Es necesario revisar la integridad de los pagos", HttpStatus.CONFLICT.value()));
			factura.setStatusFactura(FacturaStatusEnum.RECHAZO_TESORERIA.getValor());
			repository.save(factura);
		} else {
			if (pago.getRevision1() && pago.getRevision2()) {
				entity.setStatusPago("ACEPTADO");
				Factura factura = repository.findByFolio(pago.getFolio())
						.orElseThrow(() -> new InvoiceManagerException("El pago no tiene  asignada una factura",
								"Es necesario revisar la integridad de los pagos", HttpStatus.CONFLICT.value()));
				factura.setStatusPago(PagoStatusEnum.PAGADA.getValor());
				factura.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
				repository.save(factura);
			}
		}
		return mapper.getPagoDtoFromEntity(pagoRepository.save(entity));

	}

	public void deletePago(Integer id) {
		Pago pago = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		// TODO implement logic to delete complements linked to the payments
		pagoRepository.delete(pago);
	}

	public FacturaContext timbrarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		return timbradoServiceEvaluator.facturaTimbradoValidation(facturaDto, folio);
	}

	public FacturaContext cancelarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		Optional<Factura> folioPadreEntity = repository.findByFolio(facturaDto.getFolioPadre());
		Factura folioEnity = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Folio not found",
						String.format("Folio with the name %s not found", facturaDto.getFolio()),
						HttpStatus.NOT_FOUND.value()));
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								String.format("La empresa con el rfc no existe", facturaDto.getRfcEmisor()))));
		FacturaContext facturaContext = new FacturaContextBuilder()
				.setFacturaDto(mapper.getFacturaDtoFromEntity(folioEnity))
				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio))).setEmpresaDto(empresaDto)
				.setFacturaPadreDto(
						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
				.setTipoFactura(facturaDto.getMetodoPago()).setTipoDocumento(facturaDto.getTipoDocumento()).build();
		return timbradoServiceEvaluator.facturaCancelacionValidation(facturaContext);
	}

}
