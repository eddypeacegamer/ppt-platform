package com.business.unknow.commons.builder;

import java.util.List;

import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.FacturaDto;

public class FacturaContextBuilder extends AbstractBuilder<FacturaContext> {

	public FacturaContextBuilder() {
		super(new FacturaContext());
	}

	public FacturaContextBuilder setTipoFactura(String tipoFactura) {
		instance.setTipoFactura(tipoFactura);
		return this;
	}

	public FacturaContextBuilder setFacturaDto(FacturaDto factura) {
		instance.setFacturaDto(factura);
		return this;
	}

	public FacturaContextBuilder setComplementos(List<FacturaDto> complementos) {
		instance.setComplementos(complementos);
		return this;
	}

	public FacturaContextBuilder setComplementos(FacturaDto complemento) {
		instance.setComplementoActual(complemento);
		return this;
	}
	
	public FacturaContextBuilder setPagos(List<PagoDto> pagos) {
		instance.setPagos(pagos);
		return this;
	}
	
	public FacturaContextBuilder setComlpemento(FacturaDto comlpemento) {
		instance.setComlpemento(comlpemento);
		return this;
	}

}
