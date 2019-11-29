package com.business.unknow.client.swsapiens.model;

public class SwSapiensCancel {

	private String uuid;
	private String password;
	private String rfc;
	private String b64Cer;
	private String b64Key;

	public SwSapiensCancel() {

	}

	public SwSapiensCancel(String uuid, String password, String rfc, String b64Cer, String b64Key) {
		super();
		this.uuid = uuid;
		this.password = password;
		this.rfc = rfc;
		this.b64Cer = b64Cer;
		this.b64Key = b64Key;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getB64Cer() {
		return b64Cer;
	}

	public void setB64Cer(String b64Cer) {
		this.b64Cer = b64Cer;
	}

	public String getB64Key() {
		return b64Key;
	}

	public void setB64Key(String b64Key) {
		this.b64Key = b64Key;
	}

	@Override
	public String toString() {
		return "SwSapiensCancel [uuid=" + uuid + ", password=" + password + ", rfc=" + rfc + ", b64Cer=" + b64Cer
				+ ", b64Key=" + b64Key + "]";
	}

}
