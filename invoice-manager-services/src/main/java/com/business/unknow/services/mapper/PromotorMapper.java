package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.PromotorDto;
import com.business.unknow.services.entities.Promotor;

/**
 * @author eej000f
 *
 */
@Mapper
public interface PromotorMapper {

	PromotorDto getRoleDtoFromentity(Promotor entity);

	Promotor getEntityFromRoleDto(PromotorDto dto);

	List<PromotorDto> getRoleDtosFromEntities(List<Promotor> entities);

	List<Promotor> getEntitiesFromRoleDtos(List<PromotorDto> dto);
}
