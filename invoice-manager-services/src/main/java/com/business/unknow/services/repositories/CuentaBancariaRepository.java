package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
