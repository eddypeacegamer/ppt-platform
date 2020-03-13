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

import com.business.unknow.model.dto.services.ContribuyenteDto;
import com.business.unknow.services.services.ContribuyenteService;

@RestController
@RequestMapping("/api/contribuyentes")
public class ContribuyenteController {

	@Autowired
	private ContribuyenteService service;
	
	@GetMapping
	public ResponseEntity<Page<ContribuyenteDto>> getClientsByParameters(
			@RequestParam(name = "razonSocial", required = false) Optional<String> razonSocial,
			@RequestParam(name = "rfc", required = false) Optional<String> rfc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getContribuyentesByParametros(rfc, razonSocial, page, size), HttpStatus.OK);
	}
	

	@GetMapping("/{rfc}")
	public ResponseEntity<ContribuyenteDto> getClientByRfc(@PathVariable String rfc) {
		return new ResponseEntity<>(service.getContribuyenteByRfc(rfc), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ContribuyenteDto> insertClient(@RequestBody @Valid ContribuyenteDto contribuyente) {
		return new ResponseEntity<>(service.insertNewContribuyente(contribuyente), HttpStatus.CREATED);
	}

	@PutMapping("/{rfc}")
	public ResponseEntity<ContribuyenteDto> updateClient(@PathVariable String rfc,
			@RequestBody @Valid ContribuyenteDto contribuyente) {
		return new ResponseEntity<>(service.updateContribuyenteInfo(contribuyente, rfc), HttpStatus.OK);
	}
}
