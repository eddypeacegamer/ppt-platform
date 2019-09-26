package com.business.unknow.services.entities.catalogs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "REGIMEN_FISCAL")
public class RegimenFiscal {
	
	@Id
	@Column(name = "CLAVE")
	private Integer clave;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "P_FISICA")
	private boolean pFisica;
	
	@Column(name = "P_MORAL")
	private boolean pMoral;
	
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
		return "RegimenFiscal [clave=" + clave + ", descripcion=" + descripcion + ", pFisica=" + pFisica + ", pMoral="
				+ pMoral + ", inicioVigencia=" + inicioVigencia + "]";
	}

}
