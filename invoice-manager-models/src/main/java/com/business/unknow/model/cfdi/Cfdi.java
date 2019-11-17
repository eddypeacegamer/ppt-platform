package com.business.unknow.model.cfdi;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.business.unknow.Constants.CfdiConstants;

@XmlRootElement(name = "cfdi:Comprobante")
@XmlType(propOrder = { "version", "serie", "folio", "sello", "noCertificado", "certificado", "subtotal", "descuento",
		"moneda", "total", "tipoDeComprobante", "metodoPago", "lugarExpedicion", "emisor", "receptor", "conceptos",
		"impuestos", "schemaUrl", "satUrl", "schemaLocation" })
public class Cfdi {

	private String schemaUrl = CfdiConstants.SCHEMA_URL;
	private String satUrl = CfdiConstants.SAT_URL;
	private String schemaLocation = CfdiConstants.SCHEMA_LOCATION;
	private String version =CfdiConstants.FACTURA_VERSION;
	private String serie;
	private String folio;
	private String fecha;
	private String sello;
	private String formaPago;
	private String noCertificado;
	private String certificado;
	private Double subtotal;
	private Double descuento;
	private String moneda;
	private Double total;
	// I para el caso comun
	private String tipoDeComprobante;
	private String metodoPago;
	private String lugarExpedicion;
	private Emisor emisor;
	private Receptor receptor;
	private List<Concepto> conceptos;
	private Impuesto impuestos;

	@XmlAttribute(name = "xmlns:xsi")
	public String getSchemaUrl() {
		return schemaUrl;
	}

	public void setSchemaUrl(String schemaUrl) {
		this.schemaUrl = schemaUrl;
	}

	@XmlAttribute(name = "xmlns:cfdi")
	public String getSatUrl() {
		return satUrl;
	}

	public void setSatUrl(String satUrl) {
		this.satUrl = satUrl;
	}

	@XmlAttribute(name = "xsi:schemaLocation")
	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	@XmlAttribute(name = "Version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute(name = "Serie")
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@XmlAttribute(name = "Folio")
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlAttribute(name = "Fecha")
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@XmlAttribute(name = "Sello")
	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	@XmlAttribute(name = "FormaPago")
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@XmlAttribute(name = "NoCertificado")
	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	@XmlAttribute(name = "Certificado")
	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	@XmlAttribute(name = "SubTotal")
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@XmlAttribute(name = "Descuento")
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@XmlAttribute(name = "Moneda")
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@XmlAttribute(name = "Total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@XmlAttribute(name = "TipoDeComprobante")
	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}

	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}

	@XmlAttribute(name = "MetodoPago")
	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	@XmlAttribute(name = "LugarExpedicion")
	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	@XmlElement(name = "cfdi:Emisor")
	public Emisor getEmisor() {
		return emisor;
	}

	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}

	@XmlElement(name = "cfdi:Receptor")
	public Receptor getReceptor() {
		return receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	@XmlElementWrapper(name = "cfdi:Conceptos")
	@XmlElement(name = "cfdi:Concepto")
	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	@XmlElement(name = "cfdi:Impuestos")
	public Impuesto getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Impuesto impuestos) {
		this.impuestos = impuestos;
	}

}
