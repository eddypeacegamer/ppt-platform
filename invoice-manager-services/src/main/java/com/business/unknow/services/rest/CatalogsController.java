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

import com.business.unknow.model.catalogs.ClaveProductoServicioDto;
import com.business.unknow.services.services.CatalogsService;

/**
 * @author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/catalogs")
public class CatalogsController {
	
	@Autowired
	private CatalogsService service;
	
	
	@GetMapping("/producto-servicios")
	public ResponseEntity<Page<ClaveProductoServicioDto>> getAllClaveProdServicio(@RequestParam(name = "page", defaultValue = "1") int page, 
						@RequestParam(name = "size", defaultValue = "15") int size){
		return new ResponseEntity<>(service.getAllProductoServicioClaves(page, size), HttpStatus.OK);
		
	}

}
