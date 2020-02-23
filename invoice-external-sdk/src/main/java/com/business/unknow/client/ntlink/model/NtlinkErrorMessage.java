package com.business.unknow.client.ntlink.model;

public class NtlinkErrorMessage {

	private String message;
	private String messageDetail;

	public NtlinkErrorMessage() {
	}

	public NtlinkErrorMessage(String message, String messageDetail) {
		this.message = message;
		this.messageDetail = messageDetail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	@Override
	public String toString() {
		return "FacturaModernaErrorMessage [message=" + message + ", messageDetail=" + messageDetail + "]";
	}
}
