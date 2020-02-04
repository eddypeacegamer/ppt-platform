package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.factura.cfdi.components.RetencionDto;
import com.business.unknow.services.entities.cfdi.Retencion;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface RetencionMapper {

	RetencionDto getClientDtoFromEntity(Retencion entity);
	Retencion getEntityFromClientDto(RetencionDto dto);
	List<RetencionDto> getClientDtosFromEntities(List<Retencion> entities);
	List<Retencion> getEntitiesFromClientDtos(List<RetencionDto> dto);
}
