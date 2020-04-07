package com.business.unknow.model.cfdi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.business.unknow.Constants.CfdiConstants;

@XmlRootElement(name = "Pagos",namespace = "http://www.sat.gob.mx/Pagos")
@XmlAccessorType(XmlAccessType.NONE)
public class ComplementoPagos {

	@XmlAttribute(name = "Version")
	private String version = CfdiConstants.FACTURA_COMPLEMENTO_VERSION;
	@XmlElement(name = "Pago",namespace = "http://www.sat.gob.mx/Pagos")
	private List<ComplementoPago> complementoPagos;

	
	public ComplementoPagos() {
		this.complementoPagos = new ArrayList<>();
	}

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	public List<ComplementoPago> getComplementoPagos() {
		return complementoPagos;
	}

	public void setComplementoPagos(List<ComplementoPago> complementoPagos) {
		this.complementoPagos = complementoPagos;
	}

	@Override
	public String toString() {
		return "ComplementoPagos [version=" + version + ", complementoPagos=" + complementoPagos + "]";
	}

}
