package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

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

	public FacturaDto updateFacturaInfo(FacturaDto factura, String folio) {
		Factura fact = repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El folio %s de la factura no existe", folio)));
		fact.setMoneda(factura.getMoneda());
		fact.setMoneda(factura.getMoneda());
		return mapper.getFacturaDtoFromEntity(repository.save(fact));
	}
}
