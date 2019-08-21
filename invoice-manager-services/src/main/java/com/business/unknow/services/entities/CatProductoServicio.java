package com.business.unknow.services.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CatProductoServicio implements Serializable {

	private static final long serialVersionUID = -8825374962570225553L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto_servicio")
	private Integer id;

	@JoinColumn(name = "id_parent", referencedColumnName = "id_producto_servicio")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CatProductoServicio parent;

	@Basic(optional = false)
	@Column(name = "value")
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatProductoServicio getParent() {
		return parent;
	}

	public void setParent(CatProductoServicio parent) {
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CatProductoServicio [id=" + id + ", parent=" + parent + ", value=" + value + "]";
	}

}
