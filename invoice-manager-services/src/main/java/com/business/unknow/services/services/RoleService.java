package com.business.unknow.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.dto.services.RoleDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Role;
import com.business.unknow.services.entities.User;
import com.business.unknow.services.mapper.RoleMapper;
import com.business.unknow.services.repositories.RoleRepository;
import com.business.unknow.services.repositories.UserRepository;

/**
 * @author tta000a
 *
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleMapper mapper;

	public List<RoleDto> getRoles() {
		List<Role> result = repository.findAll();
		return mapper.getRoleDtosFromEntities(result);
	}

	public RoleDto getRoleByName(String name) throws InvoiceManagerException {
		Optional<Role> result = repository.findByRole(name);
		if (result.isPresent()) {
			return mapper.getRoleDtoFromentity(result.get());
		} else {
			throw new InvoiceManagerException("Role not found", String.format("Role with the name %s not found", name),
					HttpStatus.NOT_FOUND.value());
		}
	}

	public List<RoleDto> getRolesByUserId(Integer id) {
		User entity = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user no existe %d", id)));
		return mapper.getRoleDtosFromEntities(repository.findByUserId(entity.getId()));
	}

	public void deleteRoleByUserIdAndId(Integer userId, Integer id) {
		User entity = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user no existe %d", userId)));
		Role rol = repository.findByUserIdAndId(entity.getId(), id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role no existe %d", id)));
		;
		repository.delete(rol);
	}

	public RoleDto insertNewRole(Integer userId,RoleDto roleDto) {
		User entity = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user no existe %d", userId)));
		Optional<Role> rol = repository.findByUserIdAndRole(entity.getId(), roleDto.getRole());
		if(rol.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Ya existe el rol con el nombre %s para el user %d",roleDto.getRole(),userId));
		}else {
			Role role=mapper.getEntityFromRoleDto(roleDto);
			role.setUser(entity);
			return mapper.getRoleDtoFromentity(repository.save(role));
		}
	}

}
