package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CFDI_RELACIONADO")
public class Relacionado implements Serializable {

	private static final long serialVersionUID = -3975983872415170489L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CFDI")
	private int id;

	@Column(name = "TIPO_RELACION")
	private String tipoRelacion;

	@Column(name = "RELACION")
	private String relacion;

	@OneToOne
	@JoinColumn(name = "ID_CFDI")
	private Cfdi cfdi;

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
		return "Relacionado [id=" + id + ", tipoRelacion=" + tipoRelacion + ", relacion=" + relacion + "]";
	}

}
