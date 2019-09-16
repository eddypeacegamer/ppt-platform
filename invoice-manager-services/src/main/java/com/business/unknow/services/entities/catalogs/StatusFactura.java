package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_FACTURAS")
public class StatusFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STATUS_FACTURA")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "STATUS_EVENTO", unique = true)
	private String statusEvento;

	@Basic(optional = false)
	@Column(name = "STATUS_PAGO", unique = true)
	private String statusPago;

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

	@Override
	public String toString() {
		return "StatusFactura [id=" + id + ", statusEvento=" + statusEvento + ", statusPago=" + statusPago
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
