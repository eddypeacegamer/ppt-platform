package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.OLD.FacturaDto;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.entities.factura.FacturaFile;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.FacturaFileRepository;
import com.business.unknow.services.repositories.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private FacturaFileRepository facturaFileRepository;

	@Autowired
	private FacturaMapper mapper;

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

	public FacturaDto insertNewFactura(FacturaDto factura) {
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

	public FacturaDto updateFacturaInfo(FacturaDto factura, String folio) {
		Factura fact = repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El folio %s de la factura no existe", folio)));
		fact.setUuid(factura.getUuid());
		return mapper.getFacturaDtoFromEntity(repository.save(fact));
	}
}
