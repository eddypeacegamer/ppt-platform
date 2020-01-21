package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BANCOS")
public class Banco implements Serializable{

	private static final long serialVersionUID = 5619170839532161430L;

	@Id
	@Column(name = "ID_BANCO")
	private Integer clave;

	@Column(name = "NOMBRE")
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
		return "Banco [clave=" + clave + ", nombre=" + nombre + "]";
	}

}
