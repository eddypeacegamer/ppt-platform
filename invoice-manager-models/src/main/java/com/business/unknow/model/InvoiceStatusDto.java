package com.business.unknow.model;

import java.io.Serializable;

public class InvoiceStatusDto implements Serializable {

	private static final long serialVersionUID = 6605485841161408524L;

	private Integer id;

	private String eventStatus;

	private String payStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@Override
	public String toString() {
		return "InvoiceStatusDto [id=" + id + ", eventStatus=" + eventStatus + ", payStatus=" + payStatus + "]";
	}
	
	
	
}
