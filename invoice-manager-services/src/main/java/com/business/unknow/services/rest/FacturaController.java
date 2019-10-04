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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.OLD.FacturaDto;
import com.business.unknow.services.services.FacturaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f
 *
 */
@RestController
@RequestMapping("/api/facturas")
@Api(value = "FacturaController", produces = "application/json")
public class FacturaController {

	@Autowired
	private FacturaService service;

	@GetMapping
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByParametros(
			@RequestParam(name = "emisor", required = false) Optional<String> rfcEmisor,
			@RequestParam(name = "remitente", required = false) Optional<String> rfcRemitente,
			@RequestParam(name = "folio", required = false) Optional<String> folio,
			@RequestParam(name = "uuid", required = false) Optional<String> uuid,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getFacturasByParametros(rfcEmisor, rfcRemitente, folio, uuid, page, size),
				HttpStatus.OK);
	}

	@GetMapping("/files/folio/{folio}")
	public ResponseEntity<FacturaFileDto> getFacturaFiles(@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFacturaFile(folio), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "insert a new Factura into the system")
	public ResponseEntity<FacturaDto> insertClient(@RequestBody @Valid FacturaDto factura) {
		return new ResponseEntity<>(service.insertNewFactura(factura), HttpStatus.CREATED);
	}

}
