package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 5310702474972292849L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Integer id;

	@Column(name = "ACTIVO")
	private boolean activo;
	
	@Column(name = "CORREO")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@OneToMany(mappedBy="user")
	private List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", roles=" + roles + ", activo=" + activo + ", correo=" + email + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
}
