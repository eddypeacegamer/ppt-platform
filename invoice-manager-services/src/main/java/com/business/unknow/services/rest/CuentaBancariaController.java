package com.business.unknow.services.rest;

import java.util.Date;
import java.util.List;

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

import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.services.CuentaBancariaService;


@RestController
@RequestMapping("/api")
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService service;

	@GetMapping("/cuentas")
	public ResponseEntity<Page<CuentaBancariaDto>> getCuentasBancariasByfilters(
			@RequestParam(name = "empresa", defaultValue = "") String empresa,
			@RequestParam(name = "banco", defaultValue = "") String banco,
			@RequestParam(name = "cuenta", defaultValue = "") String cuenta,
			@RequestParam(name = "clabe", defaultValue = "") String clabe,
			@RequestParam(name = "since", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date since,
			@RequestParam(name = "to", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<>(service.getCuentasBancariasByfilters(banco, empresa, clabe, cuenta, since, to, page, size), HttpStatus.OK);
	}
	
	@GetMapping("/empresas/{empresa}/cuentas")
	public ResponseEntity<List<CuentaBancariaDto>> getCuentasBancariasByfilters(@PathVariable(name = "empresa") String empresa) {
		return new ResponseEntity<>(service.getCuentasPorEmpresa(empresa), HttpStatus.OK);
	}
	
	
	@GetMapping("/cuenta/{empresa}/{cuenta}")
	public ResponseEntity<CuentaBancariaDto> infoCuentaBancaria(@PathVariable(name = "empresa") String empresa,@PathVariable(name = "cuenta") String cuenta) {
		return new ResponseEntity<>(service.infoCuentaBancaria(empresa,cuenta), HttpStatus.OK);
	}
	
	@PostMapping("/cuentas")
	public ResponseEntity<CuentaBancariaDto> createCuentaBancaria(@RequestBody @Valid CuentaBancariaDto cuentaBancariaDto) {
		return new ResponseEntity<>(service.createCuentaBancaria(cuentaBancariaDto), HttpStatus.OK);
	}
	
	@PutMapping("/cuentas/{cuentaId}")
	public ResponseEntity<CuentaBancariaDto> upadteCuentaBancaria(@PathVariable Integer cuentaId, @RequestBody @Valid CuentaBancariaDto cuentaBancariaDto) {
		return new ResponseEntity<>(service.updateCuentaBancaria(cuentaId, cuentaBancariaDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/cuentas/{id}")
	public ResponseEntity<Void> deleteCuentaBancaria(@PathVariable Integer id) {
		service.deleteCuentaBancaria(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
