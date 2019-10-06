/**
 * 
 */
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
public class UserDetails implements Serializable {

	
	private static final long serialVersionUID = 2366067960380180153L;
	private String authority;
	private Attributes attributes;
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "UserDetails [authority=" + authority + ", attributes=" + attributes + "]";
	}
	
}
