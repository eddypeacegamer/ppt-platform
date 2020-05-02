package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.services.ContribuyenteDto;
import com.business.unknow.services.entities.Contribuyente;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface ContribuyenteMapper {

	ContribuyenteDto getContribuyenteToFromEntity(Contribuyente entity);
	Contribuyente getEntityFromContribuyenteDto(ContribuyenteDto dto);
	List<ContribuyenteDto> getContribuyenteDtosFromEntities(List<Contribuyente> entities);
	List<Contribuyente> getEntitiesFromContribuyenteDtos(List<ContribuyenteDto> dto);
}
