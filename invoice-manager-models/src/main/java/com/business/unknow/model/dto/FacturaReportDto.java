/**
 * 
 */
package com.business.unknow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hha0009
 *
 */
public class FacturaReportDto extends ReportDto implements Serializable {
	
	private static final long serialVersionUID = -1523422223111592963L;
	
	private BigDecimal cantidad;
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
	
}
