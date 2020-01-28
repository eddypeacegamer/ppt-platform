package com.business.unknow.services.rest;

import java.util.Date;
import java.util.List;

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

import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.services.CuentaBancariaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "ClientController", produces = "application/json")
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
		return new ResponseEntity<>(service.getCuentasBancariasByfilters(banco, empresa, clabe, since, to, page, size), HttpStatus.OK);
	}
	
	@GetMapping("/empresas/{empresa}/cuentas")
	public ResponseEntity<List<CuentaBancariaDto>> getCuentasBancariasByfilters(@PathVariable(name = "empresa") String empresa) {
		return new ResponseEntity<>(service.getCuentasPorEmpresa(empresa), HttpStatus.OK);
	}
	

}
