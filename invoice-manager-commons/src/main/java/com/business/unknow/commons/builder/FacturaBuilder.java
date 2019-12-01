package com.business.unknow.commons.builder;

import com.business.unknow.model.factura.FacturaDto;

public class FacturaBuilder extends AbstractBuilder<FacturaDto> {

	public FacturaBuilder() {
		super(new FacturaDto());
	}

	public FacturaBuilder setRfcEmisor(String rfcEmisor) {
		instance.setRfcEmisor(rfcEmisor);
		return this;
	}

	public FacturaBuilder setRfcRemitente(String rfcRemitente) {
		instance.setRfcRemitente(rfcRemitente);
		return this;
	}

	public FacturaBuilder setRazonSocialEmisor(String razonSocialEmisor) {
		instance.setRazonSocialEmisor(razonSocialEmisor);
		return this;
	}

	public FacturaBuilder setRazonSocialRemitente(String razonSocialRemitente) {
		instance.setRazonSocialRemitente(razonSocialRemitente);
		return this;
	}

	public FacturaBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}

	public FacturaBuilder setFolioPadre(String folioPadre) {
		instance.setFolioPadre(folioPadre);
		return this;
	}

	public FacturaBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}

	public FacturaBuilder setTotal(double total) {
		instance.setTotal(total);
		return this;
	}
	
	public FacturaBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}
	
	public FacturaBuilder setPackFacturacion(String packFacturacion) {
		instance.setPackFacturacion(packFacturacion);
		return this;
	}
	
	public FacturaBuilder setTipoDocumento(String tipoDocumento) {
		instance.setTipoDocumento(tipoDocumento);
		return this;
	}

}
