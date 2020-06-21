/**
 * 
 */
package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.PagoFactura;

/**
 * @author ralfdemoledor
 *
 */

@Repository
public interface PagoFacturaRepository extends JpaRepository<PagoFactura, Integer> {
	
	
	List<PagoFactura> findByFolio(String folio);
	
	List<PagoFactura> findByIdCfdi(Integer idCfdi);

}
