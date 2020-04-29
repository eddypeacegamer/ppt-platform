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
	public List<Pago> findByFolio(String folio);
	public List<Pago> findByFolioPadre(String folio);
	public Optional<Pago> findById(Integer id);
	
	@Query("select p from Pago p where p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosFilteredByParams(@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	@Query("select p from Pago p where upper(p.acredor) like upper(:acredor) and p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosAcredorFilteredByParams(@Param("acredor") String acredor,@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	@Query("select p from Pago p where upper(p.deudor) like upper(:deudor) and p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaCreacion between :since and :to")
	public Page<Pago> findPagosDeudorFilteredByParams(@Param("deudor") String deudor,@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	public Page<Pago> findByFolioIgnoreCaseContaining(String folio, Pageable pageable);
	public Optional<Pago> findByFolioAndFormaPagoAndComentarioPago(String folio,String formaPago,String comentario);

	
	@Query("select p from Pago p where p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and  p.cuenta like upper(:cuenta) and p.fechaPago between :since and :to")
	public Page<Pago> findIngresosByFilterParams(@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco,@Param("cuenta") String cuenta, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	@Query("select p from Pago p where p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and  p.cuenta like upper(:cuenta) and p.fechaCreacion between :since and :to")
	public Page<Pago> findEgresosByFilterParams(@Param("status") String status,@Param("formaPago") String formaPago,@Param("banco") String banco,@Param("cuenta") String cuenta,@Param("since") Date since, @Param("to") Date to, Pageable pageable);
	
	
	@Query("select sum(p.monto) from Pago p where p.statusPago='ACEPTADO' and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and  p.cuenta like upper(:cuenta) and p.fechaPago between :since and :to")
	public Double sumIngresosByFilterParams(@Param("formaPago") String formaPago,@Param("banco") String banco,@Param("cuenta") String cuenta, @Param("since") Date since, @Param("to") Date to);
	
	@Query("select sum(p.monto) from Pago p where p.statusPago='PAGADO' and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and  p.cuenta like upper(:cuenta) and p.fechaCreacion between :since and :to")
	public Double sumEgresosByFilterParams(@Param("formaPago") String formaPago,@Param("banco") String banco,@Param("cuenta") String cuenta,@Param("since") Date since, @Param("to") Date to);
	
	
	
}
