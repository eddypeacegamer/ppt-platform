package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ComplementoPago {

	private String fechaPago;
	private String formaDePago;
	private String moneda;
	private Double monto;
	private ComplementoDocRelacionado complementoDocRelacionado;

	@XmlAttribute(name = "FechaPago")
	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	@XmlAttribute(name = "FormaDePagoP")
	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	@XmlAttribute(name = "MonedaP")
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@XmlAttribute(name = "Monto")
	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@XmlElement(name = "pago10:DoctoRelacionado")
	public ComplementoDocRelacionado getComplementoDocRelacionado() {
		return complementoDocRelacionado;
	}

	public void setComplementoDocRelacionado(ComplementoDocRelacionado complementoDocRelacionado) {
		this.complementoDocRelacionado = complementoDocRelacionado;
	}

	@Override
	public String toString() {
		return "ComplementoPago [fechaPago=" + fechaPago + ", formaDePago=" + formaDePago + ", moneda=" + moneda
				+ ", monto=" + monto + ", complementoDocRelacionado=" + complementoDocRelacionado + "]";
	}

}
