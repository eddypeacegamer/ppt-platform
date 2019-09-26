package com.business.unknow.model;

import java.io.Serializable;
import java.util.Date;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = 4951260411762447946L;

	private Integer id;
	private String rfc;
	private Boolean activo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", rfc=" + rfc  + ", activo=" + activo
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
