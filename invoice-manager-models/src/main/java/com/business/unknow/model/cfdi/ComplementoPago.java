package com.business.unknow.model.cfdi;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Pago", namespace = "http://www.sat.gob.mx/Pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComplementoPago {

	@XmlAttribute(name = "FechaPago")
	private String fechaPago;
	@XmlAttribute(name = "FormaDePagoP")
	private String formaDePago;
	@XmlAttribute(name = "MonedaP")
	private String moneda;
	@XmlAttribute(name = "Monto")
	private String monto;
	@XmlElement(name = "DoctoRelacionado", namespace = "http://www.sat.gob.mx/Pagos")
	private List<ComplementoDocRelacionado> complementoDocRelacionado;

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public List<ComplementoDocRelacionado> getComplementoDocRelacionado() {
		return complementoDocRelacionado;
	}

	public void setComplementoDocRelacionado(List<ComplementoDocRelacionado> complementoDocRelacionado) {
		this.complementoDocRelacionado = complementoDocRelacionado;
	}

	@Override
	public String toString() {
		return "ComplementoPago [fechaPago=" + fechaPago + ", formaDePago=" + formaDePago + ", moneda=" + moneda
				+ ", monto=" + monto + ", complementoDocRelacionado=" + complementoDocRelacionado + "]";
	}

}
