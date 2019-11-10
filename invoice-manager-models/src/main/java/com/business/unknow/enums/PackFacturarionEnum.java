package com.business.unknow.enums;

public enum PackFacturarionEnum {

	SW_SAPIENS("SW_SAPIENS"),
	FACTURACION_MODERNA("FACTURACION_MODERNA");
	
	private PackFacturarionEnum(String nombre) {
		this.nombre=nombre;
	}
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}
}
