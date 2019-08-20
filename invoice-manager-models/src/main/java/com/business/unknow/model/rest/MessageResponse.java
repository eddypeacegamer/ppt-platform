package com.business.unknow.model.rest;


/**
* @author eej000f
*
*/
public class MessageResponse {
	private String name;

	public MessageResponse() {
	}

	public MessageResponse(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}