/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.StatusCatalogoDto;
import com.business.unknow.model.catalogs.ClaveProductoServicioDto;
import com.business.unknow.model.catalogs.ClaveUnidadDto;
import com.business.unknow.model.catalogs.GiroDto;
import com.business.unknow.model.catalogs.RegimenFiscalDto;
import com.business.unknow.model.catalogs.StatusFacturaDto;
import com.business.unknow.model.catalogs.UsoCfdiDto;
import com.business.unknow.services.services.CatalogsService;

/**
 * @author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/api/catalogs")
public class CatalogsController {

	@Autowired
	private CatalogsService service;

	@GetMapping("/producto-servicios")
	public ResponseEntity<List<ClaveProductoServicioDto>> getClaveProductoServicios(
			@RequestParam(name = "descripcion", required = true) String description){
		return new ResponseEntity<>(service.getProductoServicioByDescription(description), HttpStatus.OK);
	}

	@GetMapping("/clave-unidad")
	public ResponseEntity<List<ClaveUnidadDto>> getClaveUnidad(
			@RequestParam(name = "nombre", required = true) String nombre) {
		return new ResponseEntity<>(service.getCalveUnidadByNombre(nombre), HttpStatus.OK);
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

	@GetMapping("/giros")
	public ResponseEntity<List<GiroDto>> getGiros() {
		return new ResponseEntity<>(service.getAllGiros(), HttpStatus.OK);
	}
	
	@GetMapping("/status-evento")
	public ResponseEntity<List<StatusCatalogoDto>> getStatusEventos() {
		return new ResponseEntity<>(service.getAllStatusEvento(), HttpStatus.OK);
	}
	
	@GetMapping("/status-pago")
	public ResponseEntity<List<StatusCatalogoDto>> getStatusPago() {
		return new ResponseEntity<>(service.getAllStatusPago(), HttpStatus.OK);
	}
	
	@GetMapping("/status-devolucion")
	public ResponseEntity<List<StatusCatalogoDto>> getAllStatusDevoluicion() {
		return new ResponseEntity<>(service.getAllStatusDevoluicion(), HttpStatus.OK);
	}
	
	@GetMapping("/status-revision")
	public ResponseEntity<List<StatusCatalogoDto>> getAllStatusRevision() {
		return new ResponseEntity<>(service.getAllStatusRevision(), HttpStatus.OK);
	}

}