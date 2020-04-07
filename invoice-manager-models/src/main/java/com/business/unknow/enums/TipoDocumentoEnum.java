package com.business.unknow.enums;

public enum TipoDocumentoEnum {

	FACTURA(1,"Factura"),
	COMPLEMENTO(2,"Complemento"),
	PREGUNTAR(3,"PREGUNTAR"),
	NOT_VALID(0,"NOT_VALID");

	private Integer valor;
	private String descripcion;

	private TipoDocumentoEnum(Integer valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoDocumentoEnum findByDesc(String nombre) {
		for (TipoDocumentoEnum v : values()) {
			if (v.getDescripcion().equals(nombre)) {
				return v;
			}
		}
		return NOT_VALID;
	}
}
