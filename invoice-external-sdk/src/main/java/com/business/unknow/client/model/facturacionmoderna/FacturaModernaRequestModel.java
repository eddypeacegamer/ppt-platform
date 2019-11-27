package com.business.unknow.client.model.facturacionmoderna;

public class FacturaModernaRequestModel {

	private String user;
	private String userPass;
	private String rfc;
	private String xml;
	private Boolean generarTxt;
	private Boolean generarPdf;
	private Boolean generarCbb;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Boolean isGenerarTxt() {
		return generarTxt;
	}

	public void setGenerarTxt(Boolean generarTxt) {
		this.generarTxt = generarTxt;
	}

	public Boolean isGenerarPdf() {
		return generarPdf;
	}

	public void setGenerarPdf(Boolean generarPdf) {
		this.generarPdf = generarPdf;
	}

	public Boolean isGenerarCbb() {
		return generarCbb;
	}

	public void setGenerarCbb(Boolean generarCbb) {
		this.generarCbb = generarCbb;
	}

	@Override
	public String toString() {
		return "FacturaModernaRequestModel [user=" + user + ", userPass=" + userPass + ", rfc=" + rfc + ", xml=" + xml
				+ ", generarTxt=" + generarTxt + ", generarPdf=" + generarPdf + ", generarCbb=" + generarCbb + "]";
	}

}
