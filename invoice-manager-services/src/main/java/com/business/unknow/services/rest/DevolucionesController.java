/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.services.DevolucionDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.dto.services.SolicitudDevolucionDto;
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
			@RequestParam(name = "statusDevolucion", required = false) Optional<String> statusDevolucion,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getDevolucionesByParams(tipoReceptor,idReceptor,statusDevolucion,page, size),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/receptor/{tipoReceptor}/{idReceptor}")
	public ResponseEntity<List<DevolucionDto>> getDevolucionesPorReceptor(
			@PathVariable(name = "tipoReceptor") String tipoReceptor, 
			@PathVariable(name = "idReceptor") String idReceptor,
			@RequestParam(name = "statusDevolucion", defaultValue = "") String statusDevolucion){
		return new ResponseEntity<>(service.getDevolucionesPorReceptor(tipoReceptor, idReceptor, statusDevolucion), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<PagoDto> solicitudDevolucion(@RequestBody SolicitudDevolucionDto solicitud) throws InvoiceManagerException{
		return new ResponseEntity<>(service.solicitudDevolucion(solicitud), HttpStatus.CREATED);
	}
	
	@PostMapping("/pago")
	public ResponseEntity<PagoDto> pagoSolicitud(@RequestBody PagoDto pago) throws InvoiceManagerException{
		return new ResponseEntity<>(service.pagoSolicitudDevolucion(pago), HttpStatus.OK);
	}

}
