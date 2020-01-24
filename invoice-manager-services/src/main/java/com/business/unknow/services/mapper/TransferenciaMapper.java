package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.services.TransferenciaDto;
import com.business.unknow.services.entities.Transferencia;

@Mapper
public interface TransferenciaMapper {
	
	TransferenciaDto getTransferenciaDtoFromEntity(Transferencia transferencia);
	List<TransferenciaDto> getTransferenciasDtoFromEntities(List<Transferencia> transferencias);
	
	Transferencia getEntityFromTransferenciaDto(TransferenciaDto transferencia);
	List<Transferencia> getEntitiesFromTransferenciasDto(List<TransferenciaDto> transferencia);

}
