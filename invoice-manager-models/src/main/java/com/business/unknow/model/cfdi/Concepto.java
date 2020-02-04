package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Concepto", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlAccessorType(XmlAccessType.FIELD)
public class Concepto {

	@XmlAttribute(name = "ClaveProdServ")
	private String claveProdServ;
	@XmlAttribute(name = "NoIdentificacion")
	private String noIdentificacion;
	@XmlAttribute(name = "Cantidad")
	private Integer cantidad;
	@XmlAttribute(name = "ClaveUnidad")
	private String claveUnidad;
	@XmlAttribute(name = "Unidad")
	private String unidad;
	@XmlAttribute(name = "Descripcion")
	private String descripcion;
	@XmlAttribute(name = "ValorUnitario")
	private Double valorUnitario;
	@XmlAttribute(name = "Importe")
	private Double importe;
	@XmlAttribute(name = "Descuento")
	private Double descuento;
	@XmlElement(name = "Impuestos", namespace = "http://www.sat.gob.mx/cfd/3")
	private Impuesto impuestos;

	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
	}

	public String getNoIdentificacion() {
		return noIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}

	public String getClaveUnidad() {
		return claveUnidad;
	}

	public void setClaveUnidad(String claveUnidad) {
		this.claveUnidad = claveUnidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Impuesto getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Impuesto impuestos) {
		this.impuestos = impuestos;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Concepto [claveProdServ=" + claveProdServ + ", noIdentificacion=" + noIdentificacion + ", cantidad="
				+ cantidad + ", claveUnidad=" + claveUnidad + ", unidad=" + unidad + ", descripcion=" + descripcion
				+ ", valorUnitario=" + valorUnitario + ", importe=" + importe + ", descuento=" + descuento
				+ ", impuestos=" + impuestos + "]";
	}

}
