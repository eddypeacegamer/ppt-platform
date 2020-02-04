package com.business.unknow.enums;

public enum TipoArchivoEnum {

	XML(1, "XML"),
	QR(2, "QR"),
	PDF(3, "PDF");

	private Integer valor;
	private String descripcion;

	private TipoArchivoEnum(Integer valor, String descripcion) {
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
