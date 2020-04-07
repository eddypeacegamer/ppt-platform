package com.business.unknow.client.swsapiens.model;

public class SwSapiensErrorMessage {

	private String message;
	private String messageDetail;
	private String data;
	private String status;

	public SwSapiensErrorMessage(String message, String messageDetail) {
		super();
		this.message = message;
		this.messageDetail = messageDetail;
	}

	public SwSapiensErrorMessage(String message, String messageDetail, String data, String status) {
		super();
		this.message = message;
		this.messageDetail = messageDetail;
		this.data = data;
		this.status = status;
	}

	public SwSapiensErrorMessage() {
		super();
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SwSapiensErrorMessage [message=" + message + ", messageDetail=" + messageDetail + ", data=" + data
				+ ", status=" + status + "]";
	}

}
