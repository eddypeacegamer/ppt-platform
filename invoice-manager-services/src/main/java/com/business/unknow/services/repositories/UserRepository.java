package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public List<User> findAll();
	
	public Page<User> findAll(Pageable pageable);

	public Optional<User> findByEmail(String email);
	
	@Query("select u from User u where u.activo like upper(:status) and upper(u.email) like upper(:email) and upper(u.alias) like upper(:alias)")
	public Page<User> findAllByParams(@Param("status") String status,@Param("email") String email,@Param("alias")String alias,Pageable pageable);

}
