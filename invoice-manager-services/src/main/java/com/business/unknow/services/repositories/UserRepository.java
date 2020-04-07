package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public List<User> findAll();

	public Optional<User> findByEmail(String email);

}
