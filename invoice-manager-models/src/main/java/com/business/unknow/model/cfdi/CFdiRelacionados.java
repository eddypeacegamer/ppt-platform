package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CfdiRelacionados", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.FIELD)
public class CFdiRelacionados {

	@XmlAttribute(name = "TipoRelacion")
	private String tipoRelacion;

	@XmlElement(name = "CfdiRelacionado", namespace = "http://www.sat.gob.mx/cfd/3")
	private CfdiRelacionado cfdiRelacionado;
	
	public CFdiRelacionados() {
	}

	public CFdiRelacionados(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public String getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public CfdiRelacionado getCfdiRelacionado() {
		return cfdiRelacionado;
	}

	public void setCfdiRelacionado(CfdiRelacionado cfdiRelacionado) {
		this.cfdiRelacionado = cfdiRelacionado;
	}

	@Override
	public String toString() {
		return "CFdiRelacionados [tipoRelacion=" + tipoRelacion + ", cfdiRelacionado=" + cfdiRelacionado + "]";
	}

}
