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

	@GetMapping("/{idPago}")
	public ResponseEntity<PagoDto> getPagoById(@PathVariable(name = "idPago") Integer idPago)
			throws InvoiceManagerException {
		return new ResponseEntity<>(service.getPaymentById(idPago), HttpStatus.OK);
	}
	
	@GetMapping("/dummy")
	public String getPagoByIdDummy(			
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size)
			throws InvoiceManagerException {
		
if(page == 0) {
	return "{\"content\":[{\"data\":{\"ID\":\"22\",\"MONTO\":3500,\"TIPO\":\"D\",\"FECHA\":\"2020-05-20\",\"SALDO\":10400,\"STATUS\":\"VALIDACION\"},\"children\":[{\"data\":{\"ID\":\"22\",\"MONTO\":-4332,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456789034}},{\"data\":{\"ID\":\"22\",\"MONTO\":-454646,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456783456}}]},{\"data\":{\"ID\":\"19\",\"MONTO\":7500,\"TIPO\":\"D\",\"FECHA\":\"2020-05-12\",\"SALDO\":500,\"STATUS\":\"RECHAZADO\"},\"children\":[{\"data\":{\"ID\":\"19\",\"MONTO\":-3000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":2020456789876}},{\"data\":{\"ID\":\"19\",\"MONTO\":-4000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":2020434567899}}]},{\"data\":{\"ID\":\"11\",\"MONTO\":10000,\"TIPO\":\"D\",\"FECHA\":\"2020-05-03\",\"SALDO\":0,\"STATUS\":\"ACEPTADO\"},\"children\":[{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-08\",\"FOLIO\":2020498765455}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-08\",\"FOLIO\":2020764234578}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-05\",\"FOLIO\":2020567887895}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-05\",\"FOLIO\":2020386468909}}]}],\"pageable\":{\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"paged\":true,\"unpaged\":false},\"totalElements\":250,\"last\":false,\"totalPages\":25,\"size\":10,\"number\":0,\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"numberOfElements\":10,\"first\":true,\"empty\":false}";
		
		}
		if(page == 1) {
			
		}
		return "{\"content\":[{\"data\":{\"ID\":\"22\",\"MONTO\":3500,\"TIPO\":\"D\",\"FECHA\":\"2020-05-20\",\"SALDO\":1000,\"STATUS\":\"VALIDACION\"},\"children\":[{\"data\":{\"ID\":\"22\",\"MONTO\":-1500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456789034}},{\"data\":{\"ID\":\"22\",\"MONTO\":-1000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456783456}}]},{\"data\":{\"ID\":\"19\",\"MONTO\":7500,\"TIPO\":\"D\",\"FECHA\":\"2020-05-12\",\"SALDO\":500,\"STATUS\":\"RECHAZADO\"},\"children\":[{\"data\":{\"ID\":\"19\",\"MONTO\":-3000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":2020456789876}},{\"data\":{\"ID\":\"19\",\"MONTO\":-4000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":2020434567899}}]},{\"data\":{\"ID\":\"11\",\"MONTO\":10000,\"TIPO\":\"D\",\"FECHA\":\"2020-05-03\",\"SALDO\":0,\"STATUS\":\"ACEPTADO\"},\"children\":[{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-08\",\"FOLIO\":2020498765455}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-08\",\"FOLIO\":2020764234578}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-05\",\"FOLIO\":2020567887895}},{\"data\":{\"ID\":\"11\",\"MONTO\":-2500,\"TIPO\":\"P\",\"FECHA\":\"2020-05-05\",\"FOLIO\":2020386468909}},{\"data\":{\"ID\":\"22\",\"MONTO\":-1000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456783456}}]},{\"data\":{\"ID\":\"15\",\"MONTO\":22324,\"TIPO\":\"D\",\"FECHA\":\"123-05-12\",\"SALDO\":123,\"STATUS\":\"RECHAZADO\"},\"children\":[{\"data\":{\"ID\":\"19\",\"MONTO\":-4235345,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":345234653246}},{\"data\":{\"ID\":\"22\",\"MONTO\":-1000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456783456}}]},{\"data\":{\"ID\":\"112\",\"MONTO\":4546456,\"TIPO\":\"D\",\"FECHA\":\"453534-05-12\",\"SALDO\":435422234,\"STATUS\":\"234234\"},\"children\":[{\"data\":{\"ID\":\"19\",\"MONTO\":-343463245234,\"TIPO\":\"P\",\"FECHA\":\"234234-05-15\",\"FOLIO\":245234523452345234}},{\"data\":{\"ID\":\"22\",\"MONTO\":-1000,\"TIPO\":\"P\",\"FECHA\":\"2020-05-22\",\"FOLIO\":2020456783456}}]},{\"data\":{\"ID\":\"19\",\"MONTO\":234234234,\"TIPO\":\"D\",\"FECHA\":\"2020-05-12\",\"SALDO\":500,\"STATUS\":\"RECHAZADO\"},\"children\":[{\"data\":{\"ID\":\"19\",\"MONTO\":-234523523,\"TIPO\":\"P\",\"FECHA\":\"2020-05-15\",\"FOLIO\":2020456789876}}]}],\"pageable\":{\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"paged\":true,\"unpaged\":false},\"totalElements\":250,\"last\":false,\"totalPages\":25,\"size\":10,\"number\":0,\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"numberOfElements\":10,\"first\":false,\"empty\":false}";
	}
	
	

}
