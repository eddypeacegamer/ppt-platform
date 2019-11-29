package com.business.unknow.client.facturacionmoderna.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Fault")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacturaModernaErrorModel {

	@XmlElement(name = "faultcode")
	private String faultcode;

	@XmlElement(name = "faultstring")
	private String faultstring;

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	public String getFaultstring() {
		return faultstring;
	}

	public void setFaultstring(String faultstring) {
		this.faultstring = faultstring;
	}

	@Override
	public String toString() {
		return "FacturaModernaErrorModel [faultcode=" + faultcode + ", faultstring=" + faultstring + "]";
	}

}
