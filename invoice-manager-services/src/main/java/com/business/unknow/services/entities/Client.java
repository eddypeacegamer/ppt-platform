package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Client implements Serializable {

	private static final long serialVersionUID = -491025321146807933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENT")
	private Integer id;

	@JoinColumn(name = "ID_PROMOTOR", referencedColumnName = "ID_PROMOTOR")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Promotor promotor;
	
	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String name;

	@Basic(optional = false)
	@Column(name = "RFC")
	private String rfc;

	@Basic(optional = false)
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	@Basic(optional = false)
	@Column(name = "CORREO")
	private String email;

	@Basic(optional = false)
	@Column(name = "CALLE")
	private String calle;

	@Basic(optional = false)
	@Column(name = "COLONIA")
	private String colonia;

	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;

	@Basic(optional = false)
	@Column(name = "COO")
	private String coo;

	@Basic(optional = false)
	@Column(name = "NO_INTERIOR")
	private String noInterior;

	@Basic(optional = false)
	@Column(name = "NO_EXTERIOR")
	private String noExterior;

	@Basic(optional = false)
	@Column(name = "MUNICIPIO")
	private String municipio;

	@Basic(optional = false)
	@Column(name = "PAIS")
	private String pais;

	@Basic(optional = false)
	@Column(name = "CODIGO_POSTAL")
	private String codigoPostal;

	@Basic(optional = false)
	@Column(name = "ACTIVO")
	private Boolean activo;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
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
		return "Client [id=" + id + ", promotor=" + promotor + ", name=" + name + ", rfc=" + rfc + ", razonSocial="
				+ razonSocial + ", email=" + email + ", calle=" + calle + ", colonia=" + colonia + ", estado=" + estado
				+ ", coo=" + coo + ", noInterior=" + noInterior + ", noExterior=" + noExterior + ", municipio="
				+ municipio + ", pais=" + pais + ", codigoPostal=" + codigoPostal + ", activo=" + activo
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
