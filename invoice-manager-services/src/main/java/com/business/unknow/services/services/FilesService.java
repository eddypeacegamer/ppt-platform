/**
 *
 */
package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.repositories.files.FilesDao;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class FilesService {


	@Autowired
	private FilesDao filesDao;

	public FacturaFileDto getFacturaFileByFolioAndType(String folio, String type) throws InvoiceManagerException {
		return filesDao.findFacturaFileByResourceTypeAndReference(folio, type).orElseThrow(
				() -> new InvoiceManagerException("El recurso solicitado no existe.", HttpStatus.NOT_FOUND.value()));
	}
	
	public Optional<FacturaFileDto> findFacturaFileByFolioAndType(String folio, String type) {
		return filesDao.findFacturaFileByResourceTypeAndReference(folio, type);
	}

	public ResourceFileDto getResourceFileByResourceReferenceAndType(String resource, String referencia, String type)
			throws InvoiceManagerException {
		return filesDao.findResourceFileByResourceTypeAndReference(resource, type, referencia).orElseThrow(
				() -> new InvoiceManagerException("El recurso solicitado no existe.", HttpStatus.NOT_FOUND.value()));
	}
	
	public Optional<ResourceFileDto> findResourceFileByResourceReferenceAndType(String resource, String referencia, String type) {
		return filesDao.findResourceFileByResourceTypeAndReference(resource, type, referencia);
	}

	public void upsertResourceFile(ResourceFileDto resourceFile) {
		Optional<ResourceFileDto> resource = filesDao.findResourceFileByResourceTypeAndReference(
				resourceFile.getTipoRecurso(), resourceFile.getReferencia(), resourceFile.getTipoArchivo());
		if (resource.isPresent()) {
			resourceFile.setId(resource.get().getId());
			filesDao.updateResourceFile(resource.get().getId(), resourceFile.getData());
		} else {
			filesDao.insertResourceFile(resourceFile);
		}
	}

	public void upsertFacturaFile(FacturaFileDto facturaFile) {
		Optional<FacturaFileDto> file = filesDao.findFacturaFileByResourceTypeAndReference(facturaFile.getFolio(), facturaFile.getTipoArchivo());
		if (file.isPresent()) {
			filesDao.updateFacturaFile(file.get().getId(), facturaFile.getData());
		}else {
			filesDao.insertFacturaFile(facturaFile);
		}
	}

	public void deleteFacturaFile(Integer id) {
		filesDao.deletFacturaFileById(id);
	}

	public void deleteResourceFile(Integer id) {
		filesDao.deletResourceFileById(id);
	}

	public void deleteFacturaFileByFolioAndType(String folio, String type) {
		filesDao.deleteFacturaFileByResourceTypeAndReference(folio, type);

	}

	public void deleteResourceFileByResourceReferenceAndType(String resource, String referencia, String type) {
		filesDao.deleteResourceFileByResourceTypeAndReference(resource, type, referencia);
	}


}
