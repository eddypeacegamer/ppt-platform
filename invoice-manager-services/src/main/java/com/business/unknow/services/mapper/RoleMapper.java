package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.RoleDto;
import com.business.unknow.services.entities.Role;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface RoleMapper {

	RoleDto getRoleDtoFromentity(Role entity);

	Role getEntityFromRoleDto(RoleDto dto);

	List<RoleDto> getRoleDtosFromEntities(List<Role> entities);

	List<Role> getEntitiesFromRoleDtos(List<RoleDto> dto);

}
