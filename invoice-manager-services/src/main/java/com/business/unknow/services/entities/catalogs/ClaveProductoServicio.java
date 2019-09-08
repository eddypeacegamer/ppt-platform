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
	private String descipcion;
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
	public String getDescipcion() {
		return descipcion;
	}
	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
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
		return "ClaveProductoServicio [clave=" + clave + ", descipcion=" + descipcion + ", similares=" + similares
				+ ", inicioVigencia=" + inicioVigencia + "]";
	}
}
