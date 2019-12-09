package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.services.entities.Empresa;

/**
 * @author eej000f
 *
 */
@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface EmpresaMapper {

	EmpresaDto getEmpresaDtoFromEntity(Empresa entity);

	Empresa getEntityFromEmpresaDto(EmpresaDto dto);

	List<EmpresaDto> getEmpresaDtosFromEntities(List<Empresa> entities);

	List<Empresa> getEntitiesFromEmpresaDtos(List<EmpresaDto> dto);

}
