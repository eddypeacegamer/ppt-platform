package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	public Page<Pago> findAll(Pageable pageable);
	public List<Pago> findByFolio(String folio);
	public Optional<Pago> findById(Integer id);
	
}
