package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	@Column(name = "FECHA_TIMBRADO")
	private Date fecha;

	@Column(name = "SELLO")
	private String sello;

	@Column(name = "NO_CERTIFICADO")
	private String noCertificado;

	@Column(name = "CERTIFICADO")
	private String certificado;
	
	@Column(name = "SUBTOTAL")
	private BigDecimal subtotal;
	
	@Column(name = "DESCUENTO")
	private BigDecimal descuento;
	
	@Column(name = "MONEDA")
	private String moneda;
	
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
	
	@Column(name = "USO_CFDI")
	private String usoCfdi;

	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@Column(name = "RFC_PROV_CERTIF")
	private String rfcProvCertif;

	@Column(name = "SELLO_CFD")
	private String selloCfd;

	@Column(name = "NO_CERTIFICADO_SAT")
	private String noCertificadoSat;

	@Column(name = "SELLO_SAT")
	private String selloSat;
	
	@Column(name = "CADENA_ORIGINAL")
	private String cadenaOriginal;
	
	@Column(name = "EMISOR")
	private String emisor;
	
	@Column(name = "RECEPTOR")
	private String receptor;
	
	@Column(name= "ID_FACTURA")
	private Integer idFactura;

	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "cfdi", orphanRemoval = true)
	private List<Concepto> conceptos;
	
	
	//TODO Evaliuate add Complemento and TimbreFicalDigital hierarchie here
	
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

	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}
	
	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	@Override
	public String toString() {
		return "Cfdi [id=" + id + ", version=" + version + ", serie=" + serie + ", folio=" + folio + ", fecha=" + fecha
				+ ", sello=" + sello + ", noCertificado=" + noCertificado + ", certificado=" + certificado
				+ ", subtotal=" + subtotal + ", descuento=" + descuento + ", moneda=" + moneda + ", total=" + total
				+ ", tipoDeComprobante=" + tipoDeComprobante + ", metodoPago=" + metodoPago + ", formaPago=" + formaPago
				+ ", condicionesDePago=" + condicionesDePago + ", lugarExpedicion=" + lugarExpedicion + ", usoCfdi="
				+ usoCfdi + ", regimenFiscal=" + regimenFiscal + ", rfcProvCertif=" + rfcProvCertif + ", selloCfd="
				+ selloCfd + ", noCertificadoSat=" + noCertificadoSat + ", selloSat=" + selloSat + ", cadenaOriginal="
				+ cadenaOriginal + ", emisor=" + emisor + ", receptor=" + receptor + ", idFactura=" + idFactura
				+ ", conceptos=" + conceptos + "]";
	}

}
