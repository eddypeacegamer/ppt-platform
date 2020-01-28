package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;

public class EmisorDto implements Serializable {

	
	private static final long serialVersionUID = -6859374411200231323L;
	
	private String rfc;
	private String nombre;
	private String regimenFiscal;
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegimenFiscal() {
		return regimenFiscal;
	}
	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}
	@Override
	public String toString() {
		return "Emisor [rfc=" + rfc + ", nombre=" + nombre + ", regimenFiscal=" + regimenFiscal + "]";
	}
	
}
