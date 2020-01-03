/**
 * 
 */
package com.business.unknow.model;

import java.io.Serializable;
import java.util.Date;
import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *@author ralfdemoledor
 *
 */
public class DevolucionDto implements Serializable {
	

	private static final long serialVersionUID = 6166507072002168875L;
	
	private Integer id;
	private Integer idPagoOrigen;
	private Integer idPagoDestino;
	private String folio;
	private String statusDevolucion;
	private Double monto;
	private String receptor;
	private String tipoReceptor;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
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
	public Integer getIdPagoDestino() {
		return idPagoDestino;
	}
	public void setIdPagoDestino(Integer idPagoDestino) {
		this.idPagoDestino = idPagoDestino;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getStatusDevolucion() {
		return statusDevolucion;
	}
	public void setStatusDevolucion(String statusDevolucion) {
		this.statusDevolucion = statusDevolucion;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
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
	@Override
	public String toString() {
		return "DevolucionDto [id=" + id + ", idPagoOrigen=" + idPagoOrigen + ", idPagoDestino=" + idPagoDestino
				+ ", folio=" + folio + ", statusPago=" + statusDevolucion + ", monto=" + monto + ", receptor=" + receptor
				+ ", tipoReceptor=" + tipoReceptor + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}
}
