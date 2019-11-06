package com.business.unknow.services.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
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
			@RequestParam(name = "payStatus", required = false) Optional<String> statusPago,
			@RequestParam(name = "validationStatus", required = false) Optional<String> statusValidacion,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getFacturasByParametros(rfcEmisor, rfcRemitente, folio, statusValidacion,
				statusPago, page, size), HttpStatus.OK);
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

	@PostMapping("/{folio}/complementos")
	@ApiOperation(value = "insert a new complemento into the system")
	public ResponseEntity<FacturaDto> insertComplemento(@RequestBody @Valid FacturaDto factura,
			@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(service.insertNewComplemento(factura, folio), HttpStatus.CREATED);
	}

	@PutMapping("/{folio}")
	@ApiOperation(value = "update an existing in the system")
	public ResponseEntity<FacturaDto> updateFactura(@PathVariable String folio, @RequestBody @Valid FacturaDto factura)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.updateFactura(factura, folio), HttpStatus.OK);
	}

	@GetMapping("/{folio}/complementos")
	public ResponseEntity<List<FacturaDto>> getComplementos(@PathVariable String folio) {
		return new ResponseEntity<>(service.getComplementos(folio), HttpStatus.OK);
	}

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

	@GetMapping("/{folio}/files")
	public ResponseEntity<FacturaFileDto> getFacturaFiles(@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(service.getFacturaFile(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/files")
	@ApiOperation(value = "insert a new Factura FILE into the system")
	public ResponseEntity<FacturaFileDto> insertFacturaFile(@RequestBody @Valid FacturaFileDto facturaFile) {
		return new ResponseEntity<>(service.insertNewFacturaFile(facturaFile), HttpStatus.CREATED);
	}

	@DeleteMapping("/{folio}/files/{id}")
	@ApiOperation(value = "insert a new Factura FILE into the system")
	public ResponseEntity<Void> deleteFacturaFile(@PathVariable String folio) {
		service.deleteFacturaFile(folio);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{folio}/files")
	@ApiOperation(value = "insert a new Factura into the system")
	public ResponseEntity<FacturaFileDto> updateFactura(@PathVariable String folio,
			@RequestBody @Valid FacturaFileDto facturaFile) throws InvoiceManagerException {
		return new ResponseEntity<>(service.updateFacturaFile(facturaFile, folio), HttpStatus.OK);
	}

	@GetMapping("/{folio}/pagos")
	public ResponseEntity<List<PagoDto>> getFacturaPagos(@PathVariable String folio){
		return new ResponseEntity<>(service.getPagos(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/pagos")
	@ApiOperation(value = "insert a new Payment into the system")
	public ResponseEntity<PagoDto> insertPago(@RequestBody @Valid PagoDto pago) {
		return new ResponseEntity<>(service.insertNewPago(pago), HttpStatus.CREATED);
	}

	@PutMapping("/{folio}/pagos/{id}")
	@ApiOperation(value = "insert a new Factura into the system")
	public ResponseEntity<PagoDto> updatePago(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid PagoDto pagoDto) throws InvoiceManagerException {
		return new ResponseEntity<>(service.updatePago(pagoDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/pagos/{id}")
	@ApiOperation(value = "Deletes an existing payment")
	public ResponseEntity<Void> deletePago(@PathVariable String folio, @PathVariable Integer id) {
		service.deletePago(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
