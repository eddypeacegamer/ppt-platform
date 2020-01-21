package com.business.unknow.services.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.services.CuentaBancariaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/cuentas-bancarias")
@Api(value = "ClientController", produces = "application/json")
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService service;

	@GetMapping
	public ResponseEntity<List<CuentaBancariaDto>> getCuentasBancariasByfilters(
			@RequestParam(name = "empresa", required = false) Optional<String> empresa,
			@RequestParam(name = "banco", required = false) Optional<String> banco) {
		return new ResponseEntity<>(service.getCuentasBancariasByfilters(banco, empresa), HttpStatus.OK);
	}

}
