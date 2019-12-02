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

import com.business.unknow.services.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	public Page<Pago> findAll(Pageable pageable);
	public List<Pago> findByFolio(String folio);
	public List<Pago> findByFolioPadre(String folio);
	
	@Query("select p from Pago p where p.statusPago like upper(:status) and p.formaPago like upper(:formaPago) and p.banco like upper(:banco) and p.fechaPago between :since and :to")
	public Page<Pago> findPagosByFilterParams(@Param("status") String status,@Param("formaPago") String formaPago,
			@Param("banco") String banco, @Param("since") Date since, @Param("to") Date to, Pageable pageable);
	public Page<Pago> findByFolioIgnoreCaseContaining(String folio, Pageable pageable);
	public Optional<Pago> findByFolioAndFormaPagoAndComentarioPago(String folio,String formaPago,String comentario);
	public Optional<Pago> findById(Integer id);
	
}
