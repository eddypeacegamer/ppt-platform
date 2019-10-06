package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "rfc", "nombre", "usoCfdi" })
public class ReceptorDto {

	private String rfc;
	private String nombre;
	private String usoCfdi;

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

	@XmlAttribute(name = "UsoCFDI")
	public String getUsoCfdi() {
		return usoCfdi;
	}

	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}

	@Override
	public String toString() {
		return "Receptor [rfc=" + rfc + ", nombre=" + nombre + ", usoCfdi=" + usoCfdi + "]";
	}

}
