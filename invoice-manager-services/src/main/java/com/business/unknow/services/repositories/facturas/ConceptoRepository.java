package com.business.unknow.services.repositories.facturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Integer> {

	@Query("select c from Concepto c where c.cfdi.id =:id")
	public List<Concepto> findByCfdi(@Param("id")int id);
}
