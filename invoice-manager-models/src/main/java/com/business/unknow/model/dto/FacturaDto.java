package com.business.unknow.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacturaDto implements Serializable {

	private static final long serialVersionUID = -1019751668989298682L;
	private Integer id;
	private String rfcEmisor;
	private String rfcRemitente;
	private String razonSocialEmisor;
	private String lineaEmisor;
	private String razonSocialRemitente;
	private String lineaRemitente;
	private String tipoDocumento;
	private String solicitante;
	private String folio;
	private String folioPadre;
	private String uuid;
	private Integer statusPago;
	private Integer statusDevolucion;
	private Integer statusFactura;
	private String statusDetail;
	private String packFacturacion;
	private String metodoPago;
	private String notas;
	private Date fechaCreacion;
	private String statusCancelacion;
	private Date fechaCancelacion;
	private Date fechaActualizacion;
	private Date fechaTimbrado;
	private Integer statusCancelado;
	private Integer idCfdi;
	private CfdiDto cfdi;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getRfcRemitente() {
		return rfcRemitente;
	}

	public void setRfcRemitente(String rfcRemitente) {
		this.rfcRemitente = rfcRemitente;
	}

	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}

	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
	}

	public String getLineaEmisor() {
		return lineaEmisor;
	}

	public void setLineaEmisor(String lineaEmisor) {
		this.lineaEmisor = lineaEmisor;
	}

	public String getRazonSocialRemitente() {
		return razonSocialRemitente;
	}

	public void setRazonSocialRemitente(String razonSocialRemitente) {
		this.razonSocialRemitente = razonSocialRemitente;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getLineaRemitente() {
		return lineaRemitente;
	}

	public void setLineaRemitente(String lineaRemitente) {
		this.lineaRemitente = lineaRemitente;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFolioPadre() {
		return folioPadre;
	}

	public void setFolioPadre(String folioPadre) {
		this.folioPadre = folioPadre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(Integer statusPago) {
		this.statusPago = statusPago;
	}

	public Integer getStatusDevolucion() {
		return statusDevolucion;
	}

	public void setStatusDevolucion(Integer statusDevolucion) {
		this.statusDevolucion = statusDevolucion;
	}

	public Integer getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(Integer statusFactura) {
		this.statusFactura = statusFactura;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getPackFacturacion() {
		return packFacturacion;
	}

	public void setPackFacturacion(String packFacturacion) {
		this.packFacturacion = packFacturacion;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public Integer getIdCfdi() {
		return idCfdi;
	}

	public void setIdCfdi(Integer idCfdi) {
		this.idCfdi = idCfdi;
	}

	public CfdiDto getCfdi() {
		return cfdi;
	}

	public void setCfdi(CfdiDto cfdi) {
		this.cfdi = cfdi;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getStatusCancelacion() {
		return statusCancelacion;
	}

	public void setStatusCancelacion(String statusCancelacion) {
		this.statusCancelacion = statusCancelacion;
	}

	public Integer getStatusCancelado() {
		return statusCancelado;
	}

	public void setStatusCancelado(Integer statusCancelado) {
		this.statusCancelado = statusCancelado;
	}

	@Override
	public String toString() {
		return "FacturaDto [id=" + id + ", rfcEmisor=" + rfcEmisor + ", rfcRemitente=" + rfcRemitente
				+ ", razonSocialEmisor=" + razonSocialEmisor + ", lineaEmisor=" + lineaEmisor
				+ ", razonSocialRemitente=" + razonSocialRemitente + ", lineaRemitente=" + lineaRemitente
				+ ", tipoDocumento=" + tipoDocumento + ", solicitante=" + solicitante + ", folio=" + folio
				+ ", folioPadre=" + folioPadre + ", uuid=" + uuid + ", statusPago=" + statusPago + ", statusDevolucion="
				+ statusDevolucion + ", statusFactura=" + statusFactura + ", statusDetail=" + statusDetail
				+ ", packFacturacion=" + packFacturacion + ", metodoPago=" + metodoPago + ", notas=" + notas
				+ ", fechaCreacion=" + fechaCreacion + ", statusCancelacion=" + statusCancelacion
				+ ", fechaCancelacion=" + fechaCancelacion + ", fechaActualizacion=" + fechaActualizacion
				+ ", fechaTimbrado=" + fechaTimbrado + ", idCfdi=" + idCfdi + ", cfdi=" + cfdi + "]";
	}

}
