/**
 * 
 */
package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ralfdemoledor
 *
 */
public class DevolucionDto implements Serializable {

	private static final long serialVersionUID = 6166507072002168875L;

	private Integer id;
	private Integer idPagoOrigen;
	private String tipo;
	private String folio;
	private BigDecimal pagoMonto;
	private BigDecimal impuesto;
	private BigDecimal porcentaje;
	private BigDecimal monto;
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
		return "DevolucionDto [id=" + id + ", idPagoOrigen=" + idPagoOrigen + ", tipo=" + tipo + ", folio=" + folio
				+ ", pagoMonto=" + pagoMonto + ", impuesto=" + impuesto + ", porcentaje=" + porcentaje + ", monto="
				+ monto + ", receptor=" + receptor + ", tipoReceptor=" + tipoReceptor + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
