package com.business.unknow.commons.builder;

import java.math.BigDecimal;
import java.util.List;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.EmisorDto;
import com.business.unknow.model.dto.cfdi.ReceptorDto;

public class CfdiDtoBuilder extends AbstractBuilder<CfdiDto> {

	public CfdiDtoBuilder() {
		super(new CfdiDto());
	}

	public CfdiDtoBuilder setVersion(String version) {
		instance.setVersion(version);
		return this;
	}

	public CfdiDtoBuilder setSerie(String serie) {
		instance.setSerie(serie);
		return this;
	}

	public CfdiDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}

	public CfdiDtoBuilder setSello(String sello) {
		instance.setSello(sello);
		return this;
	}

	public CfdiDtoBuilder setNoCertificado(String noCertificado) {
		instance.setNoCertificado(noCertificado);
		return this;
	}

	public CfdiDtoBuilder setCertificado(String certificado) {
		instance.setCertificado(certificado);
		return this;
	}

	public CfdiDtoBuilder setTotal(BigDecimal total) {
		instance.setTotal(total);
		return this;
	}

	public CfdiDtoBuilder setSubtotal(BigDecimal subtotal) {
		instance.setSubtotal(subtotal);
		return this;
	}

	public CfdiDtoBuilder setDescuento(BigDecimal descuento) {
		instance.setDescuento(descuento);
		return this;
	}

	public CfdiDtoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}

	public CfdiDtoBuilder setTipoDeComprobante(String tipoDeComprobante) {
		instance.setTipoDeComprobante(tipoDeComprobante);
		return this;
	}

	public CfdiDtoBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}

	public CfdiDtoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}

	public CfdiDtoBuilder setCondicionesDePago(String condicionesDePago) {
		instance.setCondicionesDePago(condicionesDePago);
		return this;
	}

	public CfdiDtoBuilder setLugarExpedicion(String lugarExpedicion) {
		instance.setLugarExpedicion(lugarExpedicion);
		return this;
	}

	public CfdiDtoBuilder setConceptos(List<ConceptoDto> conceptos) {
		instance.setConceptos(conceptos);
		return this;
	}

	public CfdiDtoBuilder setComplemento(ComplementoDto complemento) {
		instance.setComplemento(complemento);
		return this;
	}

	public CfdiDtoBuilder setEmisor(EmisorDto emisor) {
		instance.setEmisor(emisor);
		return this;
	}

	public CfdiDtoBuilder setReceptor(ReceptorDto receptor) {
		instance.setReceptor(receptor);
		return this;
	}
	
	public CfdiDtoBuilder setPagos(List<CfdiPagoDto> pagos) {
		if(instance.getComplemento()!=null) {
			instance.setComplemento(new ComplementoDto());
		}
		instance.getComplemento().setPagos(pagos);
		return this;
	}
	
}
