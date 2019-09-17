package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	public Page<Factura> findAll(Pageable pageable);

	@Query(value = "select f from Factura f where f.cliente.rfc =:rfc and f.cliente.promotor.name =:promotor and f.empresa.name =:empresa")
	public Page<Factura> findAllByPromotorAndRfcAndEmpresa(@Param("promotor") String promotor, @Param("rfc") String rfc,@Param("empresa") String empresa,
			Pageable pageable);
	
	@Query(value = "select f from Factura f where f.cliente.rfc =:rfc and f.cliente.promotor.name =:promotor")
	public Page<Factura> findAllByPromotorAndRfc(@Param("promotor") String promotor, @Param("rfc") String rfc,
			Pageable pageable);

	@Query(value = "select f from Factura f where  f.cliente.promotor.name =:promotor")
	public Page<Factura> findAllByPromotor(@Param("promotor") String promotor, Pageable pageable);

	public Optional<Factura> findByFolio(String folio);
}
