package com.business.unknow.services.repositories.facturas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Retencion;

@Repository
public interface RetencionRepository extends JpaRepository<Retencion, Integer>{
	
	
	long deleteByConcepto(Concepto concepto);

}
