package com.business.unknow.model.catalogs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodigoPostalUiDto implements Serializable {

	private static final long serialVersionUID = 2379130470017878430L;

	private String codigo_postal;
	private String municipio;
	private String estado;
	private List<String> colonias = new ArrayList<>();

	public CodigoPostalUiDto(String codigo_postal, String municipio, String estado) {
		super();
		this.codigo_postal = codigo_postal;
		this.municipio = municipio;
		this.estado = estado;
	}

	public CodigoPostalUiDto() {
		super();
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<String> getColonias() {
		return colonias;
	}

	public void setColonias(List<String> colonias) {
		this.colonias = colonias;
	}

	@Override
	public String toString() {
		return "CodigoPostalUiDto [codigo_postal=" + codigo_postal + ", municipio=" + municipio + ", estado=" + estado
				+ ", colonias=" + colonias + "]";
	}

}
