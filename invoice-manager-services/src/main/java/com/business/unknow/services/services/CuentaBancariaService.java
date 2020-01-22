package com.business.unknow.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.mapper.CuentaBancariaMapper;
import com.business.unknow.services.repositories.CuentaBancariaRepository;

@Service
public class CuentaBancariaService {

	@Autowired
	private CuentaBancariaRepository repository;

	@Autowired
	private CuentaBancariaMapper mapper;

	public List<CuentaBancariaDto> getCuentasBancariasByfilters(Optional<String> banco, Optional<String> empresa) {
		if (banco.isPresent() && empresa.isPresent()) {
			return mapper
					.getCuentaBancariaDtosFromEntities(repository.findByEmpresaAndBanco(empresa.get(), banco.get()));
		} else if (!banco.isPresent() && empresa.isPresent()) {
			return mapper.getCuentaBancariaDtosFromEntities(repository.findByEmpresa(empresa.get()));
		} else if (!empresa.isPresent() && banco.isPresent()) {
			return mapper.getCuentaBancariaDtosFromEntities(repository.findByBanco(banco.get()));
		} else {
			return mapper.getCuentaBancariaDtosFromEntities(repository.findAll());
		}
	}
}
