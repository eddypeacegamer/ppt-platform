package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RETENCIONES")
public class Retencion implements Serializable {

	private static final long serialVersionUID = -2655293148503394319L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RETENCION")
	private Integer id;

	@Column(name = "BASE")
	private Double base;

	@Column(name = "IMPUESTO")
	private String impuesto;

	@Column(name = "TIPO_FACTOR")
	private String tipoFactor;

	@Column(name = "TASA_CUOTA")
	private String tasaOCuota;

	@Column(name = "IMPORTE")
	private Double importe;

	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO", nullable = false)
	private Concepto concepto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
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

	public String getTasaOCuota() {
		return tasaOCuota;
	}

	public void setTasaOCuota(String tasaOCuota) {
		this.tasaOCuota = tasaOCuota;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
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
		return "Retencion [id=" + id + ", base=" + base + ", impuesto=" + impuesto + ", tipoFactor=" + tipoFactor
				+ ", tasaOCuota=" + tasaOCuota + ", importe=" + importe + ", concepto=" + concepto + "]";
	}

}
