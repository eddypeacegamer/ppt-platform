/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.DevolucionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *@author ralfdemoledor
 *
 */

@RestController
@RequestMapping("/api/devoluciones")
@Api(value = "DevolucionesController", produces = "application/json")
public class DevolucionesController {
	
	@Autowired
	private DevolucionService service;

	
	@GetMapping
	@ApiOperation(value = "Get all devolutions.")
	public ResponseEntity<Page<DevolucionDto>> getAllDevolutions(
			@RequestParam(name = "tipoReceptor", required = false) Optional<String> tipoReceptor,
			@RequestParam(name = "idReceptor", required = false) Optional<String> idReceptor,
			@RequestParam(name = "statusPago", required = false) Optional<String> statusPago,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getDevolucionesByParams(tipoReceptor,idReceptor,statusPago,page, size),
				HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Void> generarDevoluiones(@RequestBody PagoDto pago) throws InvoiceManagerException{
		service.generarDevolucionesPorPago(pago);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
}
