package com.business.unknow.services.entities.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLAVE_UNIDAD")
public class ClaveUnidad {
	
	@Id
	@Column(name = "CLAVE")
	private String clave;
	@Column(name = "TIPO")
	private String tipo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "NOMBRE")
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
		return "ClaveUnidad [clave=" + clave + ", tipo=" + tipo + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ "]";
	}
}
