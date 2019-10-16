package com.business.unknow.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.cfdi.Concepto;

public interface ConceptoRepository extends JpaRepository<Concepto, Integer> {

}
