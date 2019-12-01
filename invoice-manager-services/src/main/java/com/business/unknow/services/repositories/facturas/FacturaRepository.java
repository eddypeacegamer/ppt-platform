package com.business.unknow.services.repositories.facturas;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.factura.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	public Page<Factura> findAll(Pageable pageable);

	public Optional<Factura> findByFolio(String folio);
	
	
	@Query("select f from Factura f where  f.statusFactura like upper(:status) and f.fechaCreacion between :since and :to")
	public Page<Factura> findAllWithStatusAndDates(@Param("status") String status,@Param("since") Date since, @Param("to") Date to,Pageable pageable);

	@Query("select f from Factura f where upper(f.rfcEmisor) like upper(:rfcEmisor) and upper(f.statusFactura) like upper(:status) and f.fechaCreacion between :since and :to")
	public Page<Factura> findByRfcEmisorWithOtherParams(@Param("rfcEmisor") String rfc,
			@Param("status") String status, @Param("since") Date since, @Param("to") Date to, Pageable pageable);

	@Query("select f from Factura f where upper(f.rfcRemitente) like upper(:rfcRemitente) and upper(f.statusFactura) like upper(:status) and f.fechaCreacion between :since and :to")
	public Page<Factura> findByRfcRemitenteWithOtherParams(@Param("rfcRemitente") String rfc,
			@Param("status") String status, @Param("since") Date since, @Param("to") Date to, Pageable pageable);

	public Page<Factura> findByFolioIgnoreCaseContaining(String rfc, Pageable pageable);
	
	@Query("select f from Factura f where folioPadre= :folioPadre")
	public List<Factura> findComplementosByFolioPadre(@Param("folioPadre") String folioPadre);
	
	public List<Factura> findByFolioPadre(String folioPadre);

}
