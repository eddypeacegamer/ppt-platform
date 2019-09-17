package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query(value = "select c from Client c where c.promotor.name =:promotor")
	public Page<Client> findAllByPromotor(@Param("promotor") String promotor, Pageable pageable);

	public Page<Client> findAll(Pageable pageable);

	public Optional<Client> findByRfc(String rfc);

	@Query(value = "select c from Client c where c.promotor.name =:promotor and c.rfc=:rfc")
	public Optional<Client> findByEmpresaAndRfc(@Param("promotor") String promotor, @Param("rfc") String rfc);
}
