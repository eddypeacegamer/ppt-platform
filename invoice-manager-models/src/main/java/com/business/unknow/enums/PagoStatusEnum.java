package com.business.unknow.enums;

public enum PagoStatusEnum {
	
	SIN_PAGAR(1,"Sin pagar"),
	PAGADA(2,"Pagada"),
	PARCIALMENTE_PAGADA(3,"Parcialmente pagada");

	private Integer valor;
	private String descripcion;

	private PagoStatusEnum(Integer valor, String descripcion) {
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
