/**
 * 
 */
package com.business.unknow.model.dto.catalogs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ralfdemoledor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaveUnidadDto implements Serializable {

	private static final long serialVersionUID = 2485025222885106558L;
	
	private String clave;
	private String tipo;
	private String descripcion;
	private String nombre;
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "ClaveUnidadDto [clave=" + clave + ", tipo=" + tipo + ", descripcion=" + descripcion + ", nombre="
				+ nombre + "]";
	}

}
