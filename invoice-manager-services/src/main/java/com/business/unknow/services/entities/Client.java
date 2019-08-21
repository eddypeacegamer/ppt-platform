package com.business.unknow.services.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = -491025321146807933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "name", unique = true)
	private String name;

	@Basic(optional = false)
	@Column(name = "rfc", unique = true)
	private String rfc;

	@Basic(optional = false)
	@Column(name = "razon_social", unique = true)
	private String razonSocial;

	@Basic(optional = false)
	@Column(name = "email", unique = true)
	private String email;

	@Basic(optional = false)
	@Column(name = "calle", unique = true)
	private String calle;

	@Basic(optional = false)
	@Column(name = "colonia", unique = true)
	private String colonia;

	@Basic(optional = false)
	@Column(name = "estado", unique = true)
	private String estado;

	@Basic(optional = false)
	@Column(name = "coo", unique = true)
	private String coo;

	@Basic(optional = false)
	@Column(name = "no_interior", unique = true)
	private String noInterior;

	@Basic(optional = false)
	@Column(name = "no_exterior", unique = true)
	private String noExterior;

	@Basic(optional = false)
	@Column(name = "municipio", unique = true)
	private String municipio;

	@Basic(optional = false)
	@Column(name = "pais", unique = true)
	private String pais;

	@Basic(optional = false)
	@Column(name = "codigo_postal", unique = true)
	private String codigoPostal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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
		return "Client [id=" + id + ", name=" + name + ", rfc=" + rfc + ", razonSocial=" + razonSocial + ", email="
				+ email + ", calle=" + calle + ", colonia=" + colonia + ", estado=" + estado + ", coo=" + coo
				+ ", noInterior=" + noInterior + ", noExterior=" + noExterior + ", municipio=" + municipio + ", pais="
				+ pais + ", codigoPostal=" + codigoPostal + "]";
	}

}
