package com.business.unknow.services.config.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.business.unknow.model.error.InvoiceManagerException;

/**
 * @author eej000f
 *
 */
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvoiceManagerException.class })
	protected ResponseEntity<Object> handleServiceException(InvoiceManagerException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getErrorMessage(), new HttpHeaders(),
				HttpStatus.valueOf(ex.getErrorMessage().getHttpStatus()), request);
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
	}
}