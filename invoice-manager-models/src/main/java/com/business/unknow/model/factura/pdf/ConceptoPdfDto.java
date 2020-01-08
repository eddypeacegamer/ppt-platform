package com.business.unknow.model.factura.pdf;

import java.io.Serializable;

public class ConceptoPdfDto implements Serializable {
	
	private static final long serialVersionUID = 400017185137932168L;
	
	private String cantidad;
	private String claveSat;
	private String descripcion;
	private String unidad;
	private String precioUnitario;
	private String descuento;
	private String importe;
	
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getClaveSat() {
		return claveSat;
	}
	public void setClaveSat(String claveSat) {
		this.claveSat = claveSat;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	@Override
	public String toString() {
		return "ConceptoPdfDto [cantidad=" + cantidad + ", claveSat=" + claveSat + ", descripcion=" + descripcion
				+ ", unidad=" + unidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento
				+ ", importe=" + importe + "]";
	}
	
	

}
