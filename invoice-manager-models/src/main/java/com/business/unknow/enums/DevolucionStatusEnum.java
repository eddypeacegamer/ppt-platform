package com.business.unknow.enums;

public enum DevolucionStatusEnum {

	SIN_DEVOLVER("Sin devolver"),
	DEVUELTA("Devuelta"),
	PARCIALMENTE_DEVUELTA("Parcialmente devuelta");

	private String valor;

	private DevolucionStatusEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
