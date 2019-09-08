package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.Role;
import com.business.unknow.services.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public List<User> findAll();

	public Optional<Role> findByEmail(String email);

}
