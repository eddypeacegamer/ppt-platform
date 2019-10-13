package com.business.unknow.services.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.commons.util.FacturaCalculator;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.PagoDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.entities.factura.FacturaFile;
import com.business.unknow.services.entities.factura.Pago;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.CfdiRepository;
import com.business.unknow.services.repositories.FacturaFileRepository;
import com.business.unknow.services.repositories.FacturaRepository;
import com.business.unknow.services.repositories.PagoRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private FacturaFileRepository facturaFileRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	private FacturaValidator validator= new FacturaValidator();

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

	public CfdiDto getFacturaCdfi(String folio) {
		Cfdi entity = cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El cfdi con el folio %s no existe", folio)));
		return cfdiMapper.getCfdiDtoFromEntity(entity);
	}

	public FacturaDto insertNewFactura(FacturaDto dto) throws InvoiceManagerException {
		validator.validatePostFactura(dto);
		dto.setFechaCreacion(new Date());
		dto.setFechaActualizacion(new Date());
		dto.setFolio(FacturaCalculator.folioEncrypt(dto));
		return mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(dto)));
	}

	public FacturaDto updateFactura(FacturaDto factura, String folio) throws InvoiceManagerException {
		Factura entity = repository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		entity.setFormaPago(factura.getFormaPago());
		entity.setMetodoPago(factura.getMetodoPago());
		entity.setNotas(factura.getNotas());
		entity.setRfcEmisor(factura.getRfcEmisor());
		entity.setStatusFactura(mapper.getEntityFromStatusFacturaDto(factura.getStatusFactura()));
		entity.setStatusDetail(factura.getStatusDetail());
		return mapper.getFacturaDtoFromEntity(repository.save(entity));
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

	public List<PagoDto> getPagos(String folio) throws InvoiceManagerException {
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
		entity.setTipoDocumento(pago.getTipoDocumento());
		entity.setTipoPago(pago.getTipoPago());
		return mapper.getPagoDtoFromEntity(pagoRepository.save(entity));
	}

	public void deletePago(Integer id) {
		Pago pago = pagoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El pago con el id %d no existe", id)));
		pagoRepository.delete(pago);
	}

}
