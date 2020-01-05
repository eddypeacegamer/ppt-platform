package com.business.unknow.services.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.TransferenciaDto;
import com.business.unknow.services.services.TransferenciaService;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
	
	
	@Autowired
	private TransferenciaService service;
	
	@GetMapping
	public ResponseEntity<Page<TransferenciaDto>> getpaginatedTransfers(
			@RequestParam(name = "tipoEmisor", defaultValue = "") String tipoEmisor,
			@RequestParam(name = "tipoReceptor", defaultValue = "") String tipoReceptor,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		
		return new ResponseEntity<>(service.getTransferenciasPaginated(tipoEmisor, tipoReceptor, page, size), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Page<TransferenciaDto>> updateTransferencia(
			@RequestParam(name = "tipoEmisor", defaultValue = "") String tipoEmisor,
			@RequestParam(name = "tipoReceptor", defaultValue = "") String tipoReceptor,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		
		return new ResponseEntity<>(service.getTransferenciasPaginated(tipoEmisor, tipoReceptor, page, size), HttpStatus.OK);
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<List<TransferenciaDto>> bulkTransfersSave(@RequestBody @Valid List<TransferenciaDto> transferencias){
		return new ResponseEntity<>(service.saveTransferencias(transferencias),HttpStatus.OK);
	}

}
