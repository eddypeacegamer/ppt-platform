package com.business.unknow.model.cfdi;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Retencion", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlType(propOrder={"base", "impuesto","tipoFactor","tasaOCuota","importe"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Retencion {
	@XmlAttribute(name = "Base")
	private BigDecimal base;
	@XmlAttribute(name = "Impuesto")
	private String impuesto;
	@XmlAttribute(name = "TipoFactor")
	private String tipoFactor;
	@XmlAttribute(name = "TasaOCuota")
	private String tasaOCuota;
	@XmlAttribute(name = "Importe")
	private BigDecimal importe;

	public Retencion() {
	}

	public Retencion(String impuesto, String tipoFactor, String tasaOCuota, BigDecimal importe) {
		this.impuesto = impuesto;
		this.tipoFactor = tipoFactor;
		this.tasaOCuota = tasaOCuota;
		this.importe = importe;
	}
	
	public Retencion(String impuesto,BigDecimal importe) {
		this.impuesto = impuesto;
		this.importe = importe;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getTipoFactor() {
		return tipoFactor;
	}

	public String getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public void setTasaOCuota(String tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "Translado [impuesto=" + impuesto + ", tipoFactor=" + tipoFactor + ", tasaOCuota=" + tasaOCuota
				+ ", importe=" + importe + "]";
	}

}
