package com.business.unknow.services.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.commons.util.FacturaCalculator;
import com.business.unknow.commons.util.FacturaDefaultValues;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.PagoDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.entities.factura.FacturaFile;
import com.business.unknow.services.entities.factura.Pago;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.ConceptoMapper;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.mapper.ImpuestoMapper;
import com.business.unknow.services.repositories.CfdiRepository;
import com.business.unknow.services.repositories.ConceptoRepository;
import com.business.unknow.services.repositories.FacturaFileRepository;
import com.business.unknow.services.repositories.FacturaRepository;
import com.business.unknow.services.repositories.ImpuestoRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.services.evaluations.FacturaServiceEvaluator;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private FacturaFileRepository facturaFileRepository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private ConceptoRepository conceptoRepository;

	@Autowired
	private ImpuestoRepository impuestoRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private ConceptoMapper conceptoMapper;

	@Autowired
	private ImpuestoMapper impuestoMapper;

	@Autowired
	private FacturaServiceEvaluator facturaServiceEvaluation;

	private FacturaValidator validator = new FacturaValidator();

	public List<FacturaDto> getComplementos(String folioPadre) {
		return mapper.getFacturaDtosFromEntities(repository.findComplementosByFolioPadre(folioPadre));
	}

	public Page<FacturaDto> getFacturasByParametros(Optional<String> rfcEmisor, Optional<String> rfcRemitente,
			Optional<String> folio, Optional<String> statusValidacion, Optional<String> statusPago, int page,
			int size) {
		Page<Factura> result;
		if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(page, size));
		} else if (rfcEmisor.isPresent()) {
			result = repository.findByRfcEmisorWithOtherParams(String.format("%%%s%%", rfcEmisor.get()),
					String.format("%%%s%%", statusValidacion.orElse("")),
					String.format("%%%s%%", statusPago.orElse("")), PageRequest.of(page, size));
		} else if (rfcRemitente.isPresent()) {
			result = repository.findByRfcRemitenteWithOtherParams(String.format("%%%s%%", rfcRemitente.get()),
					String.format("%%%s%%", statusValidacion.orElse("")),
					String.format("%%%s%%", statusPago.orElse("")), PageRequest.of(page, size));
		} else {
			result = repository.findAll(PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public FacturaDto getfacturaByFolio(String folio) {
		FacturaDto dto = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(
				cfdiRepository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		dto.setCfdi(cfdiDto);
		return dto;
	}

	public FacturaDto insertNewFactura(FacturaDto dto) throws InvoiceManagerException {
		validator.validatePostFactura(dto);
		dto.setFechaCreacion(new Date());
		dto.setFechaActualizacion(new Date());
		dto.setFolio(FacturaCalculator.folioEncrypt(dto));
		return mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(dto)));
	}

	public FacturaDto insertNewComplemento(FacturaDto dto, String folio) throws InvoiceManagerException {
		dto.setFolio(FacturaCalculator.folioEncrypt(dto));
		validator.validatePosComplementoDto(dto, folio);
		Optional<Factura> factura = repository.findByFolio(dto.getFolioPadre());
		if (factura.isPresent()) {
			FacturaContext facturaContext = new FacturaContextBuilder()
					.setFacturaDto(mapper.getFacturaDtoFromEntity(factura.get()))
					.setTipoFactura(factura.get().getMetodoPago()).setComlpemento(dto)
					.setComplementos(
							mapper.getFacturaDtosFromEntities(repository.findByFolioPadre(dto.getFolioPadre())))
					.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(dto.getFolioPadre())))
					.setComplementos(dto).build();
			facturaServiceEvaluation.facruraContexctValidation(facturaContext);
			return mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(dto)));
		} else {
			throw new InvoiceManagerException("Error al crear Complemento", "La factura padre no existe",
					Constants.BAD_REQUEST);
		}
	}

	@Transactional(rollbackOn = InvoiceManagerException.class)
	public FacturaDto insertNewFacturaWithDetail(FacturaDto dto) throws InvoiceManagerException {
		validator.validatePostFacturaWithDetail(dto);
		FacturaDefaultValues.assignaDefaultsFactura(dto);
		FacturaDto facturaDto = mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(dto)));
		dto.setCfdi(insertNewCfdi(dto.getFolio(), dto.getCfdi()));
		return facturaDto;
	}

	public FacturaDto updateFactura(FacturaDto factura, String folio) throws InvoiceManagerException {
		Factura entity = repository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		entity.setFormaPago(factura.getFormaPago());
		entity.setMetodoPago(factura.getMetodoPago());
		entity.setNotas(factura.getNotas());
		entity.setRfcEmisor(factura.getRfcEmisor());
		entity.setStatusFactura(factura.getStatusFactura());
		entity.setStatusPago(factura.getStatusPago());
		entity.setStatusDevolucion(factura.getStatusDevolucion());
		entity.setStatusDetail(factura.getStatusDetail());
		return mapper.getFacturaDtoFromEntity(repository.save(entity));
	}

	public CfdiDto getFacturaCdfi(String folio) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return cfdiMapper.getCfdiDtoFromEntity(entity);
	}

	public CfdiDto updateFacturaCfdi(String folio, Integer id, CfdiDto dto) throws InvoiceManagerException {
		validator.validatePostCfdi(dto, folio);
		Cfdi entity = cfdiRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.save(entity));
	}

	public void deleteFacturaCfdi(String folio, Integer id) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		cfdiRepository.delete(entity);
	}

	public CfdiDto insertNewCfdi(String folio, CfdiDto dto) throws InvoiceManagerException {

		Cfdi cfdiTemp = cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(cfdiTemp);
		for (ConceptoDto conceptoDto : dto.getConceptos()) {
			Concepto tempConcepto = conceptoMapper.getEntityFromClientDto(conceptoDto);
			tempConcepto.setCfdi(cfdiTemp);
			tempConcepto = conceptoRepository.save(tempConcepto);
			cfdiDto.getConceptos().add(conceptoMapper.getClientDtoFromEntity(tempConcepto));
			List<ImpuestoDto> impuestos = new ArrayList<>();
			for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
				Impuesto impuestoTemp = impuestoMapper.getEntityFromClientDto(impuestoDto);
				impuestoTemp.setConcepto(tempConcepto);
				impuestos.add(impuestoMapper.getClientDtoFromEntity(impuestoRepository.save(impuestoTemp)));
			}
			conceptoDto.setImpuestos(impuestos);
		}
		return cfdiDto;
	}

	public FacturaFileDto getFacturaFile(String folio) throws InvoiceManagerException {
		Optional<FacturaFile> facturaFile = facturaFileRepository.findByFolio(folio);
		if (facturaFile.isPresent()) {
			return mapper.getFacturaFileDtoFromEntity(facturaFile.get());
		} else {
			throw new InvoiceManagerException("Folio not found",
					String.format("Folio with the name %s not found", folio), HttpStatus.NOT_FOUND.value());
		}
	}

	public FacturaFileDto insertNewFacturaFile(FacturaFileDto factura) {
		return mapper
				.getFacturaFileDtoFromEntity(facturaFileRepository.save(mapper.getEntityFromFacturaFileDto(factura)));
	}

	public void deleteFacturaFile(String folio) {
		FacturaFile entity = facturaFileRepository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		facturaFileRepository.delete(entity);
	}

	public FacturaFileDto updateFacturaFile(FacturaFileDto factura, String folio) throws InvoiceManagerException {
		FacturaFile entity = facturaFileRepository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		entity.setPdf(factura.getPdf());
		entity.setXml(factura.getXml());
		entity.setQr(factura.getQr());
		return mapper.getFacturaFileDtoFromEntity(facturaFileRepository.save(entity));
	}

	public List<PagoDto> getPagos(String folio) {
		List<Pago> archivos = new ArrayList<>();
		archivos.addAll(pagoRepository.findByFolio(folio));
		return mapper.getPagosDtoFromEntity(archivos);
	}

	public PagoDto insertNewPago(PagoDto pago) {
		return mapper.getPagoDtoFromEntity(pagoRepository.save(mapper.getEntityFromPagoDto(pago)));
	}

	public PagoDto updatePago(PagoDto pago, Integer id) throws InvoiceManagerException {
		Pago entity = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		entity.setDocumento(pago.getDocumento());
		entity.setBanco(pago.getBanco());
		entity.setMoneda(pago.getMoneda());
		entity.setTipoPago(pago.getTipoPago());
		return mapper.getPagoDtoFromEntity(pagoRepository.save(entity));
	}

	public void deletePago(Integer id) {
		Pago pago = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		pagoRepository.delete(pago);
	}

}
