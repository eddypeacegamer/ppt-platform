/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.PagoService;

/**
 * @author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/api/pagos")
public class PagosController {

	@Autowired
	private PagoService service;

	@GetMapping
	public ResponseEntity<Page<PagoDto>> getAllPayments(
			@RequestParam(name = "folio", required = false) Optional<String> folio,
			@RequestParam(name = "acredor", required = false) Optional<String> acredor,
			@RequestParam(name = "deudor", required = false) Optional<String> deudor,
			@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<PagoDto> pagos = service.getPaginatedPayments(folio, acredor, deudor, formaPago, status, banco, since, to,
				page, size);

		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@GetMapping("/ingresos")
	public ResponseEntity<Page<PagoDto>> getIngresos(@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "cuenta", defaultValue = "") String cuenta,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<PagoDto> pagos = service.getIngresosPaginados(formaPago, status, banco, cuenta, since, to, page, size);

		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@GetMapping("/ingresos/total")
	public ResponseEntity<Double> getSumaIngresos(@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "cuenta", defaultValue = "") String cuenta,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		Double total = service.getSumaIngresosbyParams(formaPago, banco, cuenta, since, to);
		System.out.println(total);
		return new ResponseEntity<>(total, HttpStatus.OK);

	}

	@GetMapping("/egresos")
	public ResponseEntity<Page<PagoDto>> getEgresos(@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "cuenta", defaultValue = "") String cuenta,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<PagoDto> pagos = service.getEgresosPaginados(formaPago, status, banco, cuenta, since, to, page, size);

		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@GetMapping("/egresos/total")
	public ResponseEntity<Double> getSumaEgresos(@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "cuenta", defaultValue = "") String cuenta,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		return new ResponseEntity<>(service.getSumaEgresosbyParams(formaPago, banco, cuenta, since, to), HttpStatus.OK);
	}

	@GetMapping("/{idPago}")
	public ResponseEntity<PagoDto> getPagoById(@PathVariable(name = "idPago") Integer idPago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.getPaymentById(idPago), HttpStatus.OK);
	}

}
