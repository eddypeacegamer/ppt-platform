package com.business.unknow.enums;

public enum TipoRelacionEnum {

	NOTA_CREDITO("01", "Nota de crédito de los documentos relacionados"),
	SUSTITUCION("04", "Sustitución de los CFDI previos"),
	NOT_VALID("99", "Not valid");

	private String id;
	private String valor;

	private TipoRelacionEnum(String id, String valor) {
		this.valor = valor;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static TipoRelacionEnum findById(String id) {
		for (TipoRelacionEnum v : values()) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return NOT_VALID;
	}

}