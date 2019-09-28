package com.business.unknow.model;

import java.io.Serializable;
import java.util.Date;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = 4951260411762447946L;

	private Integer id;
	private Boolean activo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private ContribuyenteDto informacionFiscal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ContribuyenteDto getInformacionFiscal() {
		return informacionFiscal;
	}

	public void setInformacionFiscal(ContribuyenteDto informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", activo=" + activo + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + ", informacionFiscal=" + informacionFiscal + "]";
	}
}
