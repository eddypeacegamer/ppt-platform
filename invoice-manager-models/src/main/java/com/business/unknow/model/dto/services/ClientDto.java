package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto implements Serializable {

	private static final long serialVersionUID = 4951260411762447946L;

	private int id;
	private Boolean activo;
	private Double porcentajePromotor;
	private Double porcentajeCliente;
	private Double porcentajeDespacho;
	private Double porcentajeContacto;
	private String correoPromotor;
	private String correoContacto;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaActualizacion;
	private ContribuyenteDto informacionFiscal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Double getPorcentajePromotor() {
		return porcentajePromotor;
	}
	public void setPorcentajePromotor(Double porcentajePromotor) {
		this.porcentajePromotor = porcentajePromotor;
	}
	public Double getPorcentajeCliente() {
		return porcentajeCliente;
	}
	public void setPorcentajeCliente(Double porcentajeCliente) {
		this.porcentajeCliente = porcentajeCliente;
	}
	public Double getPorcentajeDespacho() {
		return porcentajeDespacho;
	}
	public void setPorcentajeDespacho(Double porcentajeDespacho) {
		this.porcentajeDespacho = porcentajeDespacho;
	}
	public Double getPorcentajeContacto() {
		return porcentajeContacto;
	}
	public void setPorcentajeContacto(Double porcentajeContacto) {
		this.porcentajeContacto = porcentajeContacto;
	}
	public String getCorreoPromotor() {
		return correoPromotor;
	}
	public void setCorreoPromotor(String correoPromotor) {
		this.correoPromotor = correoPromotor;
	}
	public String getCorreoContacto() {
		return correoContacto;
	}
	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
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
	public ContribuyenteDto getInformacionFiscal() {
		return informacionFiscal;
	}
	public void setInformacionFiscal(ContribuyenteDto informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}
	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", activo=" + activo + ", porcentajePromotor=" + porcentajePromotor
				+ ", porcentajeCliente=" + porcentajeCliente + ", porcentajeDespacho=" + porcentajeDespacho
				+ ", porcentajeContacto=" + porcentajeContacto + ", correoPromotor=" + correoPromotor
				+ ", correoContacto=" + correoContacto + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + ", informacionFiscal=" + informacionFiscal + "]";
	}
}
