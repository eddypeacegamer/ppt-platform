package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.business.unknow.services.entities.factura.FacturaFile;

public interface FacturaFileRepository extends JpaRepository<FacturaFile, Integer> {

	public Optional<FacturaFile> findByFolio(String folio);
}
