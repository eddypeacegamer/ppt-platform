package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	public Page<Empresa> findAll(Pageable pageable);

	@Query("select e from Empresa e where upper(e.tipo) like upper(:linea) and upper(e.informacionFiscal.rfc) like upper(:rfc)")
	public Page<Empresa> findByRfcIgnoreCaseContaining(@Param("rfc") String rfc ,@Param("linea") String linea, Pageable pageable);
	
	@Query("select e from Empresa e where upper(e.tipo) like upper(:linea) and upper(e.informacionFiscal.razonSocial) like upper(:razonSocial)")
	public Page<Empresa> findByRazonSocialIgnoreCaseContaining(@Param("razonSocial") String razonSocial, @Param("linea") String linea , Pageable pageable);

	
	@Query("select e from Empresa e where e.informacionFiscal.rfc = :rfc")
	public Optional<Empresa> findByRfc( @Param("rfc") String rfc);
	
	public List<Empresa> findByTipoAndGiro(String tipo,Integer giroId);

}
