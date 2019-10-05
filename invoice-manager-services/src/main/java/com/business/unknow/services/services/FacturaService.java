package com.business.unknow.services.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.PagoDto;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.entities.factura.FacturaFile;
import com.business.unknow.services.entities.factura.Pago;
import com.business.unknow.services.mapper.FacturaMapper;
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
	private FacturaMapper mapper;

	public Page<FacturaDto> getFacturasByParametros(Optional<String> rfcEmisor, Optional<String> rfcRemitente,
			Optional<String> folio, Optional<String> uuid, int page, int size) {
		Page<Factura> result;
		if (!rfcEmisor.isPresent() && !rfcRemitente.isPresent() && !folio.isPresent() && !uuid.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else if (rfcEmisor.isPresent()) {
			result = repository.findByRfcEmisorIgnoreCaseContaining(rfcEmisor.get(), PageRequest.of(page, size));
		} else if (rfcRemitente.isPresent()) {
			result = repository.findByRfcRemitenteIgnoreCaseContaining(rfcRemitente.get(), PageRequest.of(page, size));
		} else if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(page, size));
		} else {
			result = repository.findByUuidIgnoreCaseContaining(uuid.get(), PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public FacturaDto insertNewFactura(FacturaDto factura) {
		return mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(factura)));
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
		return mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(factura)));
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

	public List<PagoDto> getPagos(String folio) throws InvoiceManagerException {
		List<Pago> archivos = pagoRepository.findByFolio(folio);
		if (archivos.isEmpty()) {
			return new ArrayList<>();
		} else {
			return mapper.getPagosDtoFromEntity(archivos);
		}
	}

}
