package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
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

	@Column(name = "SELLO")
	private String sello;

	@Column(name = "NO_CERTIFICADO")
	private String noCertificado;

	@Column(name = "CERTIFICADO")
	private String certificado;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "TIPO_COMPROBANTE")
	private String tipoDeComprobante;

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

	@OneToMany(mappedBy = "cfdi")
	private List<Concepto> conceptos;

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

	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}

	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
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

	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	@Override
	public String toString() {
		return "Cfdi [version=" + version + ", serie=" + serie + ", folio=" + folio + ", sello=" + sello
				+ ", noCertificado=" + noCertificado + ", certificado=" + certificado + ", moneda=" + moneda
				+ ", tipoDeComprobante=" + tipoDeComprobante + ", usoCfdi=" + usoCfdi + ", regimenFiscal="
				+ regimenFiscal + ", rfcProvCertif=" + rfcProvCertif + ", selloCfd=" + selloCfd + ", noCertificadoSat="
				+ noCertificadoSat + ", selloSat=" + selloSat + ", conceptos=" + conceptos + "]";
	}

}
