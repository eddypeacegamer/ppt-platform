/**
 * 
 */
package com.business.unknow.services.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ralfdemoledor
 *
 *         Las devoluciones solo pueden ser creadas para facturas pagadas y
 *         timbradas, de otro modo no es posible crear una devolucion
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DEVOLUCIONES")
public class Devolucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DEVOLUCION")
	private Integer id;

	/*
	 * Este ID es llenado al momento de hacer el pago de la devolucion, el diseño
	 * permite que un pago pueda ligar multiples devoluciones.
	 */
	@Column(name = "ID_PAGO")
	private Integer idPagoOrigen;

	/*
	 * Folio de complemento si es PPD o folio factura si es PUE
	 */
	@NotNull
	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "PAGO_MONTO")
	private BigDecimal pagoMonto;

	@Column(name = "IMPUESTO")
	private BigDecimal impuesto;

	@Column(name = "PORCENTAJE")
	private BigDecimal porcentaje;

	@NotNull
	@Column(name = "MONTO")
	private BigDecimal monto;

	/*
	 * Entidad que recibe pago,en este caso es e correo de promotor,RFC cliente,
	 * correo contacto y CASA
	 */
	@NotNull
	@Column(name = "ID_RECEPTOR")
	private String receptor;

	/*
	 * CLIENTE,PROMOTOR,CONTACTO,CASA
	 */
	@NotNull
	@Column(name = "TIPO_RECEPTOR")
	private String tipoReceptor;

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

	public Integer getIdPagoOrigen() {
		return idPagoOrigen;
	}

	public void setIdPagoOrigen(Integer idPagoOrigen) {
		this.idPagoOrigen = idPagoOrigen;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getTipoReceptor() {
		return tipoReceptor;
	}

	public void setTipoReceptor(String tipoReceptor) {
		this.tipoReceptor = tipoReceptor;
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

	public BigDecimal getPagoMonto() {
		return pagoMonto;
	}

	public void setPagoMonto(BigDecimal pagoMonto) {
		this.pagoMonto = pagoMonto;
	}

	public BigDecimal getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Devolucion [id=" + id + ", idPagoOrigen=" + idPagoOrigen + ", tipo=" + tipo + ", folio=" + folio
				+ ", pagoMonto=" + pagoMonto + ", impuesto=" + impuesto + ", porcentaje=" + porcentaje + ", monto="
				+ monto + ", receptor=" + receptor + ", tipoReceptor=" + tipoReceptor + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
