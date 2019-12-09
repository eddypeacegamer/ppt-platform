package com.business.unknow.enums;

public enum BancoEnum {
	
	BBVA(1,"BBVA"),
	HSBC(2,"HSBC"),
	BANORTE(3,"BANORTE"),
	BANAMEX(4,"BANAMEX"),
	NO_APLICA(5,"N/A");

	private Integer valor;
	private String descripcion;
	

	private BancoEnum(Integer valor, String descripcion) {
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
