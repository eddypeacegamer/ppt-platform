package com.business.unknow.services.mapper;

import org.mapstruct.Mapper;

import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.services.entities.cfdi.Cfdi;

@Mapper
public interface CfdiMapper {

	CfdiDto getCfdiDtoFromEntity(Cfdi entity);
	Cfdi getEntityFromCfdiDto(CfdiDto dto);
}
