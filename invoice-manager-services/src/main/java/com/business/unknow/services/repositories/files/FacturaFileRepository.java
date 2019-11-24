/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.files.FacturaFile;

/**
 * @author ralfdemoledor
 *
 */
public interface FacturaFileRepository extends CrudRepository<FacturaFile, Integer>{
	
	
	public Optional<FacturaFile> findByFolioAndTipoArchivo(String folio, String tipoArchivo);

}
