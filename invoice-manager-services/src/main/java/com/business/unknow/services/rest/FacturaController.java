package com.business.unknow.services.rest;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.model.dto.PagoReportDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.services.PagoDevolucionDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.CfdiService;
import com.business.unknow.services.services.DevolucionService;
import com.business.unknow.services.services.FacturaService;
import com.business.unknow.services.services.PagoService;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author eej000f
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

	@Autowired
	private FacturaService service;

	@Autowired
	private CfdiService cfdiService;

	@Autowired
	private PagoService pagoService;

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

	@PutMapping("/{folio}")
	public ResponseEntity<FacturaDto> updateFactura(@PathVariable String folio,
			@RequestBody @Valid FacturaDto factura) {
		return new ResponseEntity<>(service.updateFactura(factura, folio), HttpStatus.OK);
	}

	@GetMapping("/{folio}/complementos")
	public ResponseEntity<List<FacturaDto>> getComplementos(@PathVariable String folio) {
		return new ResponseEntity<>(service.getComplementos(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/complementos")
	public ResponseEntity<FacturaDto> getComplementos(@PathVariable String folio, @RequestBody @Valid PagoDto pago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.createComplemento(folio, pago), HttpStatus.OK);
	}

	// CFDI
	@PostMapping("/cfdi/validacion")
	public ResponseEntity<String> validateCfdi(@RequestBody @Valid CfdiDto cfdi) throws InvoiceManagerException {
		cfdiService.validateCfdi(cfdi);
		return new ResponseEntity<>("VALIDA", HttpStatus.OK);
	}

	@GetMapping("/{folio}/cfdi")
	public ResponseEntity<CfdiDto> getfacturaCfdi(@PathVariable String folio) throws InvoiceManagerException {
		return new ResponseEntity<>(cfdiService.getCfdiByFolio(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/cfdi")
	public ResponseEntity<CfdiDto> insertFacturaCfdi(@PathVariable String folio, @RequestBody @Valid CfdiDto cfdi)
			throws InvoiceManagerException {
		return new ResponseEntity<>(cfdiService.insertNewCfdi(cfdi), HttpStatus.OK);
	}

	@PutMapping("/{folio}/cfdi/{id}")
	public ResponseEntity<CfdiDto> updateFacturaCfdi(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid CfdiDto cfdi) throws InvoiceManagerException {
		return new ResponseEntity<>(cfdiService.updateCfdiBody(folio, id, cfdi), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/cfdi/{id}")
	public ResponseEntity<Void> deleteFacturaCfdi(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		cfdiService.deleteCfdi(folio);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// CONCEPTOS
	@PostMapping("/{folio}/conceptos")
	public ResponseEntity<CfdiDto> insertConcepto(@PathVariable String folio,
			@RequestBody @Valid ConceptoDto conceptoDto) throws InvoiceManagerException {
		CfdiDto dto = cfdiService.insertNewConceptoToCfdi(folio, conceptoDto);
		service.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@PutMapping("/{folio}/conceptos/{id}")
	public ResponseEntity<CfdiDto> updateConcepto(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid ConceptoDto concepto) throws InvoiceManagerException {
		CfdiDto dto = cfdiService.updateConceptoFromCfdi(folio, id, concepto);
		service.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/conceptos/{id}")
	public ResponseEntity<CfdiDto> deleteConcepto(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		CfdiDto dto = cfdiService.removeConceptFromCfdi(folio, id);
		service.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
	}

	// PAGOS
	@GetMapping("/{folio}/pagos")
	public ResponseEntity<List<PagoDto>> getFacturaPagos(@PathVariable String folio) {
		return new ResponseEntity<>(pagoService.findPagosByFolioPadre(folio), HttpStatus.OK);
	}

	@PostMapping("/{folio}/pagos")
	public ResponseEntity<PagoDto> insertPago(@PathVariable String folio, @RequestBody @Valid PagoDto pago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.insertNewPayment(folio, pago), HttpStatus.CREATED);
	}

	@PutMapping("/{folio}/pagos/{id}")
	public ResponseEntity<PagoDto> updatePago(@PathVariable String folio, @PathVariable Integer id,
			@RequestBody @Valid PagoDto pagoDto) throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.updatePago(folio, id, pagoDto), HttpStatus.OK);
	}

	@DeleteMapping("/{folio}/pagos/{id}")
	public ResponseEntity<Void> deletePago(@PathVariable String folio, @PathVariable Integer id)
			throws InvoiceManagerException {
		pagoService.deletePago(folio, id);
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

	// DEVOLUCIONES
	@GetMapping("/{folio}/devoluciones/{tipoReceptor}")
	public ResponseEntity<PagoDevolucionDto> getSolicitudDevolcuion(@PathVariable String folio,
			@PathVariable String tipoReceptor) {
		return new ResponseEntity<>(devolucionService.findDevolucionesByfacturaAndTipoReceptor(folio, tipoReceptor),
				HttpStatus.OK);
	}

}
