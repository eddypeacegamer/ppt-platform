package com.business.unknow.model.factura;

import java.util.Date;

public class PagoDto {

	private Integer id;
	private String folio;
	private String tipoDocumento;
	private String documento;
	private Double candtidad;
	private String tipoPago;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Double getCandtidad() {
		return candtidad;
	}

	public void setCandtidad(Double candtidad) {
		this.candtidad = candtidad;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		return "PagoDto [id=" + id + ", folio=" + folio + ", tipoDocumento=" + tipoDocumento + ", documento="
				+ documento + ", candtidad=" + candtidad + ", tipoPago=" + tipoPago + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
