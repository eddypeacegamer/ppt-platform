/**
 * 
 */
package com.business.unknow.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.services.services.DevolucionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *@author ralfdemoledor
 *
 */

@RestController
@RequestMapping("/api/devoluciones")
@Api(value = "DevolucionesController", produces = "application/json")
public class DevolucionesController {
	
	@Autowired
	private DevolucionService service;

	
	@GetMapping
	@ApiOperation(value = "Get all devolutions.")
	public ResponseEntity<Page<DevolucionDto>> getAllDevolutions(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getAllDevoluciones(page, size),
				HttpStatus.OK);
	}
}
