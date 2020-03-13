/**
 * 
 */
package com.business.unknow.services.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.FacturaPdfModelDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.FilesService;


/**
 * @author hha0009
 *
 */
@RestController
@RequestMapping("/api")
public class FilesController {

	@Autowired
	private FilesService service;

	@GetMapping("/facturas/{folio}/files/{fileType}")
	public ResponseEntity<FacturaFileDto> getFacturaFiles(@PathVariable(name = "folio") String folio,
			@PathVariable(name = "fileType") String fileType) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFileByFolioAndType(folio, fileType), HttpStatus.OK);
	}

	@GetMapping("/recursos/{recurso}/files/{fileType}/referencias/{referencia}")
	public ResponseEntity<ResourceFileDto> getResourceFiles(@PathVariable(name = "recurso") String recurso,
			@PathVariable(name = "fileType") String fileType, @PathVariable(name = "referencia") String referencia)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFileByResourceReferenceAndType(recurso, referencia, fileType),
				HttpStatus.OK);
	}

	@PostMapping("/facturas/{folio}/files")
	public ResponseEntity<FacturaFileDto> insertFacturaFile(@RequestBody @Valid FacturaFileDto facturaFile) {
		return new ResponseEntity<>(service.insertfacturaFile(facturaFile), HttpStatus.CREATED);
	}

	@PostMapping("/recursos/{recurso}/files")
	public ResponseEntity<ResourceFileDto> insertResourceFile(@RequestBody @Valid ResourceFileDto resourceFile) {
		return new ResponseEntity<>(service.insertResourceFile(resourceFile), HttpStatus.CREATED);
	}

	@DeleteMapping("/facturas/files/{id}")
	public ResponseEntity<Void> deleteFacturaFile(@PathVariable Integer id) {
		service.deleteFacturaFile(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/recursos/files/{id}")
	public ResponseEntity<Void> deleteRecursoFile(@PathVariable Integer id) {
		service.deleteResourceFile(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// PDF
	@GetMapping("/facturas/{folio}/pdf")
	public ResponseEntity<FacturaPdfModelDto> getPdfFromFactura(@PathVariable String folio)
			throws InvoiceCommonException {
		return new ResponseEntity<>(service.getPdfFromFactura(folio), HttpStatus.OK);
	}

}
