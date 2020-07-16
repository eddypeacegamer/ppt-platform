/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.Date;
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

import com.business.unknow.model.dto.pagos.PagoDto;
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
	private PagoService pagoService;

	@GetMapping
	public ResponseEntity<Page<PagoDto>> getAllPayments(
			@RequestParam(name = "solicitante", required = false) Optional<String> solicitante,
			@RequestParam(name = "acredor", required = false) Optional<String> acredor,
			@RequestParam(name = "deudor", required = false) Optional<String> deudor,
			@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "formaPago", defaultValue = "") String formaPago,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<PagoDto> pagos = pagoService.getPaginatedPayments(solicitante, acredor, deudor, formaPago, status, banco,
				since, to, page, size);

		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@GetMapping("/{idPago}")
	public ResponseEntity<PagoDto> getPagoById(@PathVariable(name = "idPago") Integer idPago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.getPaymentById(idPago), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PagoDto> insertPago(@RequestBody @Valid PagoDto pago) throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.insertNewPayment(pago), HttpStatus.CREATED);
	}

	@PutMapping("/{idPago}")
	public ResponseEntity<PagoDto> updatePago(@PathVariable(name = "idPago") Integer idPago,
			@RequestBody @Valid PagoDto pagoDto) throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.updatePago(idPago, pagoDto), HttpStatus.OK);
	}

	@DeleteMapping("/{idPago}")
	public ResponseEntity<Void> deletePago(@PathVariable(name = "idPago") Integer idPago)
			throws InvoiceManagerException {
		pagoService.deletePago(idPago);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
