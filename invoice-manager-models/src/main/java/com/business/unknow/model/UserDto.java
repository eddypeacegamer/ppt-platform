/**
 * 
 */
package com.business.unknow.model;

import java.io.Serializable;

/**
 * @author eej000f
 *
 */
public class UserDto implements Serializable {

	private Integer id;

	private RoleDto role;

	private String email;

	private String pw;

	private String name;

	private static final long serialVersionUID = -4269713581531174125L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", role=" + role + ", email=" + email + ", pw=" + pw + ", name=" + name + "]";
	}

}
