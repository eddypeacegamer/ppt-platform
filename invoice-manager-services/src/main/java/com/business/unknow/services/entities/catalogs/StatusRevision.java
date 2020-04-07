package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

//@Entity
//@Table(name = "STATUS_REVISION")
@Deprecated
public class StatusRevision implements Serializable {

	private static final long serialVersionUID = -3976447705621634661L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "ID_STATUS_REVISION")
	private Integer id;

	//@Column(name = "VALUE")
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
		return "StatusRevision [id=" + id + ", nombre=" + nombre + "]";
	}

}
