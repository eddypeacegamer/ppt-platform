package com.business.unknow.services.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
	
	public Optional<Client> findByRfc(String rfc);

}
