package com.business.unknow.commons.builder;

import com.business.unknow.model.DevolucionDto;

public class DevolucionDtoBuilder extends AbstractBuilder<DevolucionDto>{

	public DevolucionDtoBuilder() {
		super(new DevolucionDto());
	}

	
	public DevolucionDtoBuilder setIdPago(int idPago) {
		instance.setIdPago(idPago);
		return this;
	}
	
	public DevolucionDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}
	
	public DevolucionDtoBuilder setStatusPago(String statusPago) {
		instance.setStatusPago(statusPago);
		return this;
	}
	
	public DevolucionDtoBuilder setMonto(Double monto) {
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
