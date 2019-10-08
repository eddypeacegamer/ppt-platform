package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.factura.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

	public List<Pago> findByFolio(String folio);
	public Optional<Pago> findById(Integer id);
}
