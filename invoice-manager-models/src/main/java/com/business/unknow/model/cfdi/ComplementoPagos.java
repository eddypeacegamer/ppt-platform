package com.business.unknow.model.cfdi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.business.unknow.Constants.CfdiConstants;

public class ComplementoPagos {

	private String version = CfdiConstants.FACTURA_COMPLEMENTO_VERSION;
	private List<ComplementoPago> complementoPagos;

	public ComplementoPagos() {
		this.complementoPagos = new ArrayList<>();
	}

	@XmlAttribute(name = "Version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlElement(name = "pago10:Pago")
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
