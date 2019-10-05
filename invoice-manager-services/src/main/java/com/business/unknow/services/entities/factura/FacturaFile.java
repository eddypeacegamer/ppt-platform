package com.business.unknow.services.entities.factura;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FACTURA_ARCHIVO")
public class FacturaFile implements Serializable {

	private static final long serialVersionUID = -7716416675628804402L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FACTURA_ARCHIVO")
	private Integer id;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "ARCHIVO_XML")
	private String xml;

	@Column(name = "ARCHIVO_PDF")
	private String pdf;

	@Column(name = "ARCHIVO_QR")
	private String qr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
		return "FacturaFile [id=" + id + ", folio=" + folio + ", xml=" + xml + ", pdf=" + pdf + ", qr=" + qr + "]";
	}

}
