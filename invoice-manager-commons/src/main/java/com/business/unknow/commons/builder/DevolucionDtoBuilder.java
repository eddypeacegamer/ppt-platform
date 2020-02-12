package com.business.unknow.commons.builder;

import java.math.BigDecimal;

import com.business.unknow.model.dto.services.DevolucionDto;

public class DevolucionDtoBuilder extends AbstractBuilder<DevolucionDto> {

	public DevolucionDtoBuilder() {
		super(new DevolucionDto());
	}

	public DevolucionDtoBuilder setIdPagoOrigen(int idPagoOrigen) {
		instance.setIdPagoOrigen(idPagoOrigen);
		return this;
	}
	
	public DevolucionDtoBuilder setTipo(String tipo) {
		instance.setTipo(tipo);
		return this;
	}
	
	public DevolucionDtoBuilder setPagoMonto(BigDecimal pagoMonto) {
		instance.setPagoMonto(pagoMonto);
		return this;
	}
	
	public DevolucionDtoBuilder setImpuesto(BigDecimal impuesto) {
		instance.setImpuesto(impuesto);
		return this;
	}
	
	public DevolucionDtoBuilder setPorcentaje(BigDecimal porcentaje) {
		instance.setPorcentaje(porcentaje);
		return this;
	}
	
	public DevolucionDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}

	public DevolucionDtoBuilder setMonto(BigDecimal monto) {
		instance.setMonto(monto);
		return this;
	}

	public DevolucionDtoBuilder setReceptor(String receptor) {
		instance.setReceptor(receptor);
		return this;
	}

	public DevolucionDtoBuilder setTipoReceptor(String tipoReceptor) {
		instance.setTipoReceptor(tipoReceptor);
		return this;
	}

}
