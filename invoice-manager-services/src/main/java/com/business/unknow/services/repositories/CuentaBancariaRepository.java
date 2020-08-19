package com.business.unknow.services.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.business.unknow.services.entities.CuentaBancaria;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {
	
	public List<CuentaBancaria> findAll();
	public List<CuentaBancaria> findByEmpresa(String empresa);
	public List<CuentaBancaria> findByBanco(String banco);
	public List<CuentaBancaria> findByEmpresaAndBanco(String empresa,String banco);
	public Optional<CuentaBancaria> findByCuenta(String cuenta);
	public Optional<CuentaBancaria> findByClabe(String clabe);
	
	public List<CuentaBancaria> findById(String id);

	@Query("select c from CuentaBancaria c where c.banco like upper(:banco) and c.empresa like upper(:empresa) and c.clabe like upper(:clabe) and c.fechaCreacion between :since and :to")
	public Page<CuentaBancaria> findCuentasByFilterParams(@Param("banco") String banco,@Param("empresa") String empresa,@Param("clabe") String clabe, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	

}
