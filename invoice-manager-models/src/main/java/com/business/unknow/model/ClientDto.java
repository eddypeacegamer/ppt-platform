package com.business.unknow.model;

import java.io.Serializable;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = 4951260411762447946L;

	private Integer id;

	private PromotorDto promotor;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PromotorDto getPromotor() {
		return promotor;
	}

	public void setPromotor(PromotorDto promotor) {
		this.promotor = promotor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", promotor=" + promotor + ", name=" + name + "]";
	}

}
