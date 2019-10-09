package com.business.unknow.model.factura.cfdi.components;

import java.util.Date;
import java.util.List;

public class CfdiDto {

	private Integer id;
	private String version;
	private String serie;
	private String folio;
	private Date fecha;
	private String sello;
	private String formaPago;
	private String noCertificado;
	private String certificado;
	private Double subtotal;
	private Double descuento;
	private String moneda;
	private Double total;
	private String tipoDeComprobante;
	private String metodoPago;
	private String lugarExpedicion;
	private String nombreEmisor;
	private String rfcEmisor;
	private String nombreReceptor;
	private String rfcReceptor;
	private String usoCfdi;
	private String regimenFiscal;
	private String rfcProvCertif;
	private String uuid;
	private String selloCfd;
	private Date fechaTimbrado;
	private String noCertificadoSat;
	private String selloSat;
	private Date fechaActualizacion;
	private List<ConceptoDto> conceptos;

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

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
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

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public String getRfcReceptor() {
		return rfcReceptor;
	}

	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSelloCfd() {
		return selloCfd;
	}

	public void setSelloCfd(String selloCfd) {
		this.selloCfd = selloCfd;
	}

	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
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

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
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
				+ fecha + ", sello=" + sello + ", formaPago=" + formaPago + ", noCertificado=" + noCertificado
				+ ", certificado=" + certificado + ", subtotal=" + subtotal + ", descuento=" + descuento + ", moneda="
				+ moneda + ", total=" + total + ", tipoDeComprobante=" + tipoDeComprobante + ", metodoPago="
				+ metodoPago + ", lugarExpedicion=" + lugarExpedicion + ", nombreEmisor=" + nombreEmisor
				+ ", rfcEmisor=" + rfcEmisor + ", nombreReceptor=" + nombreReceptor + ", rfcReceptor=" + rfcReceptor
				+ ", usoCfdi=" + usoCfdi + ", regimenFiscal=" + regimenFiscal + ", rfcProvCertif=" + rfcProvCertif
				+ ", uuid=" + uuid + ", selloCfd=" + selloCfd + ", fechaTimbrado=" + fechaTimbrado
				+ ", noCertificadoSat=" + noCertificadoSat + ", selloSat=" + selloSat + ", fechaActualizacion="
				+ fechaActualizacion + ", conceptos=" + conceptos + "]";
	}

}
