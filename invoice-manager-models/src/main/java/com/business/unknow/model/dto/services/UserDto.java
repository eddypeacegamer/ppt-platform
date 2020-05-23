/**
 * 
 */
package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.business.unknow.Constants;
import com.business.unknow.model.menu.MenuItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author eej000f
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private Integer id;
	
	private String email;

	private boolean activo;

	private String name;
	
	private String alias;

	private String urlPicture;

	private List<RoleDto> roles;
	
	private List<MenuItem> menu;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCreacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaActualizacion;

	private static final long serialVersionUID = -4269713581531174125L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}
	
	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
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
		return "UserDto [id=" + id + ", email=" + email + ", activo=" + activo + ", name=" + name + ", alias=" + alias
				+ ", urlPicture=" + urlPicture + ", roles=" + roles + ", menu=" + menu + "]";
	}

}
