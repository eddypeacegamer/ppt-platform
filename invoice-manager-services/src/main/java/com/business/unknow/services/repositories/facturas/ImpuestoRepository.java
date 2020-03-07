package com.business.unknow.services.repositories.facturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer> {
	
	@Query("select f from Impuesto f where f.concepto.id=:id")
	public List<Impuesto> findById(@Param("id") int id);
	
	long deleteByConcepto(Concepto concepto);
}
