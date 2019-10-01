package com.business.unknow.services.entities.factura;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.business.unknow.services.entities.catalogs.StatusFactura;

@Entity
@Table(name = "FACTURAS")
public class Factura implements Serializable {

	private static final long serialVersionUID = 2854049815604653381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FACTURA")
	private Integer id;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "SERIE")
	private String serie;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "FOLIO_PADRE")
	private String folioPadre;

	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;

	@Column(name = "SELLO")
	private String sello;
	// TODO EVALUATE IF THIS FIELDS SHOULD STORED OR CAN BE JUST CALCULATED
	@Column(name = "CERTIFICADO")
	private String certificado;

	@Column(name = "NO_CERTIFICADO")
	private String noCertificado;

	@Column(name = "SUBTOTAL")
	private Double subtotal;

	@Column(name = "DESCUENTO")
	private Double descuento;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "TOTAL")
	private Double total;

	@Column(name = "TIPO_COMPROBANDTE")
	private String tipoDeComprobante;

	@Column(name = "LUGAR_EXPEDICION")
	private String lugarDeExpedicion;

	@Column(name = "RFC_EMISOR")
	private String rfcEmisor;

	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@Column(name = "RAZON_SOCIAL_EMISOR")
	private String nombreEmisor;

	@Column(name = "RFC_REMITENTE")
	private String rfcRemitente;

	@Column(name = "USO_CDFI")
	private String usoCFDI;

	@Column(name = "RAZON_SOCIAL_REMITENTE")
	private String nombreRemitente;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "FECHA_TIMBRADO")
	private Date fechaTimbrado;

	@Column(name = "RFC_PROV_CERTIF")
	private String rfcProvCertif;
	// REVIEW IF WE NEED TO SAVE THIS VALUES OR JUST SAVING THE XML IS ENOUGH
	@Column(name = "SELLO_CFD")
	private String selloCFD;

	@Column(name = "NO_CERTIFICADO_SAT")
	private String noCertificadoSat;

	@Column(name = "SELLO_SAT")
	private String selloSAT;

	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Column(name = "NOTAS")
	private String notas;

	// TODO PROBABLY IS BETTER IF THE STATUS ARE REFENCED TO THE INVOICE BUT IS
	// NECESARY EVALUATE IT
	@JoinColumn(name = "ID_STATUS_FACTURA", referencedColumnName = "ID_STATUS_FACTURA")
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private StatusFactura statusFactura;
	
	@Column(name = "STATUS_DETAIL")
	private String statusDetail;

	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
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

	public StatusFactura getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(StatusFactura statusFactura) {
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

	public String getFolioPadre() {
		return folioPadre;
	}

	public void setFolioPadre(String folioPadre) {
		this.folioPadre = folioPadre;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", folioPadre="
				+ folioPadre + ", fechaSolicitud=" + fechaSolicitud + ", sello=" + sello + ", certificado="
				+ certificado + ", noCertificado=" + noCertificado + ", subtotal=" + subtotal + ", descuento="
				+ descuento + ", moneda=" + moneda + ", total=" + total + ", tipoDeComprobante=" + tipoDeComprobante
				+ ", lugarDeExpedicion=" + lugarDeExpedicion + ", rfcEmisor=" + rfcEmisor + ", regimenFiscal="
				+ regimenFiscal + ", nombreEmisor=" + nombreEmisor + ", rfcRemitente=" + rfcRemitente + ", usoCFDI="
				+ usoCFDI + ", nombreRemitente=" + nombreRemitente + ", uuid=" + uuid + ", fechaTimbrado="
				+ fechaTimbrado + ", rfcProvCertif=" + rfcProvCertif + ", selloCFD=" + selloCFD + ", noCertificadoSat="
				+ noCertificadoSat + ", selloSAT=" + selloSAT + ", formaPago=" + formaPago + ", metodoPago="
				+ metodoPago + ", notas=" + notas + ", statusFactura=" + statusFactura + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
