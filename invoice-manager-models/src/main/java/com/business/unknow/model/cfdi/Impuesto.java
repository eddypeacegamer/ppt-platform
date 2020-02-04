package com.business.unknow.model.cfdi;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Impuestos", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.FIELD)
public class Impuesto {

	@XmlAttribute(name = "TotalImpuestosTrasladados")
	private Double totalImpuestosTrasladados;
	@XmlElementWrapper(name = "Traslados", namespace = "http://www.sat.gob.mx/cfd/3")
	@XmlElement(name = "Traslado", namespace = "http://www.sat.gob.mx/cfd/3")
	private List<Translado> translados;

	public Impuesto() {
	}
	
	public Double getTotalImpuestosTrasladados() {
		return totalImpuestosTrasladados;
	}

	public void setTotalImpuestosTrasladados(Double totalImpuestosTrasladados) {
		this.totalImpuestosTrasladados = totalImpuestosTrasladados;
	}

	
	public List<Translado> getTranslados() {
		return translados;
	}

	public void setTranslados(List<Translado> translados) {
		this.translados = translados;
	}

	@Override
	public String toString() {
		return "Impuesto [totalImpuestosTrasladados=" + totalImpuestosTrasladados + ", translados=" + translados + "]";
	}

}
