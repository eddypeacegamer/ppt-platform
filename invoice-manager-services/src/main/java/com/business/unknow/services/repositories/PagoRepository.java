package com.business.unknow.services.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	public Page<Pago> findAll(Pageable pageable);
	
	public Optional<Pago> findById(Integer id);
	
	@Query("select p from Pago p join p.facturas f where f.folio = :folio")
	public List<Pago> findPagosByFolio(@Param("folio")String folio);
	
	public Page<Pago> findBySolicitanteIgnoreCaseContaining(String folio, Pageable pageable);
	
	@Query("select p from Pago p where upper(p.acredor) like upper(:acredor) and p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosAcredorFilteredByParams(@Param("acredor") String acredor,@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	@Query("select p from Pago p where upper(p.deudor) like upper(:deudor) and p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosDeudorFilteredByParams(@Param("deudor") String deudor,@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	@Query("select p from Pago p where p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosFilteredByParams(@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	

}
