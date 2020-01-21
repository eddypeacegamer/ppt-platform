package com.business.unknow.enums;

public enum PackFacturarionEnum {

	SW_SAPIENS, FACTURACION_MODERNA, NOT_VALID;

	private PackFacturarionEnum() {
	}

	public static PackFacturarionEnum findByNombre(String nombre) {
		for (PackFacturarionEnum v : values()) {
			if (v.name().equals(nombre)) {
				return v;
			}
		}
		return NOT_VALID;
	}
}
