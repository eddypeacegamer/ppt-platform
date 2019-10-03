package com.business.unknow.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.UserDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "UserController", produces = "application/json")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/{id}")
	@ApiOperation(value = "Get by id.")
	public ResponseEntity<UserDto> getRolesByName(@PathVariable Integer id) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}

}
