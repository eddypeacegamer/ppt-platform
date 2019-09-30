package com.business.unknow.services.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.ContribuyenteDto;
import com.business.unknow.services.services.ContribuyenteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contribuyentes")
@Api(value = "ContribuyenteController", produces = "application/json")
public class ContribuyenteController {

	@Autowired
	private ContribuyenteService service;

	@GetMapping("/{rfc}")
	@ApiOperation(value = "Get all contribuyentes ")
	public ResponseEntity<ContribuyenteDto> getClientByRfc(@PathVariable String rfc) {
		return new ResponseEntity<>(service.getContribuyenteByRfc(rfc), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "insert a new clien into the system")
	public ResponseEntity<ContribuyenteDto> insertClient(@RequestBody @Valid ContribuyenteDto contribuyente) {
		return new ResponseEntity<>(service.insertNewContribuyente(contribuyente), HttpStatus.CREATED);
	}

	@PutMapping("/{rfc}")
	@ApiOperation(value = "insert a new clien into the system")
	public ResponseEntity<ContribuyenteDto> updateClient(@PathVariable String rfc,
			@RequestBody @Valid ContribuyenteDto contribuyente) {
		return new ResponseEntity<>(service.updateContribuyenteInfo(contribuyente, rfc), HttpStatus.OK);
	}
}
