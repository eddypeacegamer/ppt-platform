package com.business.unknow.services.repositories.facturas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.business.unknow.services.entities.factura.FacturaFile;

@Repository
public interface FacturaFileRepository extends JpaRepository<FacturaFile, Integer> {

	public Optional<FacturaFile> findByFolio(String folio);
}