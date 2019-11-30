package com.business.unknow.client.swsapiens.util;

import com.business.unknow.client.swsapiens.model.Data;

public class SwSapiensConfig {

	private Data data;
	private String status;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SwSapiensConfig [data=" + data + ", status=" + status + "]";
	}

}
