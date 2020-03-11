package com.business.unknow.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.business.unknow.model.cfdi.Cfdi;

@XmlRootElement(name = "FacturaPdfModel")
@XmlAccessorType(XmlAccessType.NONE)
public class FacturaPdfModelDto {

	@XmlElement(name = "Qr")
	private String qr;
	@XmlElement(name = "Logotipo")
	private String logotipo;
	@XmlElement(name = "factura")
	private Cfdi factura;

	public FacturaPdfModelDto() {
		super();
	}

	public FacturaPdfModelDto(String qr, String logotipo, Cfdi factura) {
		super();
		this.qr = qr;
		this.logotipo = logotipo;
		this.factura = factura;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public Cfdi getFactura() {
		return factura;
	}

	public void setFactura(Cfdi factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "FacturaPdfModel [qr=" + qr + ", logotipo=" + logotipo + ", factura=" + factura + "]";
	}

}
