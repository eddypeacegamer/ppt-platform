package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.Date;

public class CuentaBancariaDto implements Serializable {

	private static final long serialVersionUID = 8643631228668299142L;

	private int id;
	private String empresa;
	private String banco;
	private String cuenta;
	private String clave;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
		return "CuentaBancariaDto [id=" + id + ", empresa=" + empresa + ", banco=" + banco + ", cuenta=" + cuenta
				+ ", clave=" + clave + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion
				+ "]";
	}

}
