package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "base","impuesto", "tipoFactor", "tasaOCuota", "importe" })
public class TransladoDto {
	private Double base;
	private String impuesto;
	private String tipoFactor;
	private String tasaOCuota;
	private Double importe;

	@XmlAttribute(name = "Impuesto")
	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	@XmlAttribute(name = "TipoFactor")
	public String getTipoFactor() {
		return tipoFactor;
	}

	@XmlAttribute(name = "TasaOCuota")
	public String getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public void setTasaOCuota(String tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	@XmlAttribute(name = "Importe")
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@XmlAttribute(name = "Base")
	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "Translado [impuesto=" + impuesto + ", tipoFactor=" + tipoFactor + ", tasaOCuota=" + tasaOCuota
				+ ", importe=" + importe + "]";
	}

}
