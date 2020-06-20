/**
 * 
 */
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
	private PagoService pagoService;

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

		Page<PagoDto> pagos = pagoService.getPaginatedPayments(folio, acredor, deudor, formaPago, status, banco, since, to,
				page, size);

		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@GetMapping("/{idPago}")
	public ResponseEntity<PagoDto> getPagoById(@PathVariable(name = "idPago") Integer idPago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(pagoService.getPaymentById(idPago), HttpStatus.OK);
	}
	
	
	// PAGOS
	//TODO refactor pagos controller
//		@GetMapping("/{folio}/pagos")
//		@Deprecated
//		public ResponseEntity<List<PagoDto>> getFacturaPagos(@PathVariable String folio) {
//			return new ResponseEntity<>(pagoService.findPagosByFolioPadre(folio), HttpStatus.OK);
//		}
//
//		@PostMapping("/{folio}/pagos")
//		@Deprecated
//		public ResponseEntity<PagoDto> insertPago(@PathVariable String folio, @RequestBody @Valid PagoDto pago)
//				throws InvoiceManagerException {
//			return new ResponseEntity<>(pagoService.insertNewPayment(folio, pago), HttpStatus.CREATED);
//		}
//
//		@PutMapping("/{folio}/pagos/{id}")
//		@Deprecated
//		public ResponseEntity<PagoDto> updatePago(@PathVariable String folio, @PathVariable Integer id,
//				@RequestBody @Valid PagoDto pagoDto) throws InvoiceManagerException {
//			return new ResponseEntity<>(pagoService.updatePago(folio, id, pagoDto), HttpStatus.OK);
//		}
//
//		@DeleteMapping("/{folio}/pagos/{id}")
//		@Deprecated
//		public ResponseEntity<Void> deletePago(@PathVariable String folio, @PathVariable Integer id)
//				throws InvoiceManagerException {
//			pagoService.deletePago(folio, id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}

}
