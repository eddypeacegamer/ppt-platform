package com.business.unknow.enums;

public enum MetodosPagoEnum {
	
	PUE("PUE"),
	PPD("PPD");
	
	private MetodosPagoEnum(String nombre) {
		this.nombre=nombre;
	}
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

}
