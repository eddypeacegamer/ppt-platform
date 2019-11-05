package com.business.unknow.model.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author eej000f
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage implements Serializable{

	private static final long serialVersionUID = -5565590990090533616L;
	private String developerMessage;
	private int httpStatus;
	private String message;

	public ErrorMessage() {
	}

	public ErrorMessage(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorMessage(String message, int httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public ErrorMessage(String message, String developerMessage, int httpStatus) {
		this.message = message;
		this.developerMessage = developerMessage;
		this.httpStatus = httpStatus;
	}

	public ErrorMessage(String message, String developerMessage) {
		this.message = message;
		this.developerMessage = developerMessage;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	@Override
	public String toString() {
		return "ErrorMessage [developerMessage=" + developerMessage + ", httpStatus=" + httpStatus + ", message="
				+ message + "]";
	}

}