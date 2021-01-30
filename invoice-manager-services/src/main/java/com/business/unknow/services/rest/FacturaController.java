package com.business.unknow.services.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.DevolucionService;
import com.business.unknow.services.services.FacturaService;
import com.business.unknow.services.services.PagoService;

/**
 * @author eej000f
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

	@Autowired
	private FacturaService service;

	@Autowired
	private PagoService pagoService;

	@Autowired
	private DevolucionService devolucionService;

	// FACTRURAS
	@GetMapping
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByParametros(@RequestParam Map<String, String> parameters) {
		return new ResponseEntity<>(service.getFacturasByParametros(parameters), HttpStatus.OK);
	}

	@GetMapping("/factura-reports")
	public ResponseEntity<Page<FacturaReportDto>> getAllFacturasReportsByParametros(
			@RequestParam Map<String, String> parameters) {
		return new ResponseEntity<>(service.getFacturaReportsByParams(parameters), HttpStatus.OK);
	}

	@GetMapping("/complemento-reports")
	public ResponseEntity<Page<PagoReportDto>> getAllComplementoReportsByParametros(
			@RequestParam Map<String, String> parameters) {
		return new ResponseEntity<>(service.getComplementoReportsByParams(parameters), HttpStatus.OK);
	}

	@GetMapping("/{folio}")
	public ResponseEntity<FacturaDto> getFactura(@PathVariable String folio) {
		return new ResponseEntity<>(service.getFacturaByFolio(folio), HttpStatus.OK);
	}

	@GetMapping("/complementos/{folio}")
	public ResponseEntity<FacturaDto> getComplementoByIdCfdiAnParcialidad(@PathVariable String folio,
			@RequestParam(name = "parcialidad", defaultValue = "1") Integer parcialidad) {
		return new ResponseEntity<>(service.getComplementoByIdCfdiAnParcialidad(folio, parcialidad), HttpStatus.OK);
	}

	@GetMapping("/{folio}/pagos")
	public ResponseEntity<List<PagoDto>> getPagosbyFolio(@PathVariable String folio) {
		return new ResponseEntity<>(pagoService.findPagosByFolio(folio), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FacturaDto> insertFacturaWithCfdi(@RequestBody @Valid FacturaDto factura)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewFacturaWithDetail(factura), HttpStatus.CREATED);
	}

	@PutMapping("/{idCfdi}")
	public ResponseEntity<FacturaDto> updateFactura(@PathVariable Integer idCfdi,
			@RequestBody @Valid FacturaDto factura) {
		return new ResponseEntity<>(service.updateFactura(idCfdi, factura), HttpStatus.OK);
	}

	// TIMBRADO
	@PostMapping("/{folio}/timbrar")
	public ResponseEntity<FacturaDto> timbrarFactura(@PathVariable String folio,
			@RequestBody @Valid FacturaDto facturaDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.timbrarFactura(folio, facturaDto).getFacturaDto(), HttpStatus.OK);
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

	@PostMapping("/{folio}/correos")
	public ResponseEntity<FacturaContext> renviarCorreos(@PathVariable String folio,
			@RequestBody @Valid FacturaDto facturaDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.renviarCorreo(folio, facturaDto), HttpStatus.OK);
	}

	@PostMapping("/{folio}/complementos")
	public ResponseEntity<FacturaDto> getComplementos(@PathVariable String folio, @RequestBody @Valid PagoDto pago)
			throws InvoiceManagerException {
		FacturaDto facturaDto = service.createComplemento(folio, pago);
		return new ResponseEntity<>(service.timbrarFactura(facturaDto.getFolio(), facturaDto).getFacturaDto(),
				HttpStatus.OK);
	}

	@PostMapping("/{folio}/sustitucion")
	public ResponseEntity<FacturaDto> createFacturaRelacionada(@RequestBody @Valid FacturaDto factura)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.sustitucion(factura), HttpStatus.OK);
	}
}
