package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.services.entities.cfdi.Concepto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface ConceptoMapper {

	ConceptoDto getClientDtoFromEntity(Concepto entity);
	Concepto getEntityFromClientDto(ConceptoDto dto);
	List<ClientDto> getClientDtosFromEntities(List<Concepto> entities);
	List<Concepto> getEntitiesFromClientDtos(List<ConceptoDto> dto);
	
}
