/**
 * 
 */
package com.business.unknow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ralfdemoledor
 *
 */
public class FacturaReportDto extends ReportDto implements Serializable {

	private static final long serialVersionUID = -1523422223111592963L;

	private BigDecimal cantidad;
	private String lineaEmisor;
	private String correoPromotor;
	private String porcentajePromotor;
	private String porcentajeCliente;
	private String porcentajeConcatco;
	private String porcentajeDespacho;
	private String claveUnidad;
	private String unidad;
	private Integer claveProdServ;
	private String descripcion;
	private BigDecimal valorUnitario;
	private BigDecimal importe;

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
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

	public Integer getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(Integer claveProdServ) {
		this.claveProdServ = claveProdServ;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getLineaEmisor() {
		return lineaEmisor;
	}

	public void setLineaEmisor(String lineaEmisor) {
		this.lineaEmisor = lineaEmisor;
	}

	public String getCorreoPromotor() {
		return correoPromotor;
	}

	public void setCorreoPromotor(String correoPromotor) {
		this.correoPromotor = correoPromotor;
	}

	public String getPorcentajePromotor() {
		return porcentajePromotor;
	}

	public void setPorcentajePromotor(String porcentajePromotor) {
		this.porcentajePromotor = porcentajePromotor;
	}

	public String getPorcentajeCliente() {
		return porcentajeCliente;
	}

	public void setPorcentajeCliente(String porcentajeCliente) {
		this.porcentajeCliente = porcentajeCliente;
	}

	public String getPorcentajeConcatco() {
		return porcentajeConcatco;
	}

	public void setPorcentajeConcatco(String porcentajeConcatco) {
		this.porcentajeConcatco = porcentajeConcatco;
	}

	public String getPorcentajeDespacho() {
		return porcentajeDespacho;
	}

	public void setPorcentajeDespacho(String porcentajeDespacho) {
		this.porcentajeDespacho = porcentajeDespacho;
	}

	@Override
	public String toString() {
		return "FacturaReportDto [cantidad=" + cantidad + ", lineaEmisor=" + lineaEmisor + ", correoPromotor="
				+ correoPromotor + ", porcentajePromotor=" + porcentajePromotor + ", porcentajeCliente="
				+ porcentajeCliente + ", porcentajeConcatco=" + porcentajeConcatco + ", porcentajeDespacho="
				+ porcentajeDespacho + ", claveUnidad=" + claveUnidad + ", unidad=" + unidad + ", claveProdServ="
				+ claveProdServ + ", descripcion=" + descripcion + ", valorUnitario=" + valorUnitario + ", importe="
				+ importe + "]";
	}

}
