package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.services.entities.cfdi.Concepto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface ConceptoMapper {

	ConceptoDto getConceptoDtoFromEntity(Concepto entity);
	Concepto getEntityFromConceptoDto(ConceptoDto dto);
	List<ClientDto> getconceptoDtosFromEntities(List<Concepto> entities);
	List<Concepto> getEntitiesFromConceptoDtos(List<ConceptoDto> dto);
	
}
