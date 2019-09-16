package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Optional<Client> findByRfc(String rfc);
	
	@Query(value = "select e from Client e where e.empresa.name =:empresa")
	public Page<Client> findAllByEmpresaName(@Param("empresa") String empresa,Pageable pageable);
	
	public Page<Client> findAll(Pageable pageable);
}
