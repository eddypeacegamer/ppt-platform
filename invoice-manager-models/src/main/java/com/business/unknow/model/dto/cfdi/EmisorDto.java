package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;

public class EmisorDto implements Serializable {

	private static final long serialVersionUID = -6859374411200231323L;

	private Integer id;
	private String rfc;
	private String nombre;
	private String regimenFiscal;
	private String direccion;

	public EmisorDto() {
		super();
	}

	public EmisorDto(String rfc, String nombre, String regimenFiscal,String direccion) {
		super();
		this.rfc = rfc;
		this.nombre = nombre;
		this.regimenFiscal = regimenFiscal;
		this.direccion = direccion;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Emisor [rfc=" + rfc + ", nombre=" + nombre + ", regimenFiscal=" + regimenFiscal + "]";
	}

}
