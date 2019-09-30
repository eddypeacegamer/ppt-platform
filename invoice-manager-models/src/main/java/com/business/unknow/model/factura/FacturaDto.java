package com.business.unknow.model.factura;

import java.util.Date;
import java.util.List;

import com.business.unknow.model.catalogs.StatusFacturaDto;

public class FacturaDto {

	private Integer id;
	private String version;
	private String serie;
	private String folio;
	private Date fechaSolicitud;
	private String sello;
	private String certificado;
	private String noCertificado;
	private Double subtotal;
	private Double descuento;
	private String moneda;
	private Double total;
	private String tipoDeComprobante;
	private String lugarDeExpedicion;
	private String rfcEmisor;
	private String usoCFDI;
	private String nombreEmisor;
	private String rfcRemitente;
	private String regimenFiscal;
	private String nombreRemitente;

	private List<ConceptoDto> conceptos;
	private List<PagoDto> pagos;

	private String uuid;
	private Date fechaTimbrado;
	private String rfcProvCertif;
	private String selloCFD;
	private String noCertificadoSat;
	private String selloSAT;

	private String formaPago;
	private String metodoPago;
	private String notas;
	private StatusFacturaDto statusFactura;
	private String statusDetail;
	private Date fechaCreacion;
	private Date fechaActualizacion;

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

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
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

	public String getLugarDeExpedicion() {
		return lugarDeExpedicion;
	}

	public void setLugarDeExpedicion(String lugarDeExpedicion) {
		this.lugarDeExpedicion = lugarDeExpedicion;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getRfcRemitente() {
		return rfcRemitente;
	}

	public void setRfcRemitente(String rfcRemitente) {
		this.rfcRemitente = rfcRemitente;
	}

	public String getUsoCFDI() {
		return usoCFDI;
	}

	public void setUsoCFDI(String usoCFDI) {
		this.usoCFDI = usoCFDI;
	}

	public String getNombreRemitente() {
		return nombreRemitente;
	}

	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public String getRfcProvCertif() {
		return rfcProvCertif;
	}

	public void setRfcProvCertif(String rfcProvCertif) {
		this.rfcProvCertif = rfcProvCertif;
	}

	public String getSelloCFD() {
		return selloCFD;
	}

	public void setSelloCFD(String selloCFD) {
		this.selloCFD = selloCFD;
	}

	public String getNoCertificadoSat() {
		return noCertificadoSat;
	}

	public void setNoCertificadoSat(String noCertificadoSat) {
		this.noCertificadoSat = noCertificadoSat;
	}

	public String getSelloSAT() {
		return selloSAT;
	}

	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public StatusFacturaDto getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(StatusFacturaDto statusFactura) {
		this.statusFactura = statusFactura;
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

	public List<ConceptoDto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<ConceptoDto> conceptos) {
		this.conceptos = conceptos;
	}

	public List<PagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoDto> pagos) {
		this.pagos = pagos;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	@Override
	public String toString() {
		return "FacturaDto [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio
				+ ", fechaSolicitud=" + fechaSolicitud + ", sello=" + sello + ", certificado=" + certificado
				+ ", noCertificado=" + noCertificado + ", subtotal=" + subtotal + ", descuento=" + descuento
				+ ", moneda=" + moneda + ", total=" + total + ", tipoDeComprobante=" + tipoDeComprobante
				+ ", lugarDeExpedicion=" + lugarDeExpedicion + ", rfcEmisor=" + rfcEmisor + ", usoCFDI=" + usoCFDI
				+ ", nombreEmisor=" + nombreEmisor + ", rfcRemitente=" + rfcRemitente + ", regimenFiscal="
				+ regimenFiscal + ", nombreRemitente=" + nombreRemitente + ", conceptos=" + conceptos + ", pagos="
				+ pagos + ", uuid=" + uuid + ", fechaTimbrado=" + fechaTimbrado + ", rfcProvCertif=" + rfcProvCertif
				+ ", selloCFD=" + selloCFD + ", noCertificadoSat=" + noCertificadoSat + ", selloSAT=" + selloSAT
				+ ", formaPago=" + formaPago + ", metodoPago=" + metodoPago + ", notas=" + notas + ", statusFactura="
				+ statusFactura + ", statusDetail=" + statusDetail + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
