package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CfdiPagoDto implements Serializable {

	private static final long serialVersionUID = -7042373656454531924L;
	private int id;
	private String version;
	private Date fechaPago;
	private String formaPago;
	private String moneda;
	private BigDecimal monto;
	private String folio;
	private String idDocumento;
	private BigDecimal importePagado;
	private BigDecimal importeSaldoAnterior;
	private BigDecimal importeSaldoInsoluto;
	private String metodoPago;
	private String monedaDr;
	private int numeroParcialidad;
	private String serie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public BigDecimal getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(BigDecimal importePagado) {
		this.importePagado = importePagado;
	}

	public BigDecimal getImporteSaldoAnterior() {
		return importeSaldoAnterior;
	}

	public void setImporteSaldoAnterior(BigDecimal importeSaldoAnterior) {
		this.importeSaldoAnterior = importeSaldoAnterior;
	}

	public BigDecimal getImporteSaldoInsoluto() {
		return importeSaldoInsoluto;
	}

	public void setImporteSaldoInsoluto(BigDecimal importeSaldoInsoluto) {
		this.importeSaldoInsoluto = importeSaldoInsoluto;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getMonedaDr() {
		return monedaDr;
	}

	public void setMonedaDr(String monedaDr) {
		this.monedaDr = monedaDr;
	}

	public int getNumeroParcialidad() {
		return numeroParcialidad;
	}

	public void setNumeroParcialidad(int numeroParcialidad) {
		this.numeroParcialidad = numeroParcialidad;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "CfdiPagoDto [id=" + id + ", version=" + version + ", fechaPago=" + fechaPago + ", formaPago="
				+ formaPago + ", moneda=" + moneda + ", monto=" + monto + ", folio=" + folio + ", idDocumento="
				+ idDocumento + ", importePagado=" + importePagado + ", importeSaldoAnterior=" + importeSaldoAnterior
				+ ", importeSaldoInsoluto=" + importeSaldoInsoluto + ", metodoPago=" + metodoPago + ", monedaDr="
				+ monedaDr + ", numeroParcialidad=" + numeroParcialidad + ", serie=" + serie + "]";
	}

}
