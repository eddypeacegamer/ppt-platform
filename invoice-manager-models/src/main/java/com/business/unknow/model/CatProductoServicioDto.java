package com.business.unknow.model;

import java.io.Serializable;

public class CatProductoServicioDto implements Serializable {

	private static final long serialVersionUID = -2061833595327323306L;

	private Integer id;

	private CatProductoServicioDto parent;

	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatProductoServicioDto getParent() {
		return parent;
	}

	public void setParent(CatProductoServicioDto parent) {
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CatProductoServicioDto [id=" + id + ", parent=" + parent + ", value=" + value + "]";
	}

}
