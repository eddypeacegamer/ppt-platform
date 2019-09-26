package com.business.unknow.model.composed;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.ContribuyenteDto;

public class ClientWithDetailDto {

	private ClientDto client;
	private ContribuyenteDto detail;

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	public ContribuyenteDto getDetail() {
		return detail;
	}

	public void setDetail(ContribuyenteDto detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ClientWithDetailDto [client=" + client + ", detail=" + detail + "]";
	}

}
