package com.business.unknow.services.repositories.facturas;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Cfdi;

@Repository
public interface CfdiRepository extends JpaRepository<Cfdi, Integer> {
	public Optional<Cfdi> findByFolio(String folio);
}
