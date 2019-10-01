package com.business.unknow.model.cdfi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "rfc", "nombre", "usoCdfi"})
public class Receptor {

	private String rfc;
	private String nombre;
	private String usoCdfi;

	public String getRfc() {
		return rfc;
	}

	@XmlAttribute(name = "Rfc")
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	@XmlAttribute(name = "Nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsoCdfi() {
		return usoCdfi;
	}

	@XmlAttribute(name = "UsoCdfi")
	public void setUsoCdfi(String usoCdfi) {
		this.usoCdfi = usoCdfi;
	}

	@Override
	public String toString() {
		return "Receptor [rfc=" + rfc + ", nombre=" + nombre + ", usoCdfi=" + usoCdfi + "]";
	}
}
