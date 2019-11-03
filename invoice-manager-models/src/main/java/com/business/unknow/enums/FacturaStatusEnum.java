package com.business.unknow.enums;

public enum FacturaStatusEnum {
	
	VALIDACION_OPERACIONES(1,"Validacion operaciones"),
	VALIDACION_TESORERIA(2,"Validacion tesoreria"),
	TIMBRADA(3,"Timbrada"),
	RECHAZO_TESORERIA(4,"Rechazo tesoreria"),
	RECHAZO_OPERACIONES(5,"Rechazo Operaciones"),
	CANCELADA(6,"Cancelada");

	private Integer valor;
	private String descripcion;
	
	private FacturaStatusEnum(Integer valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}


	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
