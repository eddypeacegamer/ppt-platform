package com.business.unknow.services.services.builder;

import java.util.Date;

import com.business.unknow.commons.builder.AbstractBuilder;
import com.business.unknow.model.dto.services.CuentaBancariaDto;

public class CuentaBancariaDtoBuilder extends AbstractBuilder<CuentaBancariaDto> {

	public CuentaBancariaDtoBuilder() {
		super(new CuentaBancariaDto());
	}
	
	
	public CuentaBancariaDtoBuilder setTotal(int total) {
		instance.setTotal(total);
		return this;
	}
	public CuentaBancariaDtoBuilder setId(int id) {
		instance.setId(id);
		return this;
	}
	public CuentaBancariaDtoBuilder setBanco(String banco) {
		instance.setBanco(banco);
		return this;
	}


	public CuentaBancariaDtoBuilder setLinea(String linea) {
		instance.setLinea(linea);
		return this;
	}

	public CuentaBancariaDtoBuilder setGiro(String giro) {
		instance.setGiro(giro);
		return this;
	}

	public CuentaBancariaDtoBuilder setRazonSocial(String razonSocial) {
		instance.setRazonSocial(razonSocial);
		return this;
	}
	public CuentaBancariaDtoBuilder setEmpresa(String empresa) {
		instance.setEmpresa(empresa);
		return this;
	}
	public CuentaBancariaDtoBuilder setCuenta(String cuenta) {
		instance.setCuenta(cuenta);
		return this;
	}

	public CuentaBancariaDtoBuilder setClabe(String clabe) {
		instance.setClabe(clabe);
		return this;
	}
	public CuentaBancariaDtoBuilder setFechaActualizacion(Date fechaActualizacion) {
		instance.setFechaActualizacion(fechaActualizacion);
		return this;
	}
	public CuentaBancariaDtoBuilder setFechaCreacion(Date fechaCreacion) {
		instance.setFechaCreacion(fechaCreacion);
		return this;
	}
}
