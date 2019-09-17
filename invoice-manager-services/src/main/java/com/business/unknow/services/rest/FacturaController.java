package com.business.unknow.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.FacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.FacturaService;

import io.swagger.annotations.Api;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping
@Api(value = "FacturaController", produces = "application/json")
public class FacturaController {

	@Autowired
	private FacturaService service;

	@GetMapping("/facturas")
	public ResponseEntity<Page<FacturaDto>> getAllFacturas(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "15") int size) {
		return new ResponseEntity<>(service.getAllFacturas(page, size), HttpStatus.OK);
	}


	@GetMapping("/promotores/{promotor}/clientes/{rfc}/facturas")
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByEmpresaAndClientAndEmpresa(@PathVariable String rfc,
			@PathVariable String promotor, @RequestParam(name = "empresa", required=false) String empresa,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "15") int size) throws InvoiceManagerException {
		return new ResponseEntity<>(
				service.getAllFacturasByPromotorAndClientAndEmpresas(promotor, rfc, empresa, page, size), HttpStatus.OK);
	}

	@GetMapping("/promotores/{promotor}/facturas")
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByEmpresat(@PathVariable String promotor,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "15") int size) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getAllFacturasByPromotor(promotor, page, size), HttpStatus.OK);
	}

	@GetMapping("/facturas/{folio}")
	public ResponseEntity<FacturaDto> getAllFacturaByFolio(@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFacturaByFolio(folio), HttpStatus.OK);
	}
}
