package com.business.unknow.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.RoleDto;
import com.business.unknow.services.entities.Role;
import com.business.unknow.services.mapper.RoleMapper;
import com.business.unknow.services.repository.RoleRepository;

/**
 * @author tta000a
 *
 */
@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private RoleMapper mapper;
	
	
	public List<RoleDto> getRoles() {
		List<Role> result = repository.findAll();
		return mapper.getRoleDtosFromEntities(result);
	}
	
	public RoleDto getRoleByName(String name) {
		Optional<Role> result = repository.findByName(name);
		if(result.isPresent()) {
			return mapper.getRoleDtoFromentity(result.get());
		}else {
			return null;
		}
		
	}

}
