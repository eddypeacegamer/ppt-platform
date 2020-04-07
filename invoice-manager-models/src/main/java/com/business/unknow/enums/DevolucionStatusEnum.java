package com.business.unknow.enums;

public enum DevolucionStatusEnum {

	SIN_DEVOLVER(1,"Sin devolver"),
	DEVUELTA(2,"Devuelta"),
	PARCIALMENTE_DEVUELTA(3,"Parcialmente devuelta");

	private Integer valor;
	private String descripcion;
	

	private DevolucionStatusEnum(Integer valor, String descripcion) {
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
