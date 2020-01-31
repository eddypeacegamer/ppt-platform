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
	
	public Page<Factura> findByFolioIgnoreCaseContaining(String folio, Pageable pageable);
	
	@Query("select f from Factura f where f.solicitante=:solicitante and f.lineaEmisor='A' and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findBySolicitanteWithParams(@Param("solicitante") String solicitante,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where f.solicitante=:solicitante and f.lineaEmisor='A' and f.statusFactura = upper(:status) and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findBySolicitanteAndStatusWithParams(@Param("solicitante") String solicitante,@Param("status") String status,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	
	@Query("select f from Factura f where f.lineaEmisor=:lineaEmisor and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findByLineaEmisorWithParams(@Param("lineaEmisor") String lineaEmisor,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where f.lineaEmisor=:lineaEmisor and upper(f.statusFactura) = upper(:status) and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findByLineaEmisorAndStatusWithParams(@Param("lineaEmisor") String lineaEmisor,@Param("status") String status,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);

	
	
	@Query("select f from Factura f where folioPadre= :folioPadre")
	public List<Factura> findComplementosByFolioPadre(@Param("folioPadre") String folioPadre);
	
	public List<Factura> findByFolioPadre(String folioPadre);

}
