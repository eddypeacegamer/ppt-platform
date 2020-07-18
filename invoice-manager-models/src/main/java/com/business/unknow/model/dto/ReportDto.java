/**
 * 
 */
package com.business.unknow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ralfdemoledor
 *
 */
public class ReportDto implements Serializable{
	
	private static final long serialVersionUID = 8443985089248503145L;
	
	
	private String folio;
	private String folioFiscal;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaEmision;
	private String rfcEmisor;
	private String emisor;		
	private String rfcReceptor;
	private String receptor;
	private String tipoDocumento;
	private String packFacturacion;
	private String tipoComprobante;
	private BigDecimal impuestosTrasladados;
	private BigDecimal impuestosRetenidos;
	private BigDecimal subtotal;
	private BigDecimal total;
	private String metodoPago;
	private String formaPago;
	private String moneda;
	private String statusFactura;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCancelacion;
	
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFolioFiscal() {
		return folioFiscal;
	}
	public void setFolioFiscal(String folioFiscal) {
		this.folioFiscal = folioFiscal;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getRfcEmisor() {
		return rfcEmisor;
	}
	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getRfcReceptor() {
		return rfcReceptor;
	}
	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}
	public String getReceptor() {
		return receptor;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getPackFacturacion() {
		return packFacturacion;
	}
	public void setPackFacturacion(String packFacturacion) {
		this.packFacturacion = packFacturacion;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public BigDecimal getImpuestosTrasladados() {
		return impuestosTrasladados;
	}
	public void setImpuestosTrasladados(BigDecimal impuestosTrasladados) {
		this.impuestosTrasladados = impuestosTrasladados;
	}
	public BigDecimal getImpuestosRetenidos() {
		return impuestosRetenidos;
	}
	public void setImpuestosRetenidos(BigDecimal impuestosRetenidos) {
		this.impuestosRetenidos = impuestosRetenidos;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getStatusFactura() {
		return statusFactura;
	}
	public void setStatusFactura(String statusFactura) {
		this.statusFactura = statusFactura;
	}
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	
}
