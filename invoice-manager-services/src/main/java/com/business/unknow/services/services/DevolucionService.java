/**
 * 
 */
package com.business.unknow.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.repositories.DevolucionRepository;

/**
 *@author ralfdemoledor
 *
 */
@Service
public class DevolucionService {
	
	
	@Autowired
	private DevolucionRepository repository;
	
	@Autowired
	private DevolucionMapper mapper;
	
	public Page<DevolucionDto> getAllDevoluciones(int page,int size){
		
		Page<Devolucion> result = repository.findAll(PageRequest.of(page, size));
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

}
