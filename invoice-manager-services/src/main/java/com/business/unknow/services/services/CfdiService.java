/**
 * 
 */
package com.business.unknow.services.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.repositories.facturas.CfdiRepository;

/**
 * @author hha0009
 *
 */
public class CfdiService {
	
	
	@Autowired
	private CfdiRepository repository;
	
	@Autowired
	protected CfdiMapper mapper;
	
	
	public CfdiDto insertCfdi(CfdiDto cfdi) {
		
		
		
		
		return mapper.getCfdiDtoFromEntity(repository.save(mapper.getEntityFromCfdiDto(cfdi)));
	}
	


}
