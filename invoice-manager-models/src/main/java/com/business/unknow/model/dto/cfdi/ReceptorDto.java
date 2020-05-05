package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;

public class ReceptorDto implements Serializable {

	private static final long serialVersionUID = 171758513601059506L;
	private String rfc;
	private String nombre;
	private String usoCfdi;
	private String direccion;

	public ReceptorDto() {
		super();
	}

	public ReceptorDto(String rfc, String nombre, String usoCfdi,String direccion) {
		super();
		this.rfc = rfc;
		this.nombre = nombre;
		this.usoCfdi = usoCfdi;
		this.direccion = direccion;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsoCfdi() {
		return usoCfdi;
	}

	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "ReceptorDto [rfc=" + rfc + ", nombre=" + nombre + ", usoCfdi=" + usoCfdi + "]";
	}

}
