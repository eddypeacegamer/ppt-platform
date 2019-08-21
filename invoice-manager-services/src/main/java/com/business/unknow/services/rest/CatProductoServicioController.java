package com.business.unknow.services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.CatProductoServicioDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.CatProductoServicioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/catProductService")
@Api(value = "CatProductoServicioController", produces = "application/json")
public class CatProductoServicioController {

	@Autowired
	private CatProductoServicioService service;

	@GetMapping
	@ApiOperation(value = "Get cat roots.")
	public ResponseEntity<List<CatProductoServicioDto>> getRoots() throws InvoiceManagerException {
		return new ResponseEntity<>(service.getRoots(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get cat by parent id.")
	public ResponseEntity<List<CatProductoServicioDto>> getChildrenByRootId(@PathVariable Integer id)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.getChildsByRootId(id), HttpStatus.OK);
	}
}
