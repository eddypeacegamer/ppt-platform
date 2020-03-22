package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_FORMA_PAGO")
public class FormaPago implements Serializable {

	private static final long serialVersionUID = 8508424746268290927L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLAVE")
	private String id;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "SHORT_DESC")
	private String shortDescripcion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getShortDescripcion() {
		return shortDescripcion;
	}

	public void setShortDescripcion(String shortDescripcion) {
		this.shortDescripcion = shortDescripcion;
	}

	@Override
	public String toString() {
		return "FormaPago [id=" + id + ", descripcion=" + descripcion + ", shortDescripcion=" + shortDescripcion + "]";
	}

}
