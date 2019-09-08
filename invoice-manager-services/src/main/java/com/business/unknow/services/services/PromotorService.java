package com.business.unknow.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.PromotorDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Promotor;
import com.business.unknow.services.mapper.PromotorMapper;
import com.business.unknow.services.repositories.PromotorRepository;

@Service
public class PromotorService {

	@Autowired
	private PromotorRepository repository;

	@Autowired
	private PromotorMapper mapper;

	public List<PromotorDto> getPromotors() {
		return mapper.getPromotorDtosFromEntities(repository.findAll());
	}

	public PromotorDto getPromotorsByName(String name) throws InvoiceManagerException {
		Optional<Promotor> promotor = repository.findByName(name);
		if (promotor.isPresent()) {
			return mapper.getPromotortoFromentity(promotor.get());
		} else {
			throw new InvoiceManagerException("Promotor not found", String.format("The promotor %s not found", name),
					HttpStatus.NOT_FOUND.value());
		}
	}

}
