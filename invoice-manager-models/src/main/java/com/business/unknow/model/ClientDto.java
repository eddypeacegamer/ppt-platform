package com.business.unknow.model;

import java.io.Serializable;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = 4951260411762447946L;

	private Integer id;

	private String name;

	private String rfc;

	private String razonSocial;

	private String email;

	private String calle;

	private String colonia;

	private String estado;

	private String coo;

	private String noInterior;

	private String noExterior;

	private String municipio;

	private String pais;

	private String codigoPostal;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", name=" + name + ", rfc=" + rfc + ", razonSocial=" + razonSocial + ", email="
				+ email + ", calle=" + calle + ", colonia=" + colonia + ", estado=" + estado + ", coo=" + coo
				+ ", noInterior=" + noInterior + ", noExterior=" + noExterior + ", municipio=" + municipio + ", pais="
				+ pais + ", codigoPostal=" + codigoPostal + "]";
	}

}
