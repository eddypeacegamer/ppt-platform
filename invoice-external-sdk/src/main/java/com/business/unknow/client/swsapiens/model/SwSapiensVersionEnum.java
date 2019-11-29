package com.business.unknow.client.swsapiens.model;

public enum SwSapiensVersionEnum {

	V1("v1"), V2("v2"), V3("v3"), V4("v4");

	private String value;

	private SwSapiensVersionEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
