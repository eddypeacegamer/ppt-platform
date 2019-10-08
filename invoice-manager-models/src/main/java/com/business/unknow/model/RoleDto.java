package com.business.unknow.model;

public class RoleDto {

	private Integer id;
	private String role;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", role=" + role + ", description=" + description + "]";
	}

}
