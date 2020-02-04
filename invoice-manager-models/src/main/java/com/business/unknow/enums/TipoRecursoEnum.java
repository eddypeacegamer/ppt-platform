package com.business.unknow.enums;

public enum TipoRecursoEnum {

	EMPRESA(1, "EMPRESA"),
	FACTURA(2, "FACTURA"),
	PAGO(3, "PAGO");

	private Integer valor;
	private String descripcion;

	private TipoRecursoEnum(Integer valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
