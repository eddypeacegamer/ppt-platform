package com.business.unknow.services.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author eej000f
 *
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = -2562827192729375750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ROLE")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "ROLE")
	private String role;
	
	@Basic(optional = true)
	@Column(name = "ROLE_DESC")
	private String description;
	
	@ManyToOne
    @JoinColumn(name="ID_USER", nullable=false)
    private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", description=" + description + "]";
	}

}
