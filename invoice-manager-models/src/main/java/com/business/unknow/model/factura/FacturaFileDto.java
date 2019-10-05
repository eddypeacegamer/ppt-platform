package com.business.unknow.model.factura;

public class FacturaFileDto {

	private Integer id;

	private String xml;

	private String pdf;

	private String qr;

	private String folio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "FacturaFileDto [id=" + id + ", xml=" + xml + ", pdf=" + pdf + ", qr=" + qr + ", folio=" + folio + "]";
	}

}
