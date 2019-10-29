/**
 * 
 */
package com.business.unknow.model;

import java.io.Serializable;
import java.util.List;

import com.business.unknow.model.menu.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author eej000f
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private String email;

	private boolean activo;

	private String name;

	private String urlPicture;

	private List<String> roles;
	
	private List<MenuItem> menu;

	private static final long serialVersionUID = -4269713581531174125L;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "UserDto [email=" + email + ", activo=" + activo + ", name=" + name + ", urlPicture=" + urlPicture
				+ ", roles=" + roles + "]";
	}

}
