package com.business.unknow.model.cfdi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.business.unknow.Constants.CfdiConstants;

@XmlRootElement(name = "Comprobante", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.NONE)
public class Cfdi {

	@XmlAttribute(name = "Version")
	private String version = CfdiConstants.FACTURA_VERSION;
	@XmlAttribute(name = "Serie")
	private String serie;
	@XmlAttribute(name = "Folio")
	private String folio;
	@XmlAttribute(name = "Fecha")
	private String fecha;
	@XmlAttribute(name = "Sello")
	private String sello;
	@XmlAttribute(name = "FormaPago")
	private String formaPago;
	@XmlAttribute(name = "NoCertificado")
	private String noCertificado;
	@XmlAttribute(name = "Certificado")
	private String certificado;
	@XmlAttribute(name = "SubTotal")
	private BigDecimal subtotal;
	@XmlAttribute(name = "Descuento")
	private BigDecimal descuento;
	@XmlAttribute(name = "TipoCambio")
	private BigDecimal tipoCambio;
	@XmlAttribute(name = "Moneda")
	private String moneda;
	@XmlAttribute(name = "Total")
	private BigDecimal total;
	@XmlAttribute(name = "TipoDeComprobante")
	private String tipoDeComprobante;
	@XmlAttribute(name = "MetodoPago")
	private String metodoPago;
	@XmlAttribute(name = "LugarExpedicion")
	private String lugarExpedicion;
	@XmlElement(name = "Emisor", namespace = "http://www.sat.gob.mx/cfd/3")
	private Emisor emisor;
	@XmlElement(name = "Receptor", namespace = "http://www.sat.gob.mx/cfd/3")
	private Receptor receptor;
	@XmlElementWrapper(name = "Conceptos", namespace = "http://www.sat.gob.mx/cfd/3")
	@XmlElement(name = "Concepto", namespace = "http://www.sat.gob.mx/cfd/3")
	private List<Concepto> conceptos;
	@XmlElement(name = "Impuestos", namespace = "http://www.sat.gob.mx/cfd/3")
	private Impuesto impuestos;
	@XmlElement(name = "Complemento", namespace = "http://www.sat.gob.mx/cfd/3")
	private Complemento complemento;

	public Cfdi() {
		this.conceptos = new ArrayList<>();
		this.impuestos = new Impuesto();
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
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

	public Receptor getReceptor() {
		return receptor;
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

	public Impuesto getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Impuesto impuestos) {
		this.impuestos = impuestos;
	}

	public Complemento getComplemento() {
		return complemento;
	}

	public void setComplemento(Complemento complemento) {
		this.complemento = complemento;
	}
	

	@Override
	public String toString() {
		return "Cfdi [version=" + version + ", serie=" + serie + ", folio=" + folio + ", fecha=" + fecha + ", sello="
				+ sello + ", formaPago=" + formaPago + ", noCertificado=" + noCertificado + ", certificado="
				+ certificado + ", subtotal=" + subtotal + ", descuento=" + descuento + ", moneda=" + moneda
				+ ", total=" + total + ", tipoDeComprobante=" + tipoDeComprobante + ", metodoPago=" + metodoPago
				+ ", lugarExpedicion=" + lugarExpedicion + ", emisor=" + emisor + ", receptor=" + receptor
				+ ", conceptos=" + conceptos + ", impuestos=" + impuestos + ", complemento=" + complemento + "]";
	}
	
}
