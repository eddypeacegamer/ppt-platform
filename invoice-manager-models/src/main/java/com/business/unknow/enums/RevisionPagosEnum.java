package com.business.unknow.enums;

public enum RevisionPagosEnum {

	VALIDACION(1, "VALIDACION"), ACEPTADO(2, "ACEPTADO");

	private Integer valor;
	private String descripcion;

	private RevisionPagosEnum(Integer valor, String descripcion) {
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
