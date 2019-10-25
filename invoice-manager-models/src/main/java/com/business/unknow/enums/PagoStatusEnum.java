package com.business.unknow.enums;

public enum PagoStatusEnum {
	
	SIN_PAGAR("Sin pagar"),
	PAGADA("Pagada"),
	PARCIALMENTE_PAGADA("Parcialmente pagada");

	private String valor;

	private PagoStatusEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
