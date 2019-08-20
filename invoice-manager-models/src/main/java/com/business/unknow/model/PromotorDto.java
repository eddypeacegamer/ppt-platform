package com.business.unknow.model;

import java.io.Serializable;

public class PromotorDto implements Serializable {

	private static final long serialVersionUID = 449933003127388665L;

	private Integer id;

	private UserDto user;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PromotorDto [id=" + id + ", user=" + user + ", name=" + name + "]";
	}

}
