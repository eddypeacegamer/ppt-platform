package com.business.unknow.client.facturacionmoderna.model;

public class FacturaModernaRequestModel {

	private String user;
	private String userPass;
	private String rfc;
	private String xml;
	private String uuid;
	private Boolean generarTxt;
	private Boolean generarPdf;
	private Boolean generarCbb;

	public FacturaModernaRequestModel() {
	}

	public FacturaModernaRequestModel(String user, String userPass, String rfc, String xml, Boolean generarTxt,
			Boolean generarPdf, Boolean generarCbb) {
		this.user = user;
		this.userPass = userPass;
		this.rfc = rfc;
		this.xml = xml;
		this.generarTxt = generarTxt;
		this.generarPdf = generarPdf;
		this.generarCbb = generarCbb;
	}
	
	public FacturaModernaRequestModel(String user, String userPass, String rfc, String uuid) {
		this.user = user;
		this.userPass = userPass;
		this.rfc = rfc;
		this.uuid=uuid;
	}

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Boolean getGenerarTxt() {
		return generarTxt;
	}

	public Boolean getGenerarPdf() {
		return generarPdf;
	}

	public Boolean getGenerarCbb() {
		return generarCbb;
	}

	@Override
	public String toString() {
		return "FacturaModernaRequestModel [user=" + user + ", userPass=" + userPass + ", rfc=" + rfc + ", xml=" + xml
				+ ", uuid=" + uuid + ", generarTxt=" + generarTxt + ", generarPdf=" + generarPdf + ", generarCbb="
				+ generarCbb + "]";
	}

}
