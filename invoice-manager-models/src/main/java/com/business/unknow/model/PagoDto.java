package com.business.unknow.model;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoDto implements Serializable {

	private static final long serialVersionUID = -8495281362684756977L;

	private Integer id;
	private String folio;
	private String moneda;
	private String banco;
	private Double monto;
	private String statusPago;
	private Boolean revision1;
	private Boolean revision2;
	private String comentarioPago;
	private String tipoPago;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaPago;
	private String documento;
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
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

	public String getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(String statusPago) {
		this.statusPago = statusPago;
	}

	public String getComentarioPago() {
		return comentarioPago;
	}

	public void setComentarioPago(String comentarioPago) {
		this.comentarioPago = comentarioPago;
	}

	public Boolean getRevision1() {
		return revision1;
	}

	public void setRevision1(Boolean revision1) {
		this.revision1 = revision1;
	}

	public Boolean getRevision2() {
		return revision2;
	}

	public void setRevision2(Boolean revision2) {
		this.revision2 = revision2;
	}

	@Override
	public String toString() {
		return "PagoDto [id=" + id + ", folio=" + folio + ", moneda=" + moneda + ", banco=" + banco + ", monto=" + monto
				+ ", statusPago=" + statusPago + ", revision1=" + revision1 + ", revision2=" + revision2
				+ ", comentarioPago=" + comentarioPago + ", tipoPago=" + tipoPago + ", fechaPago=" + fechaPago
				+ ", documento=" + documento + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}

}
