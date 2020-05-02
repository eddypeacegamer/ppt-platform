package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.dto.services.UserDto;
import com.business.unknow.services.entities.User;

@Mapper
public interface UserMapper {
	@Mappings({ @Mapping(target = "roles", ignore = true), @Mapping(target = "menu", ignore = true),
			@Mapping(target = "urlPicture", ignore = true), @Mapping(target = "name", ignore = true) })
	UserDto getUserDtoFromentity(User entity);
	List<UserDto> getUsersDtoFromEntities(List<User> entities);

	@Mappings({ @Mapping(target = "roles", ignore = true), @Mapping(target = "fechaActualizacion", ignore = true),
			@Mapping(target = "id", ignore = true), @Mapping(target = "fechaCreacion", ignore = true) })
	User getUserEntityFroDto(UserDto dto);
}
