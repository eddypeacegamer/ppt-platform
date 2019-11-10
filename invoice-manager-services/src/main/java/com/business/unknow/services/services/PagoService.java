/**
 * 
 */
package com.business.unknow.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.PagoDto;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.facturas.PagoRepository;

/**
 *@author ralfdemoledor
 *
 */
@Service
public class PagoService {
	
	@Autowired
	private PagoRepository repository;
	
	@Autowired
	private PagoMapper mapper;
	
	
	public Page<PagoDto> getAllPagos(int page,int size){
		
		Page<Pago> result = repository.findAll(PageRequest.of(page, size));
		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

}
