package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.Role;

/**
 * @author eej000f
 *
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
 
	public List<Role> findAll();
	public Optional<Role> findByRole(String role);
}
