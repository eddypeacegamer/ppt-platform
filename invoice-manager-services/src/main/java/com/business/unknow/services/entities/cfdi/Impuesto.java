package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMPUESTOS")
public class Impuesto implements Serializable {

	private static final long serialVersionUID = 5623944906795628517L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMPUESTO")
	private Integer id;

	@Column(name = "BASE")
	private BigDecimal base;

	@Column(name = "IMPUESTO")
	private String impuesto;

	@Column(name = "TIPO_FACTOR")
	private String tipoFactor;

	@Column(name = "TASA_CUOTA")
	private BigDecimal tasaOCuota;

	@Column(name = "IMPORTE")
	private BigDecimal importe;

	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO", nullable = false)
	private Concepto concepto;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getTipoFactor() {
		return tipoFactor;
	}

	public void setTipoFactor(String tipoFactor) {
		this.tipoFactor = tipoFactor;
	}

	public BigDecimal getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTasaOCuota(BigDecimal tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	@Override
	public String toString() {
		return "Impuesto [id=" + id + ", base=" + base + ", impuesto=" + impuesto + ", tipoFactor=" + tipoFactor
				+ ", tasaOCuota=" + tasaOCuota + ", importe=" + importe + ", concepto=" + concepto + "]";
	}

}
