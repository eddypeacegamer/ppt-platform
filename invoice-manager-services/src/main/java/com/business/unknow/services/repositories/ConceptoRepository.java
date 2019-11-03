package com.business.unknow.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Integer> {

}
