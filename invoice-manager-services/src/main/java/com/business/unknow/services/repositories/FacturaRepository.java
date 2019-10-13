package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.factura.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	public Page<Factura> findAll(Pageable pageable);

	public Optional<Factura> findByFolio(String folio);

	@Query("select f from Factura f where upper(f.rfcEmisor) like upper(:rfcEmisor) and upper(f.statusFactura.statusEvento) like upper(:statusEvento) and upper(f.statusFactura.statusPago) like upper(:statusPago)")
	public Page<Factura> findByRfcEmisorWithOtherParams(@Param("rfcEmisor") String rfc,
			@Param("statusEvento") String statusValidacion, @Param("statusPago") String statusPago, Pageable pageable);

	@Query("select f from Factura f where upper(f.rfcRemitente) like upper(:rfcRemitente) and upper(f.statusFactura.statusEvento) like upper(:statusEvento) and upper(f.statusFactura.statusPago) like upper(:statusPago)")
	public Page<Factura> findByRfcRemitenteWithOtherParams(@Param("rfcRemitente") String rfc,
			@Param("statusEvento") String statusValidacion, @Param("statusPago") String statusPago, Pageable pageable);

	public Page<Factura> findByFolioIgnoreCaseContaining(String rfc, Pageable pageable);
	
	@Query("select f from Factura f where folioPadre= :folioPadre")
	public List<Factura> findComplementosByFolioPadre(@Param("folioPadre") String folioPadre);

}
