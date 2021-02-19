package com.business.unknow.services.services;

import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.entities.CuentaBancaria;
import com.business.unknow.services.mapper.CuentaBancariaMapper;
import com.business.unknow.services.repositories.CuentaBancariaRepository;
import com.business.unknow.services.repositories.CuentasBanncariasRepositoryDto;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuentaBancariaService {

	@Autowired
	private CuentaBancariaRepository repository;

	@Autowired
	private CuentaBancariaMapper mapper;
	
	@Autowired
	private CuentasBanncariasRepositoryDto jdbcrepo;

	
	public Page<CuentaBancariaDto> getCuentasBancariasByfilters(String banco, String empresa, String clabe, String cuenta, Date since,
			Date to, int page, int size) {
			Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
			Date end = (to == null) ? new Date() : to;
			int offset = ((size * (page+1)) - size) ;	
			List<CuentaBancariaDto> resultqry = jdbcrepo.resultQry(banco,empresa,clabe,cuenta,start,end,offset,size);	
			Page<CuentaBancariaDto> result = new PageImpl<CuentaBancariaDto>(resultqry); 
			Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").descending());
			int pages = resultqry.size() > 0 ? resultqry.get(0).getTotal() :0;
		return new PageImpl<>(result.getContent(), pageable,pages);
	}
	
	public List<CuentaBancariaDto> getCuentasPorEmpresa(String empresa){
		return mapper.getCuentaBancariaDtosFromEntities(repository.findByEmpresa(empresa));
	}
	
	public CuentaBancariaDto infoCuentaBancaria(String empresa,String cuenta){
		return mapper.getCuentaBancariaToFromEntity(repository.findByEmpresaAndCuenta(empresa,cuenta).get());
	}
	
	public CuentaBancariaDto createCuentaBancaria(CuentaBancariaDto cuentaDto) {
		Optional<CuentaBancaria> entity = repository.findByEmpresaAndCuenta(cuentaDto.getEmpresa(),cuentaDto.getCuenta());
		if (entity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("Esta Empresa con esta cuenta ya existe %s", cuentaDto.getCuenta()));
		} else {
			
			CuentaBancaria cuentaBancaria = repository.save(mapper.getEntityFromCuentaBancariaDto(cuentaDto));
			return mapper.getCuentaBancariaToFromEntity(cuentaBancaria);
		}
	}
	
	public CuentaBancariaDto updateCuentaBancaria(Integer cuentaId,CuentaBancariaDto cuentaBancariaDto) {
		CuentaBancaria entity = repository.findById(cuentaBancariaDto.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,
				String.format("Esta Empresa con esta cuenta ya existe %s", cuentaBancariaDto.getCuenta())));
		entity.setBanco(cuentaBancariaDto.getBanco());
		entity.setEmpresa(cuentaBancariaDto.getEmpresa());
		entity.setCuenta(cuentaBancariaDto.getCuenta());
		entity.setClabe(cuentaBancariaDto.getClabe());
		
		return mapper.getCuentaBancariaToFromEntity(repository.save(entity));
	}
	
	public void deleteCuentaBancaria(Integer id) {
		CuentaBancaria entity = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Esta cuenta no existe %d", id)));
		repository.delete(entity);
	}

	
}
