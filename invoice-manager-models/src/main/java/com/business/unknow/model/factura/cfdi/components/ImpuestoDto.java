package com.business.unknow.model.factura.cfdi.components;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpuestoDto implements Serializable{

	private static final long serialVersionUID = 3241569278979852126L;
	private Integer id;
	private BigDecimal base;
	private String impuesto;
	private String tipoFactor;
	private BigDecimal tasaOCuota;
	private BigDecimal importe;
	
	public ImpuestoDto() {
	}
	
	public ImpuestoDto(BigDecimal base, BigDecimal importe, BigDecimal tasaOcuota) {
		this.base = base;
		this.importe = importe;
		this.tasaOCuota = tasaOcuota;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getTipoFactor() {
		return tipoFactor;
	}

	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}
	
	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTasaOCuota(BigDecimal tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "ImpuestoDto [id=" + id + ", base=" + base + ", impuesto=" + impuesto + ", tipoFactor=" + tipoFactor
				+ ", tasaOCuota=" + tasaOCuota + ", importe=" + importe + "]";
	}

}
