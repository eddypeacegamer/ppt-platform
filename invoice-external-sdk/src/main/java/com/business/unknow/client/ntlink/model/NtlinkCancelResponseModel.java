package com.business.unknow.client.ntlink.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CancelaCfdiResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class NtlinkCancelResponseModel {

	@XmlElement(name = "CancelaCfdiResult", namespace = "a")
	private String cancelaCfdiResult;

	public String getCancelaCfdiResult() {
		return cancelaCfdiResult;
	}

	public void setCancelaCfdiResult(String cancelaCfdiResult) {
		this.cancelaCfdiResult = cancelaCfdiResult;
	}

	@Override
	public String toString() {
		return "NtlinkCancelResponseModel [cancelaCfdiResult=" + cancelaCfdiResult + "]";
	}

}
