package com.business.unknow.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

	public List<Client> findAll();

	public Optional<Client> findByName(String name);

}
