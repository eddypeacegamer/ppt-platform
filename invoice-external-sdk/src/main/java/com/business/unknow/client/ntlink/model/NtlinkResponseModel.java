package com.business.unknow.client.ntlink.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TimbraCfdiQrResult")
@XmlAccessorType(XmlAccessType.NONE)
public class NtlinkResponseModel {
	

	@XmlElement(name = "CadenaTimbre",namespace = "a")
	private String cadenaTimbre;

	@XmlElement(name = "Cfdi")
	private String cfdi;
	
	@XmlElement(name = "DescripcionError")
	private String descripcionError;
	
	@XmlElement(name = "QrCodeBase64")
	private String qrCodeBase64;
	
	@XmlElement(name = "Valido")
	private String valido;

	public String getCadenaTimbre() {
		return cadenaTimbre;
	}

	public void setCadenaTimbre(String cadenaTimbre) {
		this.cadenaTimbre = cadenaTimbre;
	}

	public String getCfdi() {
		return cfdi;
	}

	public void setCfdi(String cfdi) {
		this.cfdi = cfdi;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getQrCodeBase64() {
		return qrCodeBase64;
	}

	public void setQrCodeBase64(String qrCodeBase64) {
		this.qrCodeBase64 = qrCodeBase64;
	}

	public String getValido() {
		return valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	@Override
	public String toString() {
		return "NtlinkErrorModel [cadenaTimbre=" + cadenaTimbre + ", cfdi=" + cfdi + ", descripcionError="
				+ descripcionError + ", qrCodeBase64=" + qrCodeBase64 + ", valido=" + valido + "]";
	}
	
}
