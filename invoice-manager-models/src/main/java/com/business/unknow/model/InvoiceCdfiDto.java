package com.business.unknow.model;

import java.io.Serializable;

public class InvoiceCdfiDto implements Serializable {

	private static final long serialVersionUID = -7966329671037198999L;

	private Integer id;

	private ClientDto client;

	private InvoiceCdfiDto invoiceCdfi;

	private Double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	public InvoiceCdfiDto getInvoiceCdfi() {
		return invoiceCdfi;
	}

	public void setInvoiceCdfi(InvoiceCdfiDto invoiceCdfi) {
		this.invoiceCdfi = invoiceCdfi;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "InvoiceCdfiDto [id=" + id + ", client=" + client + ", invoiceCdfi=" + invoiceCdfi + ", amount=" + amount
				+ "]";
	}

}
