package com.business.unknow.client.ntlink.model;

public class NtlinkClientException  extends Exception {

	private static final long serialVersionUID = 2169841323231803594L;
	private NtlinkErrorMessage errorMessage;
	private Integer httpStatus;

	public NtlinkClientException(NtlinkErrorMessage errorMessage, Integer httpStatus) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public NtlinkErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(NtlinkErrorMessage errorMessage) {
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
		return "NtlinkClientException [errorMessage=" + errorMessage + ", httpStatus=" + httpStatus + "]";
	}
}
