package com.business.unknow.services.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "EmpresaController", produces = "application/json")
public class EmpresaController {

	@Autowired
	private EmpresaService service;

	@GetMapping("/empresas")
	@ApiOperation(value = "Get all client by promotor name and name.")
	public ResponseEntity<Page<EmpresaDto>> getEmpresasByParameter(
			@RequestParam(name = "razonSocial", required = false) Optional<String> razonSocial,
			@RequestParam(name = "rfc", required = false) Optional<String> rfc,
			@RequestParam(name = "linea", defaultValue = "") String linea,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getEmpresasByParametros(rfc, razonSocial, linea, page, size),
				HttpStatus.OK);
	}

	@GetMapping("/empresas/{rfc}")
	@ApiOperation(value = "insert a new empresa into the system")
	public ResponseEntity<EmpresaDto> updateClient(@PathVariable String rfc) {
		return new ResponseEntity<>(service.getEmpresaByRfc(rfc), HttpStatus.OK);
	}

	@GetMapping("/lineas/{linea}/giros/{giro}/empresas")
	@ApiOperation(value = "Get all companies by linea and giro")
	public ResponseEntity<List<EmpresaDto>> getEmpresasByLineaAndGiro(@PathVariable(name = "linea") String linea,
			@PathVariable(name = "giro") Integer giro) {
		return new ResponseEntity<>(service.getEmpresasByGiroAndLinea(linea, giro), HttpStatus.OK);
	}
	
	@GetMapping("/lineas/{linea}/empresas")
	@ApiOperation(value = "Get all companies by linea and giro")
	public ResponseEntity<List<EmpresaDto>> getEmpresasByLineaAndGiro(@PathVariable(name = "linea") String linea,
			@RequestParam(name = "rfc", required = true) String rfc) {
		return new ResponseEntity<>(service.getEmpresaByLineaAndRfc(linea, rfc), HttpStatus.OK);
	}

	@PostMapping("/empresas")
	@ApiOperation(value = "insert a new empresa into the system")
	public ResponseEntity<EmpresaDto> insertClient(@RequestBody @Valid EmpresaDto empresa)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewEmpresa(empresa), HttpStatus.CREATED);
	}

	@PutMapping("/empresas/{rfc}")
	@ApiOperation(value = "insert a new empresa into the system")
	public ResponseEntity<EmpresaDto> updateClient(@PathVariable String rfc, @RequestBody @Valid EmpresaDto empresa) {
		return new ResponseEntity<>(service.updateEmpresaInfo(empresa, rfc), HttpStatus.OK);
	}
}
