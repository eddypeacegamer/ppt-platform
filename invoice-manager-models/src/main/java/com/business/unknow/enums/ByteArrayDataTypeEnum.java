package com.business.unknow.enums;

public enum ByteArrayDataTypeEnum {

	PDF("application/pdf"),
	XML("text/plain");

	private String valor;


	private ByteArrayDataTypeEnum(String valor) {
		this.valor = valor;
	}


	public String getValor() {
		return valor;
	}
}