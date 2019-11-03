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

import com.business.unknow.model.PagoDto;
import com.business.unknow.services.services.PagoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *@author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/api/pagos")
@Api(value = "PagosController", produces = "application/json")
public class PagosController {
	
	@Autowired
	private PagoService service;
	
	
	@GetMapping
	@ApiOperation(value = "Get all payments.")
	public ResponseEntity<Page<PagoDto>> getAllPayments(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		
		Page<PagoDto> pagos = service.getAllPagos(page, size);
		
		return new ResponseEntity<>(pagos,HttpStatus.OK);
	}

}
