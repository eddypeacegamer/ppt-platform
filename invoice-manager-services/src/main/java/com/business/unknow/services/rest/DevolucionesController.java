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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.services.DevolucionDto;
import com.business.unknow.model.dto.services.PagoDevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.DevolucionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ralfdemoledor
 *
 */

@RestController
@RequestMapping("/api")
@Api(value = "DevolucionesController", produces = "application/json")
public class DevolucionesController {

	@Autowired
	private DevolucionService service;

	@GetMapping("/devoluciones")
	@ApiOperation(value = "Get all devolutions.")
	public ResponseEntity<Page<DevolucionDto>> getAllDevolutions(
			@RequestParam(name = "tipoReceptor", required = false) Optional<String> tipoReceptor,
			@RequestParam(name = "idReceptor", required = false) Optional<String> idReceptor,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(
				service.getDevolucionesByParams(tipoReceptor, idReceptor, page, size), HttpStatus.OK);
	}
	
	
	@GetMapping("/devoluciones/receptor/{tipoReceptor}/{idReceptor}")
	public ResponseEntity<List<DevolucionDto>> getDevolucionesPorReceptor(
			@PathVariable(name = "tipoReceptor") String tipoReceptor,
			@PathVariable(name = "idReceptor") String idReceptor,
			@RequestParam(name = "statusDevolucion", defaultValue = "") String statusDevolucion) {
		return new ResponseEntity<>(service.getDevolucionesPorReceptor(tipoReceptor, idReceptor),
				HttpStatus.OK);
	}

	@GetMapping("/devoluciones/receptor/{tipoReceptor}/{idReceptor}/saldo")
	public ResponseEntity<Double> getSaldoDevoluciones(
			@PathVariable(name = "tipoReceptor", required = true) String tipoReceptor,
			@PathVariable(name = "idReceptor", required = true) String idReceptor) {
		return new ResponseEntity<>(service.getMontoDevoluciones(tipoReceptor, idReceptor), HttpStatus.OK);
	}

	@PostMapping("/devoluciones")
	public ResponseEntity<PagoDevolucionDto> solicitudDevolucion(@RequestBody PagoDevolucionDto solicitudes)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.solicitudDevolucion(solicitudes), HttpStatus.CREATED);
	}

	
	@PostMapping("/devoluciones/bulk")
	public ResponseEntity<List<PagoDevolucionDto>> solicitudDevoluciones(@RequestBody List<PagoDevolucionDto> solicitudes)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.solicitudDevoluciones(solicitudes), HttpStatus.CREATED);
	}

	@PutMapping("/devoluciones/{id}")
	public ResponseEntity<PagoDevolucionDto> solicitudDevolucionUpdate(@RequestBody PagoDevolucionDto solicitud,
			@PathVariable(name = "id") Integer id) throws InvoiceManagerException {
		return new ResponseEntity<>(service.solicitudDevolucionUpdate(solicitud, id), HttpStatus.CREATED);
	}
	@GetMapping("/devoluciones/pagos")
	public ResponseEntity<Page<PagoDevolucionDto>> getDevolucionesByParams(
			@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "tipoReceptor", defaultValue = "") String tipoReceptor,
			@RequestParam(name = "idReceptor", defaultValue = "") String idReceptor,
			@RequestParam(name = "beneficiario", defaultValue = "") String beneficiario,
			@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		return new ResponseEntity<>(service.getPagoDevolucionesByParams(status, formaPago, beneficiario, tipoReceptor, idReceptor, page, size),HttpStatus.OK);
	}

}
