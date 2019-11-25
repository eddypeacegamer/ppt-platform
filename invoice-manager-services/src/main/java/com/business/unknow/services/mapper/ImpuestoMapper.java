package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
import com.business.unknow.services.entities.cfdi.Impuesto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface ImpuestoMapper {

	ImpuestoDto getClientDtoFromEntity(Impuesto entity);
	Impuesto getEntityFromClientDto(ImpuestoDto dto);
	List<ImpuestoDto> getClientDtosFromEntities(List<Impuesto> entities);
	List<Impuesto> getEntitiesFromClientDtos(List<ImpuestoDto> dto);
}
