package com.business.unknow.services.services;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.entities.CuentaBancaria;
import com.business.unknow.services.mapper.CuentaBancariaMapper;
import com.business.unknow.services.repositories.CuentaBancariaRepository;

@Service
public class CuentaBancariaService {

	@Autowired
	private CuentaBancariaRepository repository;

	@Autowired
	private CuentaBancariaMapper mapper;

	public Page<CuentaBancariaDto> getCuentasBancariasByfilters(String banco, String empresa, String clabe, Date since,
			Date to, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<CuentaBancaria> result = repository.findCuentasByFilterParams(String.format("%%%s%%", banco),
				String.format("%%%s%%", empresa), String.format("%%%s%%", clabe), start, end,
				PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));

		return new PageImpl<>(mapper.getCuentaBancariaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	
	public List<CuentaBancariaDto> getCuentasPorEmpresa(String empresa){
		return mapper.getCuentaBancariaDtosFromEntities(repository.findByEmpresa(empresa));
	}
}
