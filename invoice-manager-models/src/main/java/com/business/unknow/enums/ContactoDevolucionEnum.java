package com.business.unknow.enums;

public enum ContactoDevolucionEnum {
	
	CONTACTO(1,"CONTACTO"),
	CLIENTE(2,"CLIENTE"),
	PROMOTOR(3,"PROMOTOR"),
	DESPACHO(4,"DESPACHO");

	private Integer valor;
	private String descripcion;
	

	private ContactoDevolucionEnum(Integer valor, String descripcion) {
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
