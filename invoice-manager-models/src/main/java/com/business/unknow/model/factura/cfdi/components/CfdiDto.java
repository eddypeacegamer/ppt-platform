package com.business.unknow.model.factura.cfdi.components;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CfdiDto implements Serializable {

	private static final long serialVersionUID = -303198243726456894L;
	private Integer id;
	private String version;
	private String serie;
	private String folio;
	private Date fecha;
	private String sello;
	private String noCertificado;
	private String certificado;
	private BigDecimal subtotal;
	private BigDecimal descuento;
	private String moneda;
	private BigDecimal total;
	private String tipoDeComprobante;
	private String metodoPago;
	private String formaPago;
	private String condicionesDePago;
	private String lugarExpedicion;
	private String usoCfdi;
	private String regimenFiscal;
	private String rfcProvCertif;
	private String selloCfd;
	private String noCertificadoSat;
	private String selloSat;
	private String cadenaOriginal;
	private String emisor;
	private String receptor;
	private List<ConceptoDto> conceptos;
	
	//TODO Evaliuate add Complemento and TimbreFicalDigital hierarchie here
	
	public CfdiDto() {
		this.conceptos = new ArrayList<>();
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getUsoCfdi() {
		return usoCfdi;
	}

	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getRfcProvCertif() {
		return rfcProvCertif;
	}

	public void setRfcProvCertif(String rfcProvCertif) {
		this.rfcProvCertif = rfcProvCertif;
	}

	public String getSelloCfd() {
		return selloCfd;
	}

	public void setSelloCfd(String selloCfd) {
		this.selloCfd = selloCfd;
	}

	public String getNoCertificadoSat() {
		return noCertificadoSat;
	}

	public void setNoCertificadoSat(String noCertificadoSat) {
		this.noCertificadoSat = noCertificadoSat;
	}

	public String getSelloSat() {
		return selloSat;
	}

	public void setSelloSat(String selloSat) {
		this.selloSat = selloSat;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public List<ConceptoDto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<ConceptoDto> conceptos) {
		this.conceptos = conceptos;
	}

	@Override
	public String toString() {
		return "CfdiDto [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", fecha="
				+ fecha + ", sello=" + sello + ", noCertificado=" + noCertificado + ", certificado=" + certificado
				+ ", subtotal=" + subtotal + ", descuento=" + descuento + ", moneda=" + moneda + ", total=" + total
				+ ", tipoDeComprobante=" + tipoDeComprobante + ", metodoPago=" + metodoPago + ", formaPago=" + formaPago
				+ ", condicionesDePago=" + condicionesDePago + ", lugarExpedicion=" + lugarExpedicion + ", usoCfdi="
				+ usoCfdi + ", regimenFiscal=" + regimenFiscal + ", rfcProvCertif=" + rfcProvCertif + ", selloCfd="
				+ selloCfd + ", noCertificadoSat=" + noCertificadoSat + ", selloSat=" + selloSat + ", cadenaOriginal="
				+ cadenaOriginal + ", emisor=" + emisor + ", receptor=" + receptor + ", conceptos=" + conceptos + "]";
	}

}
