package com.business.unknow.services.repositories.facturas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.cfdi.Receptor;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Integer> {

	@Query("select f from Receptor f where f.cfdi.id=:id")
	public Optional<Receptor> findByIdCfdi(@Param("id") Integer idCfdi);
}
