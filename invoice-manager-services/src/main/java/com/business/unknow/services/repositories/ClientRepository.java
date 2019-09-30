package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	public Page<Client> findAll(Pageable pageable);
	
	
	@Query("select c from Client c where c.informacionFiscal.rfc like :rfc")
	public Page<Client> findByRfcIgnoreCaseContaining(@Param("rfc") String rfc , Pageable pageable);
	
	@Query("select c from Client c where upper(c.informacionFiscal.razonSocial) like upper(:razonSocial)")
	public Page<Client> findByRazonSocialIgnoreCaseContaining(@Param("razonSocial") String razonSocial , Pageable pageable);

	@Query("select c from Client c where c.informacionFiscal.rfc = :rfc")
	public Optional<Client> findByRfc( @Param("rfc") String rfc);
}
