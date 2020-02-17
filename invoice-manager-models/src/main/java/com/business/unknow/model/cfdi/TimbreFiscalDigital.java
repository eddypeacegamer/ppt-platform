package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TimbreFiscalDigital", namespace = "http://www.sat.gob.mx/TimbreFiscalDigital")
@XmlAccessorType(XmlAccessType.FIELD)
public class TimbreFiscalDigital {
	@XmlAttribute(name = "Version")
	private String version;
	@XmlAttribute(name = "UUID")
	private String uuid;
	@XmlAttribute(name = "FechaTimbrado")
	private String fechaTimbrado;
	@XmlAttribute(name = "RfcProvCertif")
	private String rfcProvCertif;
	@XmlAttribute(name = "SelloCFD")
	private String selloCFD;
	@XmlAttribute(name = "NoCertificadoSAT")
	private String noCertificadoSAT;
	@XmlAttribute(name = "SelloSAT")
	private String SelloSAT;

	public TimbreFiscalDigital() {
		super();
	}

	public TimbreFiscalDigital(String version, String uuid, String fechaTimbrado, String rfcProvCertif, String selloCFD,
			String noCertificadoSAT, String selloSAT) {
		super();
		this.version = version;
		this.uuid = uuid;
		this.fechaTimbrado = fechaTimbrado;
		this.rfcProvCertif = rfcProvCertif;
		this.selloCFD = selloCFD;
		this.noCertificadoSAT = noCertificadoSAT;
		SelloSAT = selloSAT;
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

	public String getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(String fechaTimbrado) {
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

	public String getNoCertificadoSAT() {
		return noCertificadoSAT;
	}

	public void setNoCertificadoSAT(String noCertificadoSAT) {
		this.noCertificadoSAT = noCertificadoSAT;
	}

	public String getSelloSAT() {
		return SelloSAT;
	}

	public void setSelloSAT(String selloSAT) {
		SelloSAT = selloSAT;
	}

	@Override
	public String toString() {
		return "TimbreFiscalDigital [version=" + version + ", uuid=" + uuid + ", fechaTimbrado=" + fechaTimbrado
				+ ", rfcProvCertif=" + rfcProvCertif + ", selloCFD=" + selloCFD + ", noCertificadoSAT="
				+ noCertificadoSAT + ", SelloSAT=" + SelloSAT + "]";
	}

}
