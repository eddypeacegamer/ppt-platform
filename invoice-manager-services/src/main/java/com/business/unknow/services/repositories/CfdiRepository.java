package com.business.unknow.services.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.cfdi.Cfdi;

public interface CfdiRepository extends JpaRepository<Cfdi, Integer> {
	public Optional<Cfdi> findByFolio(String folio);
}
