package com.business.unknow.enums;

public enum FormaPagoEnum {
	CHEQUE_NORMATIVO(1, "Sin Cheque nominativo", "CHEQUE", "02"),
	TRANSFERENCIA_ELECTRONICA(2, "Transferencia electrónica de fondos", "TRANSFERENCIA", "03"),
	EFECTIVO(3, "Efectivo", "EFECTIVO", "01"),
	DEPOSITO(4, "Deposito Bancario", "DEPOSITO", "02"),
	CREDITO(5, "Credito Despacho", "CREDITO", "12"),
	NOT_VALID(6, "NOT_VALID", "NOT_VALID", "00");

	private Integer valor;
	private String descripcion;
	private String pagoValue;
	private String clave;

	private FormaPagoEnum(Integer valor, String descripcion, String pagoValue, String clave) {
		this.valor = valor;
		this.descripcion = descripcion;
		this.pagoValue = pagoValue;
		this.clave = clave;
	}

	public static FormaPagoEnum findByDesc(String descripcion) {
		for (FormaPagoEnum v : values()) {
			if (v.getDescripcion().equals(descripcion)) {
				return v;
			}
		}
		return NOT_VALID;
	}
	
	public static FormaPagoEnum findByPagoValue(String pagoValue) {
		for (FormaPagoEnum v : values()) {
			if (v.getPagoValue().equals(pagoValue)) {
				return v;
			}
		}
		return NOT_VALID;
	}
	
	public static FormaPagoEnum findByPagoClave(String clave) {
		for (FormaPagoEnum v : values()) {
			if (v.getClave().equals(clave)) {
				return v;
			}
		}
		return NOT_VALID;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPagoValue() {
		return pagoValue;
	}

	public String getClave() {
		return clave;
	}

}