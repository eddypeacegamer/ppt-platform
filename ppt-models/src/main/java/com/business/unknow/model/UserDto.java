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
	private String name;
	

	private static final long serialVersionUID = -4269713581531174125L;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "UserDto [name=" + name + "]";
	}

}
