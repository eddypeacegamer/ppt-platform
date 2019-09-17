package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Empresa;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	private EmpresaMapper mapper;

	public Page<EmpresaDto> getAllEmpresas(int page, int size) {
		Page<Empresa> result = repository.findAll(PageRequest.of(page, size));
		return new PageImpl<EmpresaDto>(mapper.getEmpresaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public EmpresaDto getEmpresaByName(String name) throws InvoiceManagerException {
		Optional<Empresa> result = repository.findByName(name);
		if (result.isPresent()) {
			return mapper.getEmpresaDtoFromEntity(result.get());
		} else {
			throw new InvoiceManagerException("Empresa not found",
					String.format("La empresa con el nombre %s no existe", name), HttpStatus.NOT_FOUND.value());
		}
	}

}
