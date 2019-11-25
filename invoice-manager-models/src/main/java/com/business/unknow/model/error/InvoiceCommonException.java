package com.business.unknow.model.error;

public class InvoiceCommonException extends Exception {

	private static final long serialVersionUID = 4248038391374547660L;

	private final transient ErrorMessage errorMessage;

	public InvoiceCommonException(String message, String developerMessage) {
		super(message);
		this.errorMessage = new ErrorMessage(message, developerMessage);
	}

	public InvoiceCommonException(String message) {
		super(message);
		this.errorMessage = new ErrorMessage(message);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
