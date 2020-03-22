package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONTRIBUYENTES")
public class Contribuyente implements Serializable {

	private static final long serialVersionUID = -262866475157657093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTRIBUYENTE")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "RFC")
	private String rfc;

	@Column(name = "GIRO")
	private String giro;
	
	@Column(name = "MORAL")
	private boolean moral;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CURP")
	private String curp;

	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	@Column(name = "CALLE")
	private String calle;

	@Column(name = "NO_EXTERIOR")
	private String noExterior;

	@Column(name = "NO_INTERIOR")
	private String noInterior;

	@Column(name = "MUNICIPIO")
	private String municipio;

	@Column(name = "LOCALIDAD")
	private String localidad;

	@Column(name = "ESTADO")
	private String estado;

	@Column(name = "PAIS")
	private String pais;

	@Column(name = "COO")
	private String coo;

	@Column(name = "CODIGO_POSTAL")
	private String cp;

	@Basic(optional = false)
	@Column(name = "CORREO")
	private String correo;

	@Column(name = "TELEFONO")
	private String telefono;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
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

	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		return "Contribuyente [rfc=" + rfc + ", giro=" + giro + ", nombre=" + nombre + ", curp=" + curp
				+ ", razonSocial=" + razonSocial + ", calle=" + calle + ", noExterior=" + noExterior + ", noInterior="
				+ noInterior + ", municipio=" + municipio + ", localidad=" + localidad + ", estado=" + estado
				+ ", pais=" + pais + ", coo=" + coo + ", cp=" + cp + ", correo=" + correo + ", telefono=" + telefono
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
