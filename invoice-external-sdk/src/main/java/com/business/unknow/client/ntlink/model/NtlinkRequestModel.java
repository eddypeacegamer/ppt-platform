package com.business.unknow.client.ntlink.model;

public class NtlinkRequestModel {

	private String user;
	private String userPass;
	private String xml;

	public NtlinkRequestModel(String user, String userPass, String xml) {
		super();
		this.user = user;
		this.userPass = userPass;
		this.xml = xml;
	}

	public NtlinkRequestModel() {
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

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Override
	public String toString() {
		return "NtlinkRequestModel [user=" + user + ", userPass=" + userPass + ", xml=" + xml + "]";
	}

}
