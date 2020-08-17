package com.business.unknow.services.repositories.facturas;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.factura.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>,JpaSpecificationExecutor<Factura> {

	public Page<Factura> findAll(Pageable pageable);

	public Optional<Factura> findByFolio(String folio);
	
	public Optional<Factura> findByIdCfdi(Integer id);
	
	
	public Page<Factura> findByFolioIgnoreCaseContaining(String folio, Pageable pageable);
	
	public Page<Factura> findByIdCfdi(Integer prefolio, Pageable pageable);
	
	public Page<Factura> findByPreFolio(String prefolio, Pageable pageable);
	
	
	//@Query("select f from Factura f where f.solicitante=:solicitante and f.lineaEmisor=:lineaEmisor and  and f.metodoPago='PPD' and f.tipoDocumento='Factura' and upper(f.razonSocialEmisor) = upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) = upper(:razonSocialRemitente)")
	@Query("select f from Factura f where f.solicitante=:solicitante and f.statusFactura = 3 and f.saldoPendiente>0.01 and f.lineaEmisor=:lineaEmisor and f.metodoPago='PPD' and f.tipoDocumento='Factura' and upper(f.razonSocialEmisor) = upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) = upper(:razonSocialRemitente)")
	public Page<Factura> findFacturasPPD(@Param("solicitante") String solicitante,@Param("lineaEmisor") String lineaEmisor,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where f.solicitante=:solicitante and f.lineaEmisor=:lineaEmisor and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findBySolicitanteWithParams(@Param("solicitante") String solicitante,@Param("lineaEmisor") String lineaEmisor,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where f.solicitante=:solicitante and f.lineaEmisor=:lineaEmisor and f.statusFactura = upper(:status) and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findBySolicitanteAndStatusWithParams(@Param("solicitante") String solicitante,@Param("lineaEmisor") String lineaEmisor,@Param("status") String status,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	
	@Query("select f from Factura f where f.lineaEmisor=:lineaEmisor and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findByLineaEmisorWithParams(@Param("lineaEmisor") String lineaEmisor,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where f.lineaEmisor=:lineaEmisor and upper(f.statusFactura) = upper(:status) and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findByLineaEmisorAndStatusWithParams(@Param("lineaEmisor") String lineaEmisor,@Param("status") String status,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);

	@Query("select f from Factura f where  f.tipoDocumento=:tipo and f.lineaEmisor=:lineaEmisor and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findReportsByLineaEmisorWithParams(@Param("tipo") String tipoDocumento,@Param("lineaEmisor") String lineaEmisor,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	@Query("select f from Factura f where  f.tipoDocumento=:tipo and f.lineaEmisor=:lineaEmisor and upper(f.statusFactura) = upper(:status) and f.fechaCreacion between :since and :to and upper(f.razonSocialEmisor) like upper(:razonSocialEmisor) and upper(f.razonSocialRemitente) like upper(:razonSocialRemitente)")
	public Page<Factura> findReportsByLineaAndStatusEmisorWithParams(@Param("tipo") String tipoDocumento,@Param("status") String status,@Param("lineaEmisor") String lineaEmisor,@Param("since") Date since, @Param("to") Date to,@Param("razonSocialEmisor") String razonSocialEmisor,@Param("razonSocialRemitente") String razonSocialRemitente,Pageable pageable);
	
	


}
