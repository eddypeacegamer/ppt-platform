package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CfdiPagoDto")
public class CfdiPagoDto implements Serializable {

	private static final long serialVersionUID = -7042373656454531924L;
	private int id;
	private String version;
	@XmlAttribute(name = "ComplentoFechaPago")
	private Date fechaPago;
	@XmlAttribute(name = "ComplentoFormaPago")
	private String formaPago;
	@XmlAttribute(name = "ComplentoMoneda")
	private String moneda;
	@XmlAttribute(name = "ComplentoMonto")
	private BigDecimal monto;
	@XmlAttribute(name = "ComplentoFolio")
	private String folio;
	@XmlAttribute(name = "ComplentoIdDocumento")
	private String idDocumento;
	@XmlAttribute(name = "ComplentoImportePagado")
	private BigDecimal importePagado;
	@XmlAttribute(name = "ComplentoImporteSaldoAnterior")
	private BigDecimal importeSaldoAnterior;
	@XmlAttribute(name = "ComplentoImporteSaldoInsoluto")
	private BigDecimal importeSaldoInsoluto;
	@XmlAttribute(name = "ComplentoMetodoPago")
	private String metodoPago;
	private String monedaDr;
	@XmlAttribute(name = "ComplentoNumeroParcialidad")
	private int numeroParcialidad;
	@XmlAttribute(name = "Serie")
	private String serie;
	private String montoDesc;
	private boolean valido;
	private CfdiDto cfdi;
	private BigDecimal tipoCambioDr;
	private BigDecimal tipoCambio;

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

	public String getMontoDesc() {
		return montoDesc;
	}

	public void setMontoDesc(String montoDesc) {
		this.montoDesc = montoDesc;
	}

	public CfdiDto getCfdi() {
		return cfdi;
	}

	public void setCfdi(CfdiDto cfdi) {
		this.cfdi = cfdi;
	}

	public BigDecimal getTipoCambioDr() {
		return tipoCambioDr;
	}

	public void setTipoCambioDr(BigDecimal tipoCambioDr) {
		this.tipoCambioDr = tipoCambioDr;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	@Override
	public String toString() {
		return "CfdiPagoDto [id=" + id + ", version=" + version + ", fechaPago=" + fechaPago + ", formaPago="
				+ formaPago + ", moneda=" + moneda + ", monto=" + monto + ", folio=" + folio + ", idDocumento="
				+ idDocumento + ", importePagado=" + importePagado + ", importeSaldoAnterior=" + importeSaldoAnterior
				+ ", importeSaldoInsoluto=" + importeSaldoInsoluto + ", metodoPago=" + metodoPago + ", monedaDr="
				+ monedaDr + ", numeroParcialidad=" + numeroParcialidad + ", serie=" + serie + ", montoDesc="
				+ montoDesc + ", valido=" + valido + ", cfdi=" + cfdi + ", tipoCambioDr=" + tipoCambioDr
				+ ", tipoCambio=" + tipoCambio + "]";
	}

}
