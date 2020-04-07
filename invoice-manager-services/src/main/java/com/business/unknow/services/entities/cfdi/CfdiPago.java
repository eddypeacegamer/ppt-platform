package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CFDI_PAGOS")
public class CfdiPago implements Serializable {

	private static final long serialVersionUID = -9003721389303480808L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CFDI_PAGO")
	private int id;
	@Column(name = "VERSION")
	private String version;
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;
	@Column(name = "FORMA_PAGO")
	private String formaPago;
	@Column(name = "MONEDA")
	private String moneda;
	@Column(name = "MONTO")
	private BigDecimal monto;
	@Column(name = "FOLIO")
	private String folio;
	@Column(name = "ID_DOCUMENTO")
	private String idDocumento;
	@Column(name = "IMPORTE_PAGADO")
	private BigDecimal importePagado;
	@Column(name = "IMPORTE_SALDO_ANTERIOR")
	private BigDecimal importeSaldoAnterior;
	@Column(name = "IMPORTE_SALDO_INSOLUTO")
	private BigDecimal importeSaldoInsoluto;
	@Column(name = "METODO_PAGO")
	private String metodoPago;
	@Column(name = "MONEDA_DR")
	private String monedaDr;
	@Column(name = "NUM_PARCIALIDAD",columnDefinition = "TINYINT")
	private int numeroParcialidad;
	@Column(name = "SERIE")
	private String serie;
	@ManyToOne
	@JoinColumn(name = "ID_CFDI", nullable = false)
	private Cfdi cfdi;

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

	public Cfdi getCfdi() {
		return cfdi;
	}

	public void setCfdi(Cfdi cfdi) {
		this.cfdi = cfdi;
	}

	@Override
	public String toString() {
		return "CfdiPago [id=" + id + ", version=" + version + ", fechaPago=" + fechaPago + ", formaPago=" + formaPago
				+ ", moneda=" + moneda + ", monto=" + monto + ", folio=" + folio + ", idDocumento=" + idDocumento
				+ ", importePagado=" + importePagado + ", importeSaldoAnterior=" + importeSaldoAnterior
				+ ", importeSaldoInsoluto=" + importeSaldoInsoluto + ", metodoPago=" + metodoPago + ", monedaDr="
				+ monedaDr + ", numeroParcialidad=" + numeroParcialidad + ", serie=" + serie + ", cfdi=" + cfdi + "]";
	}

}
