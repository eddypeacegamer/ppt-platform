package com.business.unknow.services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.RoleDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/roles")
@Api(value = "RoleController", produces = "application/json")
public class RoleController {

	@Autowired
	private RoleService service;

	@GetMapping
	@ApiOperation(value = "Get all role.")
	public ResponseEntity<List<RoleDto>> getRoles() {
		return new ResponseEntity<>(service.getRoles(), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	@ApiOperation(value = "Get role by id.")
	public ResponseEntity<RoleDto> getRolesByName(@PathVariable String name) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getRoleByName(name), HttpStatus.OK);
	}

}
