package com.business.unknow.model;

public class StatusCatalogoDto {

	private int id;
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StatusCatalogoDto [id=" + id + ", value=" + value + "]";
	}

}
