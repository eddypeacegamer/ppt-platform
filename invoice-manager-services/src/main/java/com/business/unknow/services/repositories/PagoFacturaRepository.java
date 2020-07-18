/**
 * 
 */
package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.PagoFactura;

/**
 * @author ralfdemoledor
 *
 */

@Repository
public interface PagoFacturaRepository extends JpaRepository<PagoFactura, Integer> {

	List<PagoFactura> findByFolio(String folio);

	@Query("select c from PagoFactura c where c.pago.id=:idPago")
	List<PagoFactura> findByPagoId(@Param("idPago") Integer idPago);

	List<PagoFactura> findByIdCfdi(Integer idCfdi);

}
