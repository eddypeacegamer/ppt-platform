package com.business.unknow.enums;

public enum FacturaStatusEnum {
	
	VALIDACION_TESORERIA(1,"Validacion tesoreria"),
	VALIDACION_OPERACIONES(2,"Validacion operaciones"),
	POR_TIMBRAR(3,"Por Timbrar"),
	TIMBRADA(4,"Timbrada"),
	RECHAZO_TESORERIA(5,"Rechazo tesoreria"),
	RECHAZO_OPERACIONES(6,"Rechazo Operaciones"),
	CANCELADA(7,"Cancelada");
	

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
