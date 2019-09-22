package com.business.unknow.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.EmpresaService;

import io.swagger.annotations.Api;

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
	public ResponseEntity<Page<EmpresaDto>> getAllEmpresas(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getAllEmpresas(page, size), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<EmpresaDto> getAllEmpresasByName(@PathVariable String name){
		return new ResponseEntity<>(service.getEmpresaByName(name), HttpStatus.OK);
	}

}
