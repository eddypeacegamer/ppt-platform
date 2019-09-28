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
	@Column(name = "ID_CLIENT")
	private Integer id;

	@Column(name = "ACTIVO")
	private Boolean activo;

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

	public Contribuyente getInformacionFiscal() {
		return informacionFiscal;
	}

	public void setInformacionFiscal(Contribuyente informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", activo=" + activo + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + ", informacionFiscal=" + informacionFiscal + "]";
	}
}
