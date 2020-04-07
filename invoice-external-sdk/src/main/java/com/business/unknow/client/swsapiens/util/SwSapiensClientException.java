package com.business.unknow.client.swsapiens.util;

import com.business.unknow.client.swsapiens.model.SwSapiensErrorMessage;

public class SwSapiensClientException extends Exception {

	private static final long serialVersionUID = -6806226126010278250L;
	private SwSapiensErrorMessage errorMessage;
	private Integer httpStatus;

	public SwSapiensClientException(SwSapiensErrorMessage errorMessage, Integer httpStatus) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public SwSapiensErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(SwSapiensErrorMessage errorMessage) {
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
