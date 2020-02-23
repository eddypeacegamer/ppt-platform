package com.business.unknow.client.ntlink.model;

public class NtlinkCancelRequestModel {

	private String user;
	private String userPass;
	private String uuid;
	private String rfcEmisor;
	private String rfcReceptor;

	public NtlinkCancelRequestModel(String user, String userPass, String uuid, String rfcEmisor, String rfcReceptor) {
		super();
		this.user = user;
		this.userPass = userPass;
		this.uuid = uuid;
		this.rfcEmisor = rfcEmisor;
		this.rfcReceptor = rfcReceptor;
	}

	public NtlinkCancelRequestModel() {
		super();
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getRfcReceptor() {
		return rfcReceptor;
	}

	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}

	@Override
	public String toString() {
		return "NtlinkCancelRequestModel [user=" + user + ", userPass=" + userPass + ", uuid=" + uuid + ", rfcEmisor="
				+ rfcEmisor + ", rfcReceptor=" + rfcReceptor + "]";
	}

}