/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.files.ResourceFile;

/**
 * @author ralfdemoledor
 *
 */
public interface ResourceFileRepository extends CrudRepository<ResourceFile, Integer>{
	
	public Optional<ResourceFile> findByTipoRecursoReferenciaAndTipoArchivo(String tipoRecurso, String referencia, String tipoArchivo);

}
