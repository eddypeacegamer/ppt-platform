package com.business.unknow.client.model.swsapiens;

public class Data {
	private String token;
	private Integer expires_in;
	private String tokeny_type;
	private String contribuyenteRFC;
	private Boolean sncf;
	private Boolean subcontratacion;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getTokeny_type() {
		return tokeny_type;
	}

	public void setTokeny_type(String tokeny_type) {
		this.tokeny_type = tokeny_type;
	}

	public String getContribuyenteRFC() {
		return contribuyenteRFC;
	}

	public void setContribuyenteRFC(String contribuyenteRFC) {
		this.contribuyenteRFC = contribuyenteRFC;
	}

	public Boolean getSncf() {
		return sncf;
	}

	public void setSncf(Boolean sncf) {
		this.sncf = sncf;
	}

	public Boolean getSubcontratacion() {
		return subcontratacion;
	}

	public void setSubcontratacion(Boolean subcontratacion) {
		this.subcontratacion = subcontratacion;
	}

	@Override
	public String toString() {
		return "Data [token=" + token + ", expires_in=" + expires_in + ", tokeny_type=" + tokeny_type
				+ ", contribuyenteRFC=" + contribuyenteRFC + ", sncf=" + sncf + ", subcontratacion=" + subcontratacion
				+ "]";
	}

}
