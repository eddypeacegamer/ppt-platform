package com.business.unknow.commons.builder;

import java.math.BigDecimal;

import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;

public class FacturaBuilder extends AbstractBuilder<FacturaDto> {

	public FacturaBuilder() {
		super(new FacturaDto());
	}
	
	public FacturaBuilder(FacturaDto facturaDto) {
		super(facturaDto);
	}

	public FacturaBuilder setRfcEmisor(String rfcEmisor) {
		instance.setRfcEmisor(rfcEmisor);
		return this;
	}
	
	public FacturaBuilder setLineaEmisor(String lineaEmisor) {
		instance.setLineaEmisor(lineaEmisor);
		return this;
	}

	public FacturaBuilder setRfcRemitente(String rfcRemitente) {
		instance.setRfcRemitente(rfcRemitente);
		return this;
	}
	
	public FacturaBuilder setLineaRemitente(String lineaRemitente) {
		instance.setLineaRemitente(lineaRemitente);
		return this;
	}
	
	public FacturaBuilder setSolicitante(String solicitante) {
		instance.setSolicitante(solicitante);
		return this;
	}

	public FacturaBuilder setRazonSocialEmisor(String razonSocialEmisor) {
		instance.setRazonSocialEmisor(razonSocialEmisor);
		return this;
	}
	
	public FacturaBuilder setTotal(BigDecimal total) {
		instance.setTotal(total);
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

	public FacturaBuilder setCfdi(CfdiDto cfdi) {
		if(cfdi==null) {
			instance.setCfdi(new CfdiDto());
		}else {
			instance.setCfdi(cfdi);
		}
		return this;
	}
	
	public FacturaBuilder setTipoDocumento(String tipoDocumento) {
		instance.setTipoDocumento(tipoDocumento);
		return this;
	}

	public FacturaBuilder setPackFacturacion(String packFacturacion) {
		instance.setPackFacturacion(packFacturacion);
		return this;
	}
	
	
	public FacturaBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}
	
	public FacturaBuilder setSaldoPendiente(BigDecimal saldoPendiente) {
		instance.setSaldoPendiente(saldoPendiente);
		return this;
	}
	
	public FacturaBuilder setValidacionOper(boolean validacionOper) {
		instance.setValidacionOper(validacionOper);
		return this;
	}
	
	public FacturaBuilder setValidacionTeso(boolean validacionTeso) {
		instance.setValidacionTeso(validacionTeso);
		return this;
	}
	
}
