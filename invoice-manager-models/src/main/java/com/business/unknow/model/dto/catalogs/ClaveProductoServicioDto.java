/**
 * 
 */
package com.business.unknow.model.dto.catalogs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ralfdemoledor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaveProductoServicioDto implements Serializable {

	private static final long serialVersionUID = -8554744373606960846L;
	private Integer clave;
	private String descripcion;
	private String similares;
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
		return "ClaveProductoServicioDto [clave=" + clave + ", descripcion=" + descripcion + ", similares=" + similares
				+ ", inicioVigencia=" + inicioVigencia + "]";
	}
}
