package com.business.unknow.model.cfdi;

import java.math.BigDecimal;
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
	@XmlAttribute(name = "FormaDePagoDesc")
	private String formaDePagoDesc;
	@XmlAttribute(name = "MonedaP")
	private String moneda;
	@XmlAttribute(name = "Monto")
	private String monto;
	@XmlAttribute(name = "TipoCambioP")
	private BigDecimal tipoCambioP;
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

	public String getFormaDePagoDesc() {
		return formaDePagoDesc;
	}

	public void setFormaDePagoDesc(String formaDePagoDesc) {
		this.formaDePagoDesc = formaDePagoDesc;
	}

	public BigDecimal getTipoCambioP() {
		return tipoCambioP;
	}

	public void setTipoCambioP(BigDecimal tipoCambioP) {
		this.tipoCambioP = tipoCambioP;
	}

	@Override
	public String toString() {
		return "ComplementoPago [fechaPago=" + fechaPago + ", formaDePago=" + formaDePago + ", formaDePagoDesc="
				+ formaDePagoDesc + ", moneda=" + moneda + ", monto=" + monto + ", tipoCambioP=" + tipoCambioP
				+ ", complementoDocRelacionado=" + complementoDocRelacionado + "]";
	}

}
