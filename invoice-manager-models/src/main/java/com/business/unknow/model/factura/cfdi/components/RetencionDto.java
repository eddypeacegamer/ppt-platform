package com.business.unknow.model.factura.cfdi.components;

public class RetencionDto {

	private Integer id;
	private Double base;
	private String impuesto;
	private String tipoFactor;
	private String tasaOCuota;
	private Double importe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
		this.base = base;
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

	public String getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTasaOCuota(String tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "RetencionDto [id=" + id + ", base=" + base + ", impuesto=" + impuesto + ", tipoFactor=" + tipoFactor
				+ ", tasaOCuota=" + tasaOCuota + ", importe=" + importe + "]";
	}

}
