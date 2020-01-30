package com.business.unknow.commons.builder;

import java.math.BigDecimal;

import com.business.unknow.model.dto.services.DevolucionDto;

public class DevolucionDtoBuilder extends AbstractBuilder<DevolucionDto>{

	public DevolucionDtoBuilder() {
		super(new DevolucionDto());
	}

	
	public DevolucionDtoBuilder setIdPagoOrigen(int idPagoOrigen) {
		instance.setIdPagoOrigen(idPagoOrigen);
		return this;
	}
	
	public DevolucionDtoBuilder setIdPagoDestino(int idPagoDestino) {
		instance.setIdPagoDestino(idPagoDestino);
		return this;
	}
	
	public DevolucionDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}
	
	public DevolucionDtoBuilder setStatusDevolucion(String statusDevolucion) {
		instance.setStatusDevolucion(statusDevolucion);
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
