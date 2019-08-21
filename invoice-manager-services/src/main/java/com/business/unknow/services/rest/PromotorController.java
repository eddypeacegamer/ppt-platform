package com.business.unknow.services.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.PromotorDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.PromotorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/promotors")
@Api(value = "PromotorController", produces = "application/json")
public class PromotorController {

	@Autowired
	private PromotorService service;

	@GetMapping
	@ApiOperation(value = "Get all promotors.")
	public ResponseEntity<List<PromotorDto>> getPromotors() {
		return new ResponseEntity<>(service.getPromotors(), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	@ApiOperation(value = "Get all promotors.")
	public ResponseEntity<PromotorDto> getPromotorByName(@PathVariable String name) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getPromotorsByName(name), HttpStatus.OK);
	}

}
