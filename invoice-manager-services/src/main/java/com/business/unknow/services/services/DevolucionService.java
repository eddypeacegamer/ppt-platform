/**
 * 
 */
package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;

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
	
	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType,Optional<String> idReceptor,Optional<String> statusPay, int page,int size){
		Page<Devolucion> result =null;
		if(!receptorType.isPresent() && !idReceptor.isPresent() && !statusPay.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		}else {
			result = repository.findDevolucionesByParams(receptorType.get(),idReceptor.get(),String.format("%%%s%%", statusPay.get()),PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	
	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		devolucion.setStatusPago("VALIDACION");
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

}
