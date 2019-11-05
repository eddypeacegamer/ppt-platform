/**
 * 
 */
package com.business.unknow.services.error;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.business.unknow.model.error.ErrorMessage;

/**
 * @author ralfdemoledor
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { SQLException.class })
	public ResponseEntity<Object> handleSQLException(Exception ex, WebRequest request) {
		logger.error("SQLException ocurred", ex);
		return handleExceptionInternal(ex,
				new ErrorMessage(ex.getMessage(), "SQLException: " + ex.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR.value()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/*
	 * @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	 * protected ResponseEntity<Object> handleMethodArgumentNotValid(
	 * MethodArgumentTypeMismatchException ex,WebRequest request) {
	 * logger.warn("MethodArgumentTypeMismatchException ocurred",ex); return
	 * handleExceptionInternal( ex, ex.getMessage(), new HttpHeaders()
	 * ,HttpStatus.BAD_REQUEST , request); }
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn("MethodArgumentNotValidException ocurred", ex);
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		logger.error("Exception ocurred", ex);
		return handleExceptionInternal(ex,
				new ErrorMessage(ex.getMessage(), "Unknown error has occurred: " + ex.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR.value()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
