package com.business.unknow.services.mapper;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.services.entities.cfdi.Cfdi;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface CfdiMapper {

	CfdiDto getCfdiDtoFromEntity(Cfdi entity);
	Cfdi getEntityFromCfdiDto(CfdiDto dto);
}
