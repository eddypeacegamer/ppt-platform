package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_GIROS")
public class Giro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_GIRO")
	private Integer id;

	@Column(name = "NOMBRE")
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Giro [id=" + id + ", nombre=" + nombre + "]";
	}

}
