package com.business.unknow.services.entities.factura;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class FacturaFile implements Serializable {

	private static final long serialVersionUID = -7716416675628804402L;

	@Id
	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "ARCHIVO_XML")
	private String xml;

	@Column(name = "ARCHIVO_PDF")
	private String pdf;

	@Column(name = "ARCHIVO_QR")
	private String qr;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	@Override
	public String toString() {
		return "FacturaFile [folio=" + folio + ", xml=" + xml + ", pdf=" + pdf + ", qr=" + qr + "]";
	}

}
