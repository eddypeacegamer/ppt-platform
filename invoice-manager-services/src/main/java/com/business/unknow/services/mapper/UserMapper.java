package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.UserDto;
import com.business.unknow.services.entities.User;

@Mapper
public interface UserMapper {

	UserDto getUserDtoFromentity(User entity);
	User getEntityFromUserDto(UserDto dto);
	List<UserDto> getUserDtosFromEntities(List<User> entities);
	List<User> getEntitiesFromUserDtos(List<UserDto> dto);
}
