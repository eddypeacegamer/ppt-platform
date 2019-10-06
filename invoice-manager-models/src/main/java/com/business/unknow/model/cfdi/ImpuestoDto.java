package com.business.unknow.model.cfdi;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ImpuestoDto {

	private Double totalImpuestosTrasladados;
	private List<TransladoDto> translados;

	@XmlAttribute(name = "TotalImpuestosTrasladados")
	public Double getTotalImpuestosTrasladados() {
		return totalImpuestosTrasladados;
	}

	public void setTotalImpuestosTrasladados(Double totalImpuestosTrasladados) {
		this.totalImpuestosTrasladados = totalImpuestosTrasladados;
	}

	@XmlElementWrapper(name = "cfdi:Traslados")
	@XmlElement(name = "cfdi:Traslado")
	public List<TransladoDto> getTranslados() {
		return translados;
	}

	public void setTranslados(List<TransladoDto> translados) {
		this.translados = translados;
	}

	@Override
	public String toString() {
		return "Impuesto [totalImpuestosTrasladados=" + totalImpuestosTrasladados + ", translados=" + translados + "]";
	}

}
