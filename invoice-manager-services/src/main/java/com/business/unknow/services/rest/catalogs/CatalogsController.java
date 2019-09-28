/**
 * 
 */
package com.business.unknow.services.rest.catalogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.catalogs.ClaveProductoServicioDto;
import com.business.unknow.model.catalogs.ClaveUnidadDto;
import com.business.unknow.model.catalogs.RegimenFiscalDto;
import com.business.unknow.model.catalogs.StatusFacturaDto;
import com.business.unknow.model.catalogs.UsoCfdiDto;
import com.business.unknow.services.services.CatalogsService;

/**
 * @author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/catalogs")
public class CatalogsController {

	@Autowired
	private CatalogsService service;

	@GetMapping("/producto-servicios")
	public ResponseEntity<Page<ClaveProductoServicioDto>> getAllClaveProdServicio(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getAllProductoServicioClaves(page, size), HttpStatus.OK);
	}
	

	@GetMapping("/clave-unidad")
	public ResponseEntity<Page<ClaveUnidadDto>> getAllClaveUnidad(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getAllClaveUnidad(page, size), HttpStatus.OK);
	}

	@GetMapping("/uso-cdfi")
	public ResponseEntity<List<UsoCfdiDto>> getAllUsoCdfi() {
		return new ResponseEntity<>(service.getAllUsoCfdi(), HttpStatus.OK);
	}

	@GetMapping("/regimen-fiscal")
	public ResponseEntity<List<RegimenFiscalDto>> getRegimenFiscal() {
		return new ResponseEntity<>(service.getAllRegimenFiscal(), HttpStatus.OK);
	}
	
	@GetMapping("/status-factura")
	public ResponseEntity<List<StatusFacturaDto>> getStatusFactura() {
		return new ResponseEntity<>(service.getAllStatusFactura(), HttpStatus.OK);
	}

}
