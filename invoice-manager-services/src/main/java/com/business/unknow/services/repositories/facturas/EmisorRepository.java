package com.business.unknow.services.repositories.facturas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.business.unknow.services.entities.cfdi.Emisor;

@Repository
public interface EmisorRepository extends JpaRepository<Emisor, Integer> {
	
	@Query("select f from Emisor f where f.cfdi.id=:id")
	public Optional<Emisor> findByIdCfdi(@Param("id") Integer idCfdi);

}
