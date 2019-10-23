package com.business.unknow.enums;

public enum MetodosPagoEnum {
	
	PUE("PUE"),
	PPT("PPT");
	
	private MetodosPagoEnum(String nombre) {
		this.nombre=nombre;
	}
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

}
