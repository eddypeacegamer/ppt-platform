package com.business.unknow.enums;

public enum ResourceFileEnum {

	CERT(1, "CERT"),
	KEY(2, "KEY"),
	LOGO(3, "LOGO"),
	IMAGEN(4, "IMAGEN");

	private Integer valor;
	private String descripcion;

	private ResourceFileEnum(Integer valor, String descripcion) {
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
