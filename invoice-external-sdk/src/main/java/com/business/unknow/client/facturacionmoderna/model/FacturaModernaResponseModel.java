package com.business.unknow.client.facturacionmoderna.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="return")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacturaModernaResponseModel {

	@XmlElement(name = "xml")
	private String xml;
	@XmlElement(name = "txt")
	private String txt;
	@XmlElement(name = "pdf")
	private String pdf;
	@XmlElement(name = "png")
	private String png;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getPng() {
		return png;
	}

	public void setPng(String png) {
		this.png = png;
	}

	@Override
	public String toString() {
		return "FacturaModernaResponseModel [xml=" + xml + ", txt=" + txt + ", pdf=" + pdf + ", png=" + png + "]";
	}

}
