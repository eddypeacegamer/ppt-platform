package com.business.unknow.services.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.ClientDto;
import com.business.unknow.services.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/api/clients")
@Api(value = "ClientController", produces = "application/json")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping
	@ApiOperation(value = "Get all client by promotor name and name.")
	public ResponseEntity<Page<ClientDto>> getClientsByParameters(
			@RequestParam(name = "razonSocial", required = false) Optional<String> razonSocial,
			@RequestParam(name = "rfc", required = false) Optional<String> rfc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getClientsByParametros(rfc, razonSocial, page, size), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	@ApiOperation(value = "Recover single client by RFC")
	public ResponseEntity<ClientDto> updateClient(@PathVariable String rfc){
		return new ResponseEntity<>(service.getClientByRFC(rfc),HttpStatus.OK);
	}

	
	@PostMapping
	@ApiOperation(value = "insert a new client into the system")
	public ResponseEntity<ClientDto> insertClient(@RequestBody @Valid ClientDto client){
		return new ResponseEntity<>(service.insertNewClient(client),HttpStatus.CREATED);
	}
	
	@PutMapping("/{rfc}")
	@ApiOperation(value = "insert a new client into the system")
	public ResponseEntity<ClientDto> updateClient(@PathVariable String rfc, @RequestBody @Valid ClientDto client){
		return new ResponseEntity<>(service.updateClientInfo(client, rfc),HttpStatus.OK);
	}
}
