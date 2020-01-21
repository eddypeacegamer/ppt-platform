package com.business.unknow.model.dto.catalogs;

import java.io.Serializable;

public class BancoDto implements Serializable {

	private static final long serialVersionUID = 8689736142808905667L;

	private Integer clave;
	private String nombre;

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "BancoDto [clave=" + clave + ", nombre=" + nombre + "]";
	}

}
