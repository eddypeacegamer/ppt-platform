package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Contribuyente;

@Repository
public interface ContribuyenteRepository extends JpaRepository<Contribuyente, Integer> {

	public Optional<Contribuyente> findByRfc(String rfc);
	public Optional<Contribuyente> findByRazonSocial(String razonSocial);
	
	@Query("select c from Contribuyente c where upper(c.rfc) like upper(:rfc)")
	public Page<Contribuyente> findByRfcIgnoreCaseContaining(@Param("rfc") String rfc , Pageable pageable);
	
	@Query("select c from Contribuyente c where upper(c.razonSocial) like upper(:razonSocial)")
	public Page<Contribuyente> findByRazonSocialIgnoreCaseContaining(@Param("razonSocial") String razonSocial , Pageable pageable);
	
	public Page<Contribuyente> findAll(Pageable pageable);
	
	
	
	
}
