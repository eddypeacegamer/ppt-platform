package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CFDI")
public class Cfdi implements Serializable {

	private static final long serialVersionUID = 6362879952092338829L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CFDI")
	private Integer id;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "SERIE")
	private String serie;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "SELLO")
	private String sello;

	@Column(name = "NO_CERTIFICADO")
	private String noCertificado;

	@Column(name = "CERTIFICADO")
	private String certificado;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "SUB_TOTAL")
	private BigDecimal subtotal;

	@Column(name = "DESCUENTO")
	private BigDecimal descuento;

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Column(name = "TIPO_COMPROBANTE")
	private String tipoDeComprobante;

	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Column(name = "CONDICIONES_PAGO")
	private String condicionesDePago;

	@Column(name = "LUGAR_EXPEDICION")
	private String lugarExpedicion;

	@OneToMany(mappedBy = "cfdi")
	private List<Concepto> conceptos;

	@OneToOne(mappedBy = "cfdi")
	private Complemento complemento;

	@OneToOne(mappedBy = "cfdi")
	private Receptor receptor;

	@OneToOne(mappedBy = "cfdi")
	private Emisor emisor;

	public Cfdi() {
		this.conceptos = new ArrayList<Concepto>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}

	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getCondicionesDePago() {
		return condicionesDePago;
	}

	public void setCondicionesDePago(String condicionesDePago) {
		this.condicionesDePago = condicionesDePago;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public Emisor getEmisor() {
		return emisor;
	}

	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	public Complemento getComplemento() {
		return complemento;
	}

	public void setComplemento(Complemento complemento) {
		this.complemento = complemento;
	}

	public Receptor getReceptor() {
		return receptor;
	}

	@Override
	public String toString() {
		return "Cfdi [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", sello=" + sello
				+ ", noCertificado=" + noCertificado + ", certificado=" + certificado + ", moneda=" + moneda
				+ ", subtotal=" + subtotal + ", descuento=" + descuento + ", total=" + total + ", tipoDeComprobante="
				+ tipoDeComprobante + ", metodoPago=" + metodoPago + ", formaPago=" + formaPago + ", condicionesDePago="
				+ condicionesDePago + ", lugarExpedicion=" + lugarExpedicion + ", conceptos=" + conceptos
				+ ", complemento=" + complemento + ", receptor=" + receptor + ", emisor=" + emisor + "]";
	}

}
