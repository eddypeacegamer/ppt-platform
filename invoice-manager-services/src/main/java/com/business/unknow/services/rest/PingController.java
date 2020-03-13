package com.business.unknow.services.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.unknow.model.rest.MessageResponse;

/**
 * @author eej000f	
 *
 */
@RestController
@RequestMapping("/ping")
public class PingController {

	@GetMapping
	public ResponseEntity<MessageResponse> getPing() {
		return new ResponseEntity<>(new MessageResponse("is working ...."),HttpStatus.OK);
	}
}