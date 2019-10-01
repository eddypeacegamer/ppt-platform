package com.business.unknow.model.cdfi;

public class Concepto {

	private String ClaveProdServ;
	private String NoIdentificacion;
	private String ClaveUnidad;
	private String Unidad;
	private String Descripcion;
	private Double ValorUnitario;
	private Double Importe;
	private Double descuento;

	public String getClaveProdServ() {
		return ClaveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		ClaveProdServ = claveProdServ;
	}

	public String getNoIdentificacion() {
		return NoIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		NoIdentificacion = noIdentificacion;
	}

	public String getClaveUnidad() {
		return ClaveUnidad;
	}

	public void setClaveUnidad(String claveUnidad) {
		ClaveUnidad = claveUnidad;
	}

	public String getUnidad() {
		return Unidad;
	}

	public void setUnidad(String unidad) {
		Unidad = unidad;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Double getValorUnitario() {
		return ValorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		ValorUnitario = valorUnitario;
	}

	public Double getImporte() {
		return Importe;
	}

	public void setImporte(Double importe) {
		Importe = importe;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
}
