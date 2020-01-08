package com.business.unknow.services.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
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

	// FACTRURAS
	@GetMapping
	public ResponseEntity<Page<FacturaDto>> getAllFacturasByParametros(
			@RequestParam(name = "folio", required = false) Optional<String> folio,
			@RequestParam(name = "solicitante", required = false) Optional<String> solicitante,//linea A by default
			@RequestParam(name = "lineaEmisor", defaultValue = "A") String lineaEmisor,
			@RequestParam(name = "emisor", defaultValue = "") String rfcEmisor,
			@RequestParam(name = "remitente", defaultValue = "") String rfcRemitente,
			@RequestParam(name = "status", defaultValue = "1") String status,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(
				service.getFacturasByParametros(folio, solicitante, lineaEmisor, status, since, to, rfcEmisor, rfcRemitente, page, size),HttpStatus.OK);
	}

	@GetMapping("/{folio}")
	public ResponseEntity<FacturaDto> getFactura(@PathVariable String folio) {
		return new ResponseEntity<>(service.getfacturaByFolio(folio), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "insert a new Factura into the system")
	public ResponseEntity<FacturaDto> insertFacturaWithCfdi(@RequestBody @Valid FacturaDto factura)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewFacturaWithDetail(factura), HttpStatus.CREATED);
	}

	@PutMapping("/{folio}")
	@ApiOperation(value = "update an existing in the system")
	public ResponseEntity<FacturaDto> updateFactura(@PathVariable String folio, @RequestBody @Valid FacturaDto factura) {
		return new ResponseEntity<>(service.updateFactura(factura, folio), HttpStatus.OK);
	}

	@GetMapping("/{folio}/complementos")
	public ResponseEntity<List<FacturaDto>> getComplementos(@PathVariable String folio) {
		return new ResponseEntity<>(service.getComplementos(folio), HttpStatus.OK);
	}

	// CFDI
	@GetMapping("/{folio}/cfdi")
	public ResponseEntity<CfdiDto> getfacturaCfdi(@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFacturaCdfi(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/cfdi")
	public ResponseEntity<CfdiDto> insertFacturaCfdi(@PathVariable String folio, @RequestBody @Valid CfdiDto cfdi)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewCfdi(folio, cfdi), HttpStatus.OK);
	}

	@PutMapping("/{folio}/cfdi/{id}")
	public ResponseEntity<CfdiDto> updateFacturaCfdi(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid CfdiDto cfdi) throws InvoiceManagerException {
		return new ResponseEntity<>(service.updateFacturaCfdi(folio, id, cfdi), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/cfdi/{id}")
	public ResponseEntity<Void> deleteFacturaCfdi(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		service.deleteFacturaCfdi(folio, id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// PAGOS
	@GetMapping("/{folio}/pagos")
	public ResponseEntity<List<PagoDto>> getFacturaPagos(@PathVariable String folio) {
		return new ResponseEntity<>(service.getPagos(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/pagos")
	@ApiOperation(value = "insert a new Payment into the system")
	public ResponseEntity<PagoDto> insertPago(@PathVariable String folio, @RequestBody @Valid PagoDto pago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewPago(folio, pago), HttpStatus.CREATED);
	}

	@PutMapping("/{folio}/pagos/{id}")
	@ApiOperation(value = "insert a new Factura into the system")
	public ResponseEntity<PagoDto> updatePago(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid PagoDto pagoDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.updatePago(pagoDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/pagos/{id}")
	@ApiOperation(value = "Deletes an existing payment")
	public ResponseEntity<Void> deletePago(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		service.deletePago(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

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

	// CONCEPTOS
	@PostMapping("/{folio}/conceptos")
	public ResponseEntity<ConceptoDto> insertConcepto(@PathVariable String folio,
			@RequestBody @Valid ConceptoDto conceptoDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertConcepto(folio, conceptoDto), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/conceptos/{id}")
	public ResponseEntity<Void> deleteConcepto(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		service.deleteconcepto(id, folio);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
