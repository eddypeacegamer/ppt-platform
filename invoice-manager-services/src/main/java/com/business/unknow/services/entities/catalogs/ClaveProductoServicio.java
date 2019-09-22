/**
 * 
 */
package com.business.unknow.services.entities.catalogs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ralfdemoledor
 *
 */
@Entity
@Table(name = "CLAVE_PROD_SERV")
public class ClaveProductoServicio {
	
	@Id
	@Column(name = "CLAVE")
	private Integer clave;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "SIMILARES")
	private String similares;
	@Column(name = "INICIO_VIGENCIA")
	private Date inicioVigencia;
	public Integer getClave() {
		return clave;
	}
	public void setClave(Integer clave) {
		this.clave = clave;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSimilares() {
		return similares;
	}
	public void setSimilares(String similares) {
		this.similares = similares;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	@Override
	public String toString() {
		return "ClaveProductoServicio [clave=" + clave + ", descripcion=" + descripcion + ", similares=" + similares
				+ ", inicioVigencia=" + inicioVigencia + "]";
	}

}
