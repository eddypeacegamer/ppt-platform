package com.business.unknow.client.facturacionmoderna.util;

import com.business.unknow.client.facturacionmoderna.model.FacturaModernaErrorMessage;

public class FacturaModernaClientException extends Exception {

	private static final long serialVersionUID = -6806226126010278250L;
	private FacturaModernaErrorMessage errorMessage;
	private Integer httpStatus;

	public FacturaModernaClientException() {
		super();
	}
	
	public FacturaModernaClientException(String message) {
		super(message);
	}
	
	public FacturaModernaClientException(FacturaModernaErrorMessage errorMessage, Integer httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public FacturaModernaErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(FacturaModernaErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "SwSapiensClientException [errorMessage=" + errorMessage + ", httpStatus=" + httpStatus + "]";
	}

}