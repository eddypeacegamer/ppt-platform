package com.business.unknow.services.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_status")
public class InvoiceStatus implements Serializable {

	private static final long serialVersionUID = -2017562498379588051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_invoice_status")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "event_status", unique = true)
	private String eventStatus;

	@Basic(optional = false)
	@Column(name = "pay_status", unique = true)
	private String payStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@Override
	public String toString() {
		return "InvoiceStatus [id=" + id + ", eventStatus=" + eventStatus + ", payStatus=" + payStatus + "]";
	}

}
