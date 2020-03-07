package com.business.unknow.model.dto;

public class FacturaPdfModelDto {

	private String qr;
	private String logotipo;
	private FacturaDto factura;

	public FacturaPdfModelDto() {
		super();
	}

	public FacturaPdfModelDto(String qr, String logotipo, FacturaDto factura) {
		super();
		this.qr = qr;
		this.logotipo = logotipo;
		this.factura = factura;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public FacturaDto getFactura() {
		return factura;
	}

	public void setFactura(FacturaDto factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "FacturaPdfModel [qr=" + qr + ", logotipo=" + logotipo + ", factura=" + factura + "]";
	}

}
