package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "claveProdServ", "noIdentificacion", "cantidad", "claveUnidad", "unidad", "descripcion",
		"valorUnitario", "importe","descuento","impuestos" })
public class Concepto {

	private String claveProdServ;
	private String noIdentificacion;
	private Integer cantidad;
	private String claveUnidad;
	private String unidad;
	private String descripcion;
	private Double valorUnitario;
	private Double importe;
	private Double descuento;
	private Impuesto impuestos;

	@XmlAttribute(name = "ClaveProdServ")
	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
	}

	@XmlAttribute(name = "NoIdentificacion")
	public String getNoIdentificacion() {
		return noIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}

	@XmlAttribute(name = "ClaveUnidad")
	public String getClaveUnidad() {
		return claveUnidad;
	}

	public void setClaveUnidad(String claveUnidad) {
		this.claveUnidad = claveUnidad;
	}

	@XmlAttribute(name = "Unidad")
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@XmlAttribute(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlAttribute(name = "ValorUnitario")
	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@XmlAttribute(name = "Importe")
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@XmlAttribute(name = "Descuento")
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@XmlElement(name = "cfdi:Impuestos")
	public Impuesto getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Impuesto impuestos) {
		this.impuestos = impuestos;
	}

	@XmlAttribute(name = "Cantidad")
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Concepto [claveProdServ=" + claveProdServ + ", noIdentificacion=" + noIdentificacion + ", claveUnidad="
				+ claveUnidad + ", unidad=" + unidad + ", descripcion=" + descripcion + ", valorUnitario="
				+ valorUnitario + ", importe=" + importe + ", descuento=" + descuento + "]";
	}

}
