package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.business.unknow.services.entities.PagoDevolucion;

@Repository
public interface PagoDevolucionRepository extends JpaRepository<PagoDevolucion, Integer> {

	public Optional<PagoDevolucion> findById(Integer id);
	
	public Page<PagoDevolucion> findAll(Pageable pageable);
	
	public Page<PagoDevolucion> findByTipoReceptorAndReceptor(String tipoReceptor,String receptor,Pageable pageable);
	
	@Query("select p from PagoDevolucion p where p.status =:status and p.formaPago like upper(:formaPago) and upper(p.beneficiario) like upper(:beneficiario) and upper(p.receptor) like upper(:receptor)")
	public Page<PagoDevolucion> findByStatusAndParams(@Param("status") String status,@Param("formaPago")String formaPago,@Param("beneficiario")String beneficiario,@Param("receptor")String receptor,Pageable pageable);
	
	@Query("select p from PagoDevolucion p where p.formaPago like upper(:formaPago) and upper(p.beneficiario) like upper(:beneficiario) and upper(p.receptor) like upper(:receptor)")
	public Page<PagoDevolucion> findByParams(@Param("formaPago")String formaPago,@Param("beneficiario")String beneficiario,@Param("receptor")String receptor,Pageable pageable);
}
