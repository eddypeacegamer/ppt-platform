/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.files.FacturaFile;


/**
 * @author ralfdemoledor
 *
 */
@Repository
public interface FacturaFileRepository extends JpaRepository<FacturaFile, Integer>{
	
	
	public Optional<FacturaFile> findByFolioAndTipoArchivo(String folio, String tipoArchivo);
	
	public long deleteByFolioAndTipoArchivo(String folio, String tipoArchivo);

}
