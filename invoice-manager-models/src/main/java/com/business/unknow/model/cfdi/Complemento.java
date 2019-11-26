package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlElement;

public class Complemento {

	private ComplementoPagos complemntoPago;
	
	public Complemento() {
		complemntoPago= new ComplementoPagos();
	}

	@XmlElement(name = "pago10:Pagos")
	public ComplementoPagos getComplemntoPago() {
		return complemntoPago;
	}

	public void setComplemntoPago(ComplementoPagos complemntoPago) {
		this.complemntoPago = complemntoPago;
	}

	@Override
	public String toString() {
		return "Complemento [complemntoPago=" + complemntoPago + "]";
	}

}
