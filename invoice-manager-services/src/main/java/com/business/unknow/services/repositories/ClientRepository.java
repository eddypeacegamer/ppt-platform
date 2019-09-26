package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	public Page<Client> findAll(Pageable pageable);
	
	public Page<Client> findByRfcIgnoreCaseContaining(String rfc , Pageable pageable);

	public Optional<Client> findByRfc(String rfc);
}
