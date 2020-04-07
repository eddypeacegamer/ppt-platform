package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "Complemento", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.NONE)
public class Complemento {

	@XmlElement(name = "Pagos", namespace = "http://www.sat.gob.mx/Pagos")
	private ComplementoPagos complemntoPago;
	@XmlElement(name = "TimbreFiscalDigital",namespace = "http://www.sat.gob.mx/TimbreFiscalDigital")
	private TimbreFiscalDigital timbreFiscalDigital;

	public Complemento() {
		complemntoPago = new ComplementoPagos();
	}

	public ComplementoPagos getComplemntoPago() {
		return complemntoPago;
	}

	public void setComplemntoPago(ComplementoPagos complemntoPago) {
		this.complemntoPago = complemntoPago;
	}

	public TimbreFiscalDigital getTimbreFiscalDigital() {
		return timbreFiscalDigital;
	}

	public void setTimbreFiscalDigital(TimbreFiscalDigital timbreFiscalDigital) {
		this.timbreFiscalDigital = timbreFiscalDigital;
	}

	@Override
	public String toString() {
		return "Complemento [complemntoPago=" + complemntoPago + ", timbreFiscalDigital=" + timbreFiscalDigital + "]";
	}

}
