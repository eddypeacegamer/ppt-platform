package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.services.ClientDto;
import com.business.unknow.services.entities.cfdi.Concepto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface ConceptoMapper {

	ConceptoDto getConceptoDtoFromEntity(Concepto entity);
	Concepto getEntityFromConceptoDto(ConceptoDto dto);
	List<ClientDto> getconceptoDtosFromEntities(List<Concepto> entities);
	List<Concepto> getEntitiesFromConceptoDtos(List<ConceptoDto> dto);
	
}
