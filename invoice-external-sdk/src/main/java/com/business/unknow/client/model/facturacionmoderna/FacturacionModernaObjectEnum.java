package com.business.unknow.client.model.facturacionmoderna;

public enum FacturacionModernaObjectEnum {

	PDF(1, "pdf"), TXT(2, "txt"), PNG(3, "png"), XML(4, "xml"), NOT_VALID(4, "NOT_VALID");

	private Integer valor;
	private String descripcion;

	private FacturacionModernaObjectEnum(Integer valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static FacturacionModernaObjectEnum findByDescription(String nombre) {
		for (FacturacionModernaObjectEnum v : values()) {
			if (v.getDescripcion().equals(nombre)) {
				return v;
			}
		}
		return NOT_VALID;
	}
}
