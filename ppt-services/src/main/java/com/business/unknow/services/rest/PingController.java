package com.business.unknow.services.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.rest.MessageResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author eej000f	
 *
 */
@RestController
@RequestMapping("/ping")
@Api(value = "PingController", produces = "application/json")

public class PingController {

	@GetMapping
	@ApiOperation(value = "Test Ping Endpoint.")
	public ResponseEntity<MessageResponse> getPing() {
		return new ResponseEntity<>(new MessageResponse("is working ...."),HttpStatus.OK);
	}
}