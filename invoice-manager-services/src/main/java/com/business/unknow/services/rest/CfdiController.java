/**
 * 
 */
package com.business.unknow.services.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.CfdiService;
import com.business.unknow.services.services.FacturaService;

/**
 * @author ralfdemoledor
 *
 */
@RestController
@RequestMapping("/api/cfdis")
public class CfdiController {

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private CfdiService cfdiService;

	// CFDI
	@PostMapping("/validacion")
	public ResponseEntity<String> validateCfdi(@RequestBody @Valid CfdiDto cfdi) throws InvoiceManagerException {
		cfdiService.validateCfdi(cfdi);
		return new ResponseEntity<>("VALIDA", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CfdiDto> getfacturaCfdi(@PathVariable Integer id) {
		return new ResponseEntity<>(cfdiService.getCfdiById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/facturaInfo")
	public ResponseEntity<FacturaDto> getfacturabyIdCfdi(@PathVariable Integer id) {
		return new ResponseEntity<>(facturaService.getFacturaBaseByPrefolio(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<CfdiDto> insertFacturaCfdi(@PathVariable String folio, @RequestBody @Valid CfdiDto cfdi)
			throws InvoiceManagerException {
		return new ResponseEntity<>(cfdiService.insertNewCfdi(cfdi), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CfdiDto> updateFacturaCfdi(@PathVariable Integer id,
			@RequestBody @Valid CfdiDto cfdi) throws InvoiceManagerException {
		return new ResponseEntity<>(cfdiService.updateCfdiBody(id,cfdi), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFacturaCfdi(@PathVariable Integer id)
			throws InvoiceManagerException {
		cfdiService.deleteCfdi(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//PAGOS PPD CFDI
	@GetMapping("/{id}/pagos")
	public ResponseEntity<List<CfdiPagoDto> > getPagosPPD(@PathVariable Integer id) {
		return new ResponseEntity<>(cfdiService.getPagosPPD(id), HttpStatus.OK);
	}

	// CONCEPTOS
	@PostMapping("/{idCfdi}/conceptos")
	public ResponseEntity<CfdiDto> insertConcepto(@PathVariable Integer idCfdi,
			@RequestBody @Valid ConceptoDto conceptoDto) throws InvoiceManagerException {
		CfdiDto dto = cfdiService.insertNewConceptoToCfdi(idCfdi, conceptoDto);
		facturaService.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@PutMapping("/{idCfdi}/conceptos/{id}")
	public ResponseEntity<CfdiDto> updateConcepto(@PathVariable Integer idCfdi, @PathVariable Integer id,
			@RequestBody @Valid ConceptoDto concepto) throws InvoiceManagerException {
		CfdiDto dto = cfdiService.updateConceptoFromCfdi(idCfdi, id, concepto);
		facturaService.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/{idCfdi}/conceptos/{id}")
	public ResponseEntity<CfdiDto> deleteConcepto(@PathVariable Integer idCfdi, @PathVariable Integer id)
			throws InvoiceManagerException {
		CfdiDto dto = cfdiService.removeConceptFromCfdi(idCfdi, id);
		facturaService.recreatePdf(dto);
		return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
	}

}
