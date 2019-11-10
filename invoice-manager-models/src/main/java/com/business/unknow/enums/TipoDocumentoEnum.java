package com.business.unknow.enums;

public enum TipoDocumentoEnum {

	FACRTURA(1,"Factura"),
	COMPLEMENTO(2,"Complemento"),
	PREGUNTAR(3,"PREGUNTAR");

	private Integer valor;
	private String descripcion;

	private TipoDocumentoEnum(Integer valor, String descripcion) {
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
