package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(name = "FECHA_SOLICITUD")
	private Date fecha;

	@Column(name = "SELLO")
	private String sello;

	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Column(name = "NO_CERTIFICADO")
	private String noCertificado;

	@Column(name = "CERTIFICADO")
	private String certificado;

	@Column(name = "SUBTOTAL")
	private Double subtotal;

	@Column(name = "DESCUENTO")
	private Double descuento;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "TOTAL")
	private Double total;

	@Column(name = "TIPO_COMPROBANTE")
	private String tipoDeComprobante;

	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Column(name = "LUGAR_EXPEDICION")
	private String lugarExpedicion;

	@Column(name = "NOMBRE_EMISOR")
	private String nombreEmisor;

	@Column(name = "RFC_EMISOR")
	private String rfcEmisor;

	@Column(name = "NOMBRE_RECEPTOR")
	private String nombreReceptor;

	@Column(name = "RFC_RECEPTOR")
	private String rfcReceptor;

	@Column(name = "USO_CFDI")
	private String usoCfdi;

	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@Column(name = "RFC_PROV_CERTIF")
	private String rfcProvCertif;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "SELLO_CFD")
	private String selloCfd;

	@Column(name = "FECHA_TIMBRADO")
	private Date fechaTimbrado;

	@Column(name = "NO_CERTIFICADO_SAT")
	private String noCertificadoSat;

	@Column(name = "SELLO_SAT")
	private String selloSat;

	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@OneToMany(mappedBy = "cfdi")
	private List<Concepto> conceptos;

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

	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	@Override
	public String toString() {
		return "Cfdi [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", fecha=" + fecha
				+ ", sello=" + sello + ", formaPago=" + formaPago + ", noCertificado=" + noCertificado
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
