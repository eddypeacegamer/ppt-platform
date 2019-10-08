package com.business.unknow.model;

public class RoleDto {

	private Integer id;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", role=" + role + "]";
	}

}
