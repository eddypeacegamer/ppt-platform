package com.business.unknow.model.dto.cfdi;

import java.io.Serializable;

public class RelacionadoDto implements Serializable {

	private static final long serialVersionUID = 6588638217033669199L;
	private int id;
	private String tipoRelacion;
	private String relacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	@Override
	public String toString() {
		return "RelacionadoDto [id=" + id + ", tipoRelacion=" + tipoRelacion + ", relacion=" + relacion + "]";
	}

}
