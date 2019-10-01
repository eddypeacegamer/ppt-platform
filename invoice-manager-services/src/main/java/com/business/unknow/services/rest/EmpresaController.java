package com.business.unknow.services.rest;

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
import com.business.unknow.services.services.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/empresas")
@Api(value = "EmpresaController", produces = "application/json")
public class EmpresaController {

	@Autowired
	private EmpresaService service;

	@GetMapping
	@ApiOperation(value = "Get all client by promotor name and name.")
	public ResponseEntity<Page<EmpresaDto>> getEmpresasByParameter(
			@RequestParam(name = "razonSocial", required = false) Optional<String> razonSocial,
			@RequestParam(name = "rfc", required = false) Optional<String> rfc,
			@RequestParam(name = "linea", required = false) Optional<String> linea,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getEmpresasByParametros(rfc, razonSocial,linea, page, size), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "insert a new empresa into the system")
	public ResponseEntity<EmpresaDto> insertClient(@RequestBody @Valid EmpresaDto empresa) {
		return new ResponseEntity<>(service.insertNewEmpresa(empresa), HttpStatus.CREATED);
	}

	@PutMapping("/{rfc}")
	@ApiOperation(value = "insert a new empresa into the system")
	public ResponseEntity<EmpresaDto> updateClient(@PathVariable String rfc, @RequestBody @Valid EmpresaDto empresa) {
		return new ResponseEntity<>(service.updateEmpresaInfo(empresa, rfc), HttpStatus.OK);
	}
}
