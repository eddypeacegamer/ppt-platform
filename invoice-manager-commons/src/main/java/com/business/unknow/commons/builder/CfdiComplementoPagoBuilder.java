package com.business.unknow.commons.builder;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.model.dto.cfdi.CfdiPagoDto;

public class CfdiComplementoPagoBuilder extends AbstractBuilder<CfdiPagoDto> {

	public CfdiComplementoPagoBuilder() {
		super(new CfdiPagoDto());
	}

	public CfdiComplementoPagoBuilder setVersion(String version) {
		instance.setVersion(version);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setFechaPago(Date fechaPago) {
		instance.setFechaPago(fechaPago);
		return this;
	}

	public CfdiComplementoPagoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setMonto(BigDecimal monto) {
		instance.setMonto(monto);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setIdDocumento(String idDocumento) {
		instance.setIdDocumento(idDocumento);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setImportePagado(BigDecimal importePagado) {
		instance.setImportePagado(importePagado);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setImporteSaldoAnterior(BigDecimal importeSaldoAnterior) {
		instance.setImporteSaldoAnterior(importeSaldoAnterior);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setImporteSaldoInsoluto(BigDecimal importeSaldoInsoluto) {
		instance.setImporteSaldoInsoluto(importeSaldoInsoluto);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setValido(Boolean valido) {
		instance.setValido(valido);
		return this;
	}
	
	
	public CfdiComplementoPagoBuilder setMonedaDr(String monedaDr) {
		instance.setMonedaDr(monedaDr);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setNumeroParcialidad(int numeroParcialidad) {
		instance.setNumeroParcialidad(numeroParcialidad);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setSerie(String serie) {
		instance.setSerie(serie);
		return this;
	}

	public CfdiComplementoPagoBuilder setTipoCambio(BigDecimal tipoCambio) {
		instance.setTipoCambio(tipoCambio);
		return this;
	}
	
	public CfdiComplementoPagoBuilder setTipoCambioDr(BigDecimal tipoCambioDr) {
		instance.setTipoCambioDr(tipoCambioDr);
		return this;
	}
	

}
