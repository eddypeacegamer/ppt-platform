package com.business.unknow.services.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_cdfi")
public class InvoiceCdfi implements Serializable {

	private static final long serialVersionUID = 688888269515116160L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_invoice_cdfi")
	private Integer id;

	@JoinColumn(name = "id_client", referencedColumnName = "id_client")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Client client;

	@JoinColumn(name = "id_invoice_status", referencedColumnName = "id_invoice_status")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private InvoiceStatus invoiceStatus;

	@Basic(optional = false)
	@Column(name = "amount", unique = true)
	private Double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "InvoiceCdfi [id=" + id + ", client=" + client + ", invoiceStatus=" + invoiceStatus + ", amount="
				+ amount + "]";
	}

}
