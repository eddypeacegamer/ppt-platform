package com.business.unknow.model.catalogs;

import java.io.Serializable;

public class CodigoPostalDto implements Serializable {

	private static final long serialVersionUID = -2907308795601544511L;
	private int id;
	private int codigoPostal;
	private String estado;
	private String municipio;
	private String ciudad;
	private String colonia;

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CodigoPostalDto [codigoPostal=" + codigoPostal + ", estado=" + estado + ", municipio=" + municipio
				+ ", ciudad=" + ciudad + ", colonia=" + colonia + "]";
	}

}
