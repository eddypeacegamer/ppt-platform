package com.business.unknow.commons.builder;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.dto.FacturaPdfModelDto;

public class FacturaPdfModelDtoBuilder extends AbstractBuilder<FacturaPdfModelDto> {

	public FacturaPdfModelDtoBuilder() {
		super(new FacturaPdfModelDto());
	}

	public FacturaPdfModelDtoBuilder setCadenaOriginal(String cadenaOriginal) {
		instance.setCadenaOriginal(cadenaOriginal);
		return this;
	}

	public FacturaPdfModelDtoBuilder setFactura(Cfdi factura) {
		instance.setFactura(factura);
		return this;
	}

	public FacturaPdfModelDtoBuilder setLogotipo(String logotipo) {
		instance.setLogotipo(logotipo);
		return this;
	}

	public FacturaPdfModelDtoBuilder setQr(String qr) {
		instance.setQr(qr);
		return this;
	}

	public FacturaPdfModelDtoBuilder setTipoDeComprobanteDesc(String tipoDeComprobanteDesc) {
		instance.setTipoDeComprobanteDesc(tipoDeComprobanteDesc);
		return this;
	}

	public FacturaPdfModelDtoBuilder setUsoCfdiDesc(String usoCfdiDesc) {
		instance.setUsoCfdiDesc(usoCfdiDesc);
		return this;
	}

	public FacturaPdfModelDtoBuilder setFormaPagoDesc(String formaPagoDesc) {
		instance.setFormaPagoDesc(formaPagoDesc);
		return this;
	}

	public FacturaPdfModelDtoBuilder setMetodoPagoDesc(String metodoPagoDesc) {
		instance.setMetodoPagoDesc(metodoPagoDesc);
		return this;
	}

	public FacturaPdfModelDtoBuilder setRegimenFiscalDesc(String regimenFiscalDesc) {
		instance.setRegimenFiscalDesc(regimenFiscalDesc);
		return this;
	}

}
