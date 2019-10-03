package com.business.unknow.model.cfdi;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Impuesto {

	private Double totalImpuestosTrasladados;
	private List<Translado> translados;

	@XmlAttribute(name = "TotalImpuestosTrasladados")
	public Double getTotalImpuestosTrasladados() {
		return totalImpuestosTrasladados;
	}

	public void setTotalImpuestosTrasladados(Double totalImpuestosTrasladados) {
		this.totalImpuestosTrasladados = totalImpuestosTrasladados;
	}

	@XmlElementWrapper(name = "cfdi:Traslados")
	@XmlElement(name = "cfdi:Traslado")
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
