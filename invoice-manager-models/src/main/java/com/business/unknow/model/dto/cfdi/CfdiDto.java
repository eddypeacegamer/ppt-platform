package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private String sello;
	private String noCertificado;
	private String certificado;
	private String moneda;
	private BigDecimal tipoCambio;
	private BigDecimal impuestosTrasladados;
	private BigDecimal impuestosRetenidos;
	private BigDecimal subtotal;
	private BigDecimal descuento;
	private BigDecimal total;
	private String tipoDeComprobante;
	private String metodoPago;
	private String formaPago;
	private String condicionesDePago;
	private String lugarExpedicion;
	private List<ConceptoDto> conceptos;
	private ComplementoDto complemento;
	private EmisorDto emisor;
	private ReceptorDto receptor;
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
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public BigDecimal getImpuestosTrasladados() {
		return impuestosTrasladados;
	}
	public void setImpuestosTrasladados(BigDecimal impuestosTrasladados) {
		this.impuestosTrasladados = impuestosTrasladados;
	}
	public BigDecimal getImpuestosRetenidos() {
		return impuestosRetenidos;
	}
	public void setImpuestosRetenidos(BigDecimal impuestosRetenidos) {
		this.impuestosRetenidos = impuestosRetenidos;
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
	public List<ConceptoDto> getConceptos() {
		return conceptos;
	}
	public void setConceptos(List<ConceptoDto> conceptos) {
		this.conceptos = conceptos;
	}
	public ComplementoDto getComplemento() {
		return complemento;
	}
	public void setComplemento(ComplementoDto complemento) {
		this.complemento = complemento;
	}
	public EmisorDto getEmisor() {
		return emisor;
	}
	public void setEmisor(EmisorDto emisor) {
		this.emisor = emisor;
	}
	public ReceptorDto getReceptor() {
		return receptor;
	}
	public void setReceptor(ReceptorDto receptor) {
		this.receptor = receptor;
	}
	@Override
	public String toString() {
		return "CfdiDto [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", sello="
				+ sello + ", noCertificado=" + noCertificado + ", certificado=" + certificado + ", moneda=" + moneda
				+ ", impuestosTrasladados=" + impuestosTrasladados + ", impuestosRetenidos=" + impuestosRetenidos
				+ ", subtotal=" + subtotal + ", descuento=" + descuento + ", total=" + total + ", tipoDeComprobante="
				+ tipoDeComprobante + ", metodoPago=" + metodoPago + ", formaPago=" + formaPago + ", condicionesDePago="
				+ condicionesDePago + ", lugarExpedicion=" + lugarExpedicion + ", conceptos=" + conceptos
				+ ", complemento=" + complemento + ", emisor=" + emisor + ", receptor=" + receptor + "]";
	}

}
