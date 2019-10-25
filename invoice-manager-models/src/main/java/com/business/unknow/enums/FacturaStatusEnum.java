package com.business.unknow.enums;

public enum FacturaStatusEnum {
	
	VALIDACION_OPERACIONES("Validacion operaciones"),
	VALIDACION_TESORERIA("Validacion tesoreria"),
	TIMBRADA("Timbrada"),
	RECHAZO_TESORERIA("Rechazo tesoreria"),
	RECHAZO_OPERACIONES("Rechazo Operaciones"),
	CANCELADA("Cancelada");

	private String valor;
	
	private FacturaStatusEnum(String valor) {
		this.valor=valor;
	}

	public String getValor() {
		return valor;
	}

}
