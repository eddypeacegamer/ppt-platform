package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.ContribuyenteDto;
import com.business.unknow.services.entities.Contribuyente;

@Mapper
public interface ContribuyenteMapper {

	ContribuyenteDto getContribuyenteToFromEntity(Contribuyente entity);
	Contribuyente getEntityFromContribuyenteDto(ContribuyenteDto dto);
	List<ContribuyenteDto> getContribuyenteDtosFromEntities(List<Contribuyente> entities);
	List<Contribuyente> getEntitiesFromContribuyenteDtos(List<ContribuyenteDto> dto);
}
