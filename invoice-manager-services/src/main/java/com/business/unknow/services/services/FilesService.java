/**
 * 
 */
package com.business.unknow.services.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaPdfModelDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.services.entities.files.FacturaFile;
import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.mapper.FilesMapper;
import com.business.unknow.services.repositories.files.FacturaFileRepository;
import com.business.unknow.services.repositories.files.ResourceFileRepository;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class FilesService {
	
	@Autowired
	private FacturaFileRepository facturaRepo;
	
	@Autowired
	private ResourceFileRepository resourceRepo;
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private FilesMapper mapper;
	
	
	public FacturaFileDto getFileByFolioAndType(String folio, String type) {
		Optional<FacturaFile> file = facturaRepo.findByFolioAndTipoArchivo(folio, type);
		if(file.isPresent()) {
			return mapper.getFacturaFileDtoFromEntity(file.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}
	
	public ResourceFileDto getFileByResourceReferenceAndType(String resource,String referencia, String type) {
		Optional<ResourceFile> file = resourceRepo.findByTipoRecursoAndReferenciaAndTipoArchivo(resource, referencia, type);
		if(file.isPresent()) {
			return mapper.getResourceFileDtoFromEntity(file.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}
	
	
	public ResourceFileDto insertResourceFile( ResourceFileDto resourceFile) {
		
		Optional<ResourceFile> resource = resourceRepo.findByTipoRecursoAndReferenciaAndTipoArchivo(resourceFile.getTipoRecurso(), resourceFile.getReferencia(), resourceFile.getTipoArchivo());
		if(resource.isPresent()) {
		 resourceFile.setId(resource.get().getId());
		 resourceFile.setFechaCreacion(new Date());
		}
		return mapper.getResourceFileDtoFromEntity(resourceRepo.save(mapper.getResourceFileFromDto(resourceFile)));
	}
	
	public FacturaFileDto insertfacturaFile(FacturaFileDto facturaFile) {
		return mapper.getFacturaFileDtoFromEntity(facturaRepo.save(mapper.getFacturaFileFromDto(facturaFile)));
	}
	
	public void deleteFacturaFile(Integer id) {
		Optional<FacturaFile> entity = facturaRepo.findById(id);
		if(entity.isPresent()) {
			facturaRepo.delete(entity.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}
	
	
	public void deleteResourceFile(Integer id) {
		Optional<ResourceFile> entity = resourceRepo.findById(id);
		if(entity.isPresent()) {
			resourceRepo.delete(entity.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
		}
	}
	
	public FacturaPdfModelDto getPdfFromFactura(String folio) {
		FacturaDto facturaDto=facturaService.getFacturaByFolio(folio);
		FacturaFileDto qr=getFileByFolioAndType(folio,TipoArchivoEnum.QR.name());
		ResourceFileDto logotipo=getFileByResourceReferenceAndType("Empresa", facturaDto.getRfcEmisor(), "LOGO");
		return new FacturaPdfModelDto(qr.getData(),logotipo.getData(),facturaDto);
	}


}
