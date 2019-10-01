package com.business.unknow.model.cdfi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "rfc", "nombre", "regimenFiscal"})
public class Emisor {

	private String rfc;
	private String nombre;
	private String regimenFiscal;

	@XmlAttribute(name = "Rfc")
	public String getRfc() {
		return rfc;
	}
	
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@XmlAttribute(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlAttribute(name = "RegimenFiscal")
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
