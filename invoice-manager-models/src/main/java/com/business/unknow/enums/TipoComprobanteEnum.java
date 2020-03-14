package com.business.unknow.enums;

public enum TipoComprobanteEnum {

	INGRESO("I", "Ingreso"), EGRESO("E", "Egreso"), TRASLADO("T", "traslado"), PAGO("P", "Pago"), NOMINA("N", "Nomina");

	private String valor;
	private String descripcion;

	private TipoComprobanteEnum(String valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoComprobanteEnum findByValor(String valor) {
		for (TipoComprobanteEnum v : values()) {
			if (v.getValor().equals(valor)) {
				return v;
			}
		}
		return INGRESO;
	}

}
