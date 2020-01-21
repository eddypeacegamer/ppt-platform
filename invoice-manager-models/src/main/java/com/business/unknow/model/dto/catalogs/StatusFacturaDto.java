package com.business.unknow.model.dto.catalogs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusFacturaDto implements Serializable {

	private static final long serialVersionUID = 6605485841161408524L;

	private Integer id;
	private String statusEvento;
	private String statusPago;
	private String statusDevolucion;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusEvento() {
		return statusEvento;
	}

	public void setStatusEvento(String statusEvento) {
		this.statusEvento = statusEvento;
	}

	public String getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(String statusPago) {
		this.statusPago = statusPago;
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

	public String getStatusDevolucion() {
		return statusDevolucion;
	}

	public void setStatusDevolucion(String statusDevolucion) {
		this.statusDevolucion = statusDevolucion;
	}

	@Override
	public String toString() {
		return "StatusFacturaDto [id=" + id + ", statusEvento=" + statusEvento + ", statusPago=" + statusPago
				+ ", statusDevolucion=" + statusDevolucion + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
