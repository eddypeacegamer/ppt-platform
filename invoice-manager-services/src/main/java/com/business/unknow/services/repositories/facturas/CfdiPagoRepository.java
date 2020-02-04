package com.business.unknow.services.repositories.facturas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.CfdiPago;

@Repository
public interface CfdiPagoRepository extends JpaRepository<CfdiPago, Integer> {

	@Query("select f from CfdiPago f where f.cfdi.id=:id")
	public List<CfdiPago> findByCfdi(@Param("id") int id);
}
