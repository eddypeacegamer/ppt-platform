package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private String folioPadre;
	private String moneda;
	private String banco;
	private String cuenta;
	private BigDecimal tipoDeCambio;
	private String formaPago;
	private BigDecimal monto;
	private BigDecimal total;
	private String acredor;
	private String deudor;
	private String statusPago;
	private String comentarioPago;
	private String solicitante;
	private Boolean revision1;
	private Boolean revision2;
	private String revisor1;
	private String revisor2;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaPago;
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
	public String getFolioPadre() {
		return folioPadre;
	}
	public void setFolioPadre(String folioPadre) {
		this.folioPadre = folioPadre;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public BigDecimal getTipoDeCambio() {
		return tipoDeCambio;
	}
	public void setTipoDeCambio(BigDecimal tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getAcredor() {
		return acredor;
	}
	public void setAcredor(String acredor) {
		this.acredor = acredor;
	}
	public String getDeudor() {
		return deudor;
	}
	public void setDeudor(String deudor) {
		this.deudor = deudor;
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
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
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
	public String getRevisor1() {
		return revisor1;
	}
	public void setRevisor1(String revisor1) {
		this.revisor1 = revisor1;
	}
	public String getRevisor2() {
		return revisor2;
	}
	public void setRevisor2(String revisor2) {
		this.revisor2 = revisor2;
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
	@Override
	public String toString() {
		return "PagoDto [id=" + id + ", folio=" + folio + ", folioPadre=" + folioPadre + ", moneda=" + moneda
				+ ", banco=" + banco + ", cuenta=" + cuenta + ", tipoDeCambio=" + tipoDeCambio + ", formaPago="
				+ formaPago + ", monto=" + monto + ", acredor=" + acredor + ", deudor=" + deudor + ", statusPago="
				+ statusPago + ", comentarioPago=" + comentarioPago + ", solicitante=" + solicitante + ", revision1="
				+ revision1 + ", revision2=" + revision2 + ", revisor1=" + revisor1 + ", revisor2=" + revisor2
				+ ", fechaPago=" + fechaPago + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}
}
