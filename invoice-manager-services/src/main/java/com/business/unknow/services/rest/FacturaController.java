package com.business.unknow.services.rest;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.model.dto.PagoReportDto;
import com.business.unknow.model.dto.pagos.PagoDevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.DevolucionService;
import com.business.unknow.services.services.FacturaService;

/**
 * @author eej000f
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

	@Autowired
	private FacturaService service;


	@Autowired
	private DevolucionService devolucionService;

	// FACTRURAS
	@GetMapping
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByParametros(
			@RequestParam(name = "folio", required = false) Optional<String> folio,
			@RequestParam(name = "solicitante", required = false) Optional<String> solicitante, // linea A by default
			@RequestParam(name = "lineaEmisor", defaultValue = "A") String lineaEmisor,
			@RequestParam(name = "emisor", defaultValue = "") String emisor,
			@RequestParam(name = "remitente", defaultValue = "") String receptor,
			@RequestParam(name = "status") Optional<String> status,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getFacturasByParametros(folio, solicitante, lineaEmisor, status, since, to,
				emisor, receptor, page, size), HttpStatus.OK);
	}

	@GetMapping("/factura-reports")
	public ResponseEntity<Page<FacturaReportDto>> getAllFacturasReportsByParametros(
			@RequestParam(name = "status") Optional<String> status,
			@RequestParam(name = "lineaEmisor", defaultValue = "A") String lineaEmisor,
			@RequestParam(name = "emisor", defaultValue = "") String emisor,
			@RequestParam(name = "remitente", defaultValue = "") String receptor,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "100") int size) {
		return new ResponseEntity<>(
				service.getFacturaReportsByParams(status, lineaEmisor, emisor, receptor, since, to, page, size),
				HttpStatus.OK);
	}

	@GetMapping("/complemento-reports")
	public ResponseEntity<Page<PagoReportDto>> getAllComplementoReportsByParametros(
			@RequestParam(name = "status") Optional<String> status,
			@RequestParam(name = "lineaEmisor", defaultValue = "A") String lineaEmisor,
			@RequestParam(name = "emisor", defaultValue = "") String emisor,
			@RequestParam(name = "remitente", defaultValue = "") String receptor,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "100") int size) {
		return new ResponseEntity<>(
				service.getComplementoReportsByParams(status, lineaEmisor, emisor, receptor, since, to, page, size),
				HttpStatus.OK);
	}

	@GetMapping("/{folio}")
	public ResponseEntity<FacturaDto> getFactura(@PathVariable String folio) {
		return new ResponseEntity<>(service.getFacturaByFolio(folio), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FacturaDto> insertFacturaWithCfdi(@RequestBody @Valid FacturaDto factura)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewFacturaWithDetail(factura), HttpStatus.CREATED);
	}

//	@PutMapping("/{folio}")
//	public ResponseEntity<FacturaDto> updateFactura(@PathVariable String folio,
//			@RequestBody @Valid FacturaDto factura) {
//		return new ResponseEntity<>(service.updateFactura(factura, folio), HttpStatus.OK);
//	}

	

	// TIMBRADO
	@PostMapping("/{folio}/timbrar")
	public ResponseEntity<FacturaContext> timbrarFactura(@PathVariable String folio,
			@RequestBody @Valid FacturaDto facturaDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.timbrarFactura(folio, facturaDto), HttpStatus.OK);
	}

	@PostMapping("/{folio}/cancelar")
	public ResponseEntity<FacturaContext> cancelarFactura(@PathVariable String folio,
			@RequestBody @Valid FacturaDto facturaDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.cancelarFactura(folio, facturaDto), HttpStatus.OK);
	}

	// DEVOLUCIONES
	@GetMapping("/{folio}/devoluciones/{tipoReceptor}")
	public ResponseEntity<PagoDevolucionDto> getSolicitudDevolcuion(@PathVariable String folio,
			@PathVariable String tipoReceptor) {
		return new ResponseEntity<>(devolucionService.findDevolucionesByfacturaAndTipoReceptor(folio, tipoReceptor),
				HttpStatus.OK);
	}

}
