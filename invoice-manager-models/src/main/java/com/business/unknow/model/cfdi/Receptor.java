package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Receptor", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.FIELD)
public class Receptor {

	@XmlAttribute(name = "Rfc")
	private String rfc;
	@XmlAttribute(name = "Nombre")
	private String nombre;
	@XmlAttribute(name = "UsoCFDI")
	private String usoCfdi;

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
