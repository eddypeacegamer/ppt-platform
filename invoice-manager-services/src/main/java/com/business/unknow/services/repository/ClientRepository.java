package com.business.unknow.services.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Optional<Client> findByRfc(String rfc);

}
