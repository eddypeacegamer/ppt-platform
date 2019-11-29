package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TimbreFiscalDigital",namespace = "http://www.sat.gob.mx/TimbreFiscalDigital")
@XmlAccessorType(XmlAccessType.FIELD)
public class TimbreFiscalDigital {

	@XmlAttribute(name = "tfd=",namespace = "http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/timbrefiscaldigital/TimbreFiscalDigitalv11.xsd")
	private String timbreDigitalUri;
	@XmlAttribute(name = "schemaLocation",namespace = "http://www.w3.org/2001/XMLSchema-instance")
	private String schemaLocationUri;
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

	public String getTimbreDigitalUri() {
		return timbreDigitalUri;
	}

	public void setTimbreDigitalUri(String timbreDigitalUri) {
		this.timbreDigitalUri = timbreDigitalUri;
	}

	public String getSchemaLocationUri() {
		return schemaLocationUri;
	}

	public void setSchemaLocationUri(String schemaLocationUri) {
		this.schemaLocationUri = schemaLocationUri;
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
		return "TimbreFiscalDigital [timbreDigitalUri=" + timbreDigitalUri + ", schemaLocationUri=" + schemaLocationUri
				+ ", version=" + version + ", uuid=" + uuid + ", fechaTimbrado=" + fechaTimbrado + ", rfcProvCertif="
				+ rfcProvCertif + ", selloCFD=" + selloCFD + ", noCertificadoSAT=" + noCertificadoSAT + ", SelloSAT="
				+ SelloSAT + "]";
	}

}
