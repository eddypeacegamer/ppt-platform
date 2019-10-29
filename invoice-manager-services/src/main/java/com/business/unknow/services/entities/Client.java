package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "CLIENTES")
public class Client implements Serializable {

	private static final long serialVersionUID = -491025321146807933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private int id;

	@Column(name = "ACTIVO")
	private Boolean activo;

	@Column(name = "PORCENTAJE_PROMOTOR")
	private Integer porcentajePromotor;

	@Column(name = "PORCENTAJE_CLIENTE")
	private Integer porcentajeCliente;

	@Column(name = "PORCENTAJE_DESPACHO")
	private Integer porcentajeDespacho;

	@Column(name = "PORCENTAJE_CONTACTO")
	private Integer porcentajeContacto;

	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RFC", referencedColumnName = "RFC")
	private Contribuyente informacionFiscal;

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

	public Contribuyente getInformacionFiscal() {
		return informacionFiscal;
	}

	public void setInformacionFiscal(Contribuyente informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}

	public Integer getPorcentajePromotor() {
		return porcentajePromotor;
	}

	public void setPorcentajePromotor(Integer porcentajePromotor) {
		this.porcentajePromotor = porcentajePromotor;
	}

	public Integer getPorcentajeCliente() {
		return porcentajeCliente;
	}

	public void setPorcentajeCliente(Integer porcentajeCliente) {
		this.porcentajeCliente = porcentajeCliente;
	}

	public Integer getPorcentajeDespacho() {
		return porcentajeDespacho;
	}

	public void setPorcentajeDespacho(Integer porcentajeDespacho) {
		this.porcentajeDespacho = porcentajeDespacho;
	}

	public Integer getPorcentajeContacto() {
		return porcentajeContacto;
	}

	public void setPorcentajeContacto(Integer porcentajeContacto) {
		this.porcentajeContacto = porcentajeContacto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Client [activo=" + activo + ", porcentajePromotor=" + porcentajePromotor + ", porcentajeCliente="
				+ porcentajeCliente + ", porcentajeDespacho=" + porcentajeDespacho + ", porcentajeContacto="
				+ porcentajeContacto + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion
				+ ", informacionFiscal=" + informacionFiscal + "]";
	}
}
