/**
 * 
 */
package com.business.unknow.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Devolucion;

/**
 *@author ralfdemoledor
 *
 */
@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer>{
	
	public Page<Devolucion> findAll(Pageable pageable);
	public List<Devolucion> findByFolio(String folio);
	public Optional<Devolucion> findById(Integer id);
	public Optional<Devolucion> findByIdPago(Integer id);

}
