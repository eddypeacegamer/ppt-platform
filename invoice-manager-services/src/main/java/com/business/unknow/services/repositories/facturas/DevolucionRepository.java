/**
 * 
 */
package com.business.unknow.services.repositories.facturas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Devolucion;

/**
 *@author ralfdemoledor
 *
 */
@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer>{
	
	public Page<Devolucion> findAll(Pageable pageable);
	@Query("select d from Devolucion d where d.tipoReceptor =:tipoReceptor and d.receptor =:idReceptor and upper(d.statusDevolucion) like upper(:status)")
	public Page<Devolucion> findDevolucionesByParamsWithStatus(@Param("tipoReceptor")String tipoReceptor,@Param("idReceptor")String idReceptor,@Param("status")String status,Pageable pageable);
	@Query("select d from Devolucion d where d.tipoReceptor =:tipoReceptor and d.receptor =:idReceptor ")
	public Page<Devolucion> findDevolucionesByParams(@Param("tipoReceptor")String tipoReceptor,@Param("idReceptor")String idReceptor,Pageable pageable);
	
	@Query("select d from Devolucion d where d.tipoReceptor =:tipoReceptor and d.receptor =:idReceptor and upper(d.statusDevolucion) like upper(:status)")
	public List<Devolucion> findDevolucionesByParams(@Param("tipoReceptor")String tipoReceptor,@Param("idReceptor")String idReceptor,@Param("status")String status);
	
	public List<Devolucion> findByIdPagoDestino(Integer idPagoDest);
	public List<Devolucion> findByFolio(String folio);
	public Optional<Devolucion> findById(Integer id);
	public Optional<Devolucion> findByIdPagoOrigen(Integer id);

}