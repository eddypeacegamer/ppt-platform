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
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 5310702474972292849L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Integer id;

	@JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Role role;

	@Basic(optional = false)
	@Column(name = "CORREO", unique = true)
	private String email;

	@Basic(optional = false)
	@Column(name = "PW", unique = true)
	private String pw;

	@Basic(optional = false)
	@Column(name = "NOMBRE", unique = true)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", email=" + email + ", pw=" + pw + ", name=" + name + "]";
	}

}
