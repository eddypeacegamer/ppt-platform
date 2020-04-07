package com.business.unknow.model.dto.catalogs;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsoCfdiDto implements Serializable{
	
	private static final long serialVersionUID = -3302132000616417709L;
	
	private String clave;
	private String descripcion;
	private boolean pMoral;
	private boolean pFisica;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date inicioVigencia;
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean ispMoral() {
		return pMoral;
	}
	public void setpMoral(boolean pMoral) {
		this.pMoral = pMoral;
	}
	public boolean ispFisica() {
		return pFisica;
	}
	public void setpFisica(boolean pFisica) {
		this.pFisica = pFisica;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	@Override
	public String toString() {
		return "UsoCfdiDto [clave=" + clave + ", descripcion=" + descripcion + ", pMoral=" + pMoral + ", pFisica="
				+ pFisica + ", inicioVigencia=" + inicioVigencia + "]";
	}

}
