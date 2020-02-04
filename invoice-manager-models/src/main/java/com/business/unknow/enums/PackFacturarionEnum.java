package com.business.unknow.enums;

public enum PackFacturarionEnum {

	SW_SAPIENS("SW_SAPIENS"), FACTURACION_MODERNA("FACTURACION_MODERNA"), NOT_VALID("NOT_VALID");

	private PackFacturarionEnum(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public static PackFacturarionEnum findByNombre(String nombre) {
		for (PackFacturarionEnum v : values()) {
			if (v.getNombre().equals(nombre)) {
				return v;
			}
		}
		return NOT_VALID;
	}
}
