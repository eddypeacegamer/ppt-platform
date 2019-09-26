package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	public Page<Empresa> findAll(Pageable pageable);

	public Optional<Empresa> findByRfc(String rfc);

	public Page<Empresa> findByRfcIgnoreCaseContaining(String rfc, Pageable pageable);

}
