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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTORES")
public class Promotor implements Serializable {

	private static final long serialVersionUID = 8102757944306156478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROMOTOR")
	private Integer id;

	@JoinColumn(name = "id_user", referencedColumnName = "ID_USER")
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	@Basic(optional = false)
	@Column(name = "NOMBRE", unique = true)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
		return "Promotor [id=" + id + ", user=" + user + ", name=" + name + "]";
	}

}
