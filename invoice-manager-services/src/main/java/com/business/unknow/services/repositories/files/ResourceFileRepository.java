/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.files.ResourceFile;

/**
 * @author ralfdemoledor
 *
 */
@Repository
public interface ResourceFileRepository extends JpaRepository<ResourceFile, Integer>{
	
	public Optional<ResourceFile> findByTipoRecursoAndReferenciaAndTipoArchivo(String tipoRecurso, String referencia, String tipoArchivo);

}
