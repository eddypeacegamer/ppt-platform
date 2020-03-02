package com.business.unknow.model.cfdi;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Impuestos", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"retenciones","translados"})
public class Impuesto {

	@XmlAttribute(name = "TotalImpuestosTrasladados")
	private BigDecimal totalImpuestosTrasladados;
	@XmlAttribute(name = "TotalImpuestosRetenidos")
	private BigDecimal totalImpuestosRetenidos;
	@XmlElementWrapper(name = "Retenciones", namespace = "http://www.sat.gob.mx/cfd/3")
	@XmlElement(name = "Retencion", namespace = "http://www.sat.gob.mx/cfd/3")
	private List<Retencion> retenciones;
	@XmlElementWrapper(name = "Traslados", namespace = "http://www.sat.gob.mx/cfd/3")
	@XmlElement(name = "Traslado", namespace = "http://www.sat.gob.mx/cfd/3")
	private List<Translado> translados;

	public Impuesto() {
	}

	public BigDecimal getTotalImpuestosTrasladados() {
		return totalImpuestosTrasladados;
	}

	public void setTotalImpuestosTrasladados(BigDecimal totalImpuestosTrasladados) {
		this.totalImpuestosTrasladados = totalImpuestosTrasladados;
	}

	public List<Translado> getTranslados() {
		return translados;
	}

	public void setTranslados(List<Translado> translados) {
		this.translados = translados;
	}

	public List<Retencion> getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(List<Retencion> retenciones) {
		this.retenciones = retenciones;
	}

	public BigDecimal getTotalImpuestosRetenidos() {
		return totalImpuestosRetenidos;
	}

	public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
		this.totalImpuestosRetenidos = totalImpuestosRetenidos;
	}

	@Override
	public String toString() {
		return "Impuesto [totalImpuestosTrasladados=" + totalImpuestosTrasladados + ", translados=" + translados + "]";
	}

}
