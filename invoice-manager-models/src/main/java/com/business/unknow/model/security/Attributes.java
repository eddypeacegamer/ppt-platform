package com.business.unknow.model.security;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ralfdemoledor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes implements Serializable{

	private static final long serialVersionUID = -614495127571115676L;
	private String picture;
	private String name;
	private String email;
	private String given_name;
	private String family_name;
	
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	@Override
	public String toString() {
		return "Attributes [picture=" + picture + ", name=" + name + ", email=" + email + ", given_name=" + given_name
				+ ", family_name=" + family_name + "]";
	}
}
