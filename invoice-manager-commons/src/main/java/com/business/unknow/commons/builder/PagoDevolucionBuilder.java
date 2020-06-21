package com.business.unknow.commons.builder;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.model.dto.pagos.PagoDevolucionDto;

public class PagoDevolucionBuilder extends AbstractBuilder<PagoDevolucionDto> {

	public PagoDevolucionBuilder(PagoDevolucionDto instance) {
		super(instance);
	}

	public PagoDevolucionBuilder() {
		super(new PagoDevolucionDto());
	}

	public PagoDevolucionBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}

	public PagoDevolucionBuilder setTipoCambio(BigDecimal tipoCambio) {
		instance.setTipoCambio(tipoCambio);
		return this;
	}

	public PagoDevolucionBuilder setMonto(BigDecimal monto) {
		instance.setMonto(monto);
		return this;
	}

	public PagoDevolucionBuilder setBeneficiario(String beneficiario) {
		instance.setBeneficiario(beneficiario);
		return this;
	}

	public PagoDevolucionBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}

	public PagoDevolucionBuilder setBanco(String banco) {
		instance.setBanco(banco);
		return this;
	}

	public PagoDevolucionBuilder setTipoReferencia(String tipoReferencia) {
		instance.setTipoReferencia(tipoReferencia);
		return this;
	}

	public PagoDevolucionBuilder setFechaPago(Date fechaPago) {
		instance.setFechaPago(fechaPago);
		return this;
	}

	public PagoDevolucionBuilder setStatus(String status) {
		instance.setStatus(status);
		return this;
	}

	public PagoDevolucionBuilder setTipoReceptor(String tipoReceptor) {
		instance.setTipoReceptor(tipoReceptor);
		return this;
	}

	public PagoDevolucionBuilder setIdDevolucion(Integer idDevolucion) {
		instance.setIdDevolucion(idDevolucion);
		return this;
	}

	public PagoDevolucionBuilder setCuentaPago(String cuentaPago) {
		instance.setCuentaPago(cuentaPago);
		return this;
	}

	public PagoDevolucionBuilder setRfcEmpresa(String rfcEmpresa) {
		instance.setRfcEmpresa(rfcEmpresa);
		return this;
	}

	public PagoDevolucionBuilder setAutorizador(String autorizador) {
		instance.setAutorizador(autorizador);
		return this;
	}

	public PagoDevolucionBuilder setSolicitante(String solicitante) {
		instance.setSolicitante(solicitante);
		return this;
	}

}
