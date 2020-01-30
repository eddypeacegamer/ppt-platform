package com.business.unknow.services.util;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.commons.builder.AbstractBuilder;
import com.business.unknow.model.dto.services.PagoDto;

public class PagoBuilder extends AbstractBuilder<PagoDto> {

	public PagoBuilder() {
		super(new PagoDto());
	}

	public PagoBuilder setBanco(String banco) {
		instance.setBanco(banco);
		return this;
	}

	public PagoBuilder setCreateUser(String createUser) {
		instance.setCreateUser(createUser);
		return this;
	}

	public PagoBuilder setComentarioPago(String comentario) {
		instance.setComentarioPago(comentario);
		return this;
	}

	public PagoBuilder setFechaPago(Date fecha) {
		instance.setFechaPago(fecha);
		return this;
	}

	public PagoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}

	public PagoBuilder setFolioPadre(String folioPadre) {
		instance.setFolioPadre(folioPadre);
		return this;
	}

	public PagoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}

	public PagoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}

	public PagoBuilder setMonto(BigDecimal monto) {
		instance.setMonto(monto);
		return this;
	}

	public PagoBuilder setRevision1(Boolean revision) {
		instance.setRevision1(revision);
		return this;
	}

	public PagoBuilder setRevision2(Boolean revision) {
		instance.setRevision1(revision);
		return this;
	}

	public PagoBuilder setTipoDeCambio(BigDecimal tipoCambio) {
		instance.setTipoDeCambio(tipoCambio);
		return this;
	}

	public PagoBuilder setStatusPago(String statusPago) {
		instance.setStatusPago(statusPago);
		return this;
	}

	public PagoBuilder setTipoPago(String tipoPago) {
		instance.setTipoPago(tipoPago);
		return this;
	}

}
