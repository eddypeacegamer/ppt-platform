package com.business.unknow.services.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.factura.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	public Page<Factura> findAll(Pageable pageable);
	public Optional<Factura> findByFolio(String folio);
	public Page<Factura> findByRfcEmisorIgnoreCaseContaining(String rfc , Pageable pageable);
	public Page<Factura> findByRfcRemitenteIgnoreCaseContaining(String rfc , Pageable pageable);
	public Page<Factura> findByFolioIgnoreCaseContaining(String rfc , Pageable pageable);
	public Page<Factura> findByUuidIgnoreCaseContaining(String rfc , Pageable pageable);
}
