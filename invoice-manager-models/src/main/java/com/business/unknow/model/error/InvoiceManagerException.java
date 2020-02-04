package com.business.unknow.model.error;

/**
 * @author eej000f
 *
 */

public class InvoiceManagerException extends Exception {

	private final transient ErrorMessage errorMessage;

	private static final long serialVersionUID = -7900659635841387010L;

	public InvoiceManagerException(String message, int HttpStatus) {
		super(message);
		this.errorMessage = new ErrorMessage(message, HttpStatus);
	}
	
	public InvoiceManagerException(String message, String developerMessage, int HttpStatus) {
		super(message);
		this.errorMessage = new ErrorMessage(message, developerMessage, HttpStatus);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
