package com.business.unknow.model.catalogs;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegimenFiscalDto implements Serializable{

	private static final long serialVersionUID = -6301363194780882965L;
	private Integer clave;
	private String descripcion;
	private boolean pFisica;
	private boolean pMoral;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
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
	public boolean ispFisica() {
		return pFisica;
	}
	public void setpFisica(boolean pFisica) {
		this.pFisica = pFisica;
	}
	public boolean ispMoral() {
		return pMoral;
	}
	public void setpMoral(boolean pMoral) {
		this.pMoral = pMoral;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	@Override
	public String toString() {
		return "RegimenFiscalDto [clave=" + clave + ", descripcion=" + descripcion + ", pFisica=" + pFisica
				+ ", pMoral=" + pMoral + ", inicioVigencia=" + inicioVigencia + "]";
	}
	
	
}
