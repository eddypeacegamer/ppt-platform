package com.business.unknow.services.entities.cfdi;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONCEPTOS")
public class Concepto implements Serializable {

	private static final long serialVersionUID = -1917092984790590992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONCEPTO")
	private Integer id;

	@Column(name = "CLAVE_PROD_SERV")
	private String claveProdServ;

	@Column(name = "NO_IDENTIFICACION")
	private String noIdentificacion;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

	@Column(name = "CLAVE_UNIDAD")
	private String claveUnidad;

	@Column(name = "UNIDAD")
	private String unidad;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "VALOR_UNITARIO")
	private Double valorUnitario;

	@Column(name = "IMPORTE")
	private Double importe;

	@Column(name = "DESCUENTO")
	private Double descuento;

	@ManyToOne
	@JoinColumn(name = "ID_CFDI", nullable = false)
	private Cfdi cfdi;

	@OneToMany(mappedBy = "concepto")
	private List<Impuesto> impuestos;

	@OneToMany(mappedBy = "concepto")
	private List<Retencion> retenciones;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoIdentificacion() {
		return noIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getClaveUnidad() {
		return claveUnidad;
	}

	public void setClaveUnidad(String claveUnidad) {
		this.claveUnidad = claveUnidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
	}

	public Cfdi getCfdi() {
		return cfdi;
	}

	public void setCfdi(Cfdi cfdi) {
		this.cfdi = cfdi;
	}

	public List<Impuesto> getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(List<Impuesto> impuestos) {
		this.impuestos = impuestos;
	}

	public List<Retencion> getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(List<Retencion> retenciones) {
		this.retenciones = retenciones;
	}

	@Override
	public String toString() {
		return "Concepto [id=" + id + ", claveProdServ=" + claveProdServ + ", noIdentificacion=" + noIdentificacion
				+ ", cantidad=" + cantidad + ", claveUnidad=" + claveUnidad + ", unidad=" + unidad + ", descripcion="
				+ descripcion + ", valorUnitario=" + valorUnitario + ", importe=" + importe + ", descuento=" + descuento
				+ ", cfdi=" + cfdi + ", impuestos=" + impuestos + ", retenciones=" + retenciones + "]";
	}

}
