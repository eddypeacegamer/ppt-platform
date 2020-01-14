package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CODIGO_POSTAL")
public class CodigoPostal implements Serializable {

	private static final long serialVersionUID = 3999575917505652397L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "CODIGO_POSTAL")
	private int codigoPostal;
	@Column(name = "ESTADO")
	private String estado;
	@Column(name = "MUNICIPIO")
	private String municipio;
	@Column(name = "CIUDAD")
	private String ciudad;
	@Column(name = "COLONIA")
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
		return "CodigoPostal [codigoPostal=" + codigoPostal + ", estado=" + estado + ", municipio=" + municipio
				+ ", ciudad=" + ciudad + ", colonia=" + colonia + "]";
	}

}
