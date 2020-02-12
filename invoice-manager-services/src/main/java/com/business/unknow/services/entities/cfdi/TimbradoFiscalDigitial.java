package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CFDI_TIMBRE_FISCAL_DIGITAL")
public class TimbradoFiscalDigitial implements Serializable {

	private static final long serialVersionUID = 6434301858121874015L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPLEMENTO")
	private Integer id;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "FECHA_TIMBRADO")
	private Date fechaTimbrado;

	@Column(name = "RFC_PROV_CERTIF")
	private String rfcProvCertif;

	@Column(name = "SELLO_CFD")
	private String selloCFD;

	@Column(name = "NO_CERTIFICADO_SAT")
	private String noCertificadoSat;

	@Column(name = "SELLO_SAT")
	private String selloSat;

	@Column(name = "CADENA_ORIGINAL")
	private String cadenaOriginal;

	@OneToOne
	@JoinColumn(name = "ID_CFDI")
	private Cfdi cfdi;

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

	public Cfdi getCfdi() {
		return cfdi;
	}

	public void setCfdi(Cfdi cfdi) {
		this.cfdi = cfdi;
	}

	@Override
	public String toString() {
		return "Complemento [id=" + id + ", version=" + version + ", uuid=" + uuid + ", fechaTimbrado=" + fechaTimbrado
				+ ", rfcProvCertif=" + rfcProvCertif + ", selloCFD=" + selloCFD + ", noCertificadoSat="
				+ noCertificadoSat + ", selloSat=" + selloSat + "]";
	}

}
