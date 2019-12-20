package com.business.unknow.model.factura.cfdi.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptoDto implements Serializable {

	private static final long serialVersionUID = 1690079459401358817L;
	private Integer id;
	private String claveProdServ;
	private String noIdentificacion;
	private Integer cantidad;
	private String claveUnidad;
	private String unidad;
	private String descripcion;
	private Double valorUnitario;
	private Double importe;
	private Double descuento;
	private List<ImpuestoDto> impuestos = new ArrayList<ImpuestoDto>();;
	private List<RetencionDto> retenciones = new ArrayList<RetencionDto>();;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
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

	public List<ImpuestoDto> getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(List<ImpuestoDto> impuestos) {
		this.impuestos = impuestos;
	}

	public List<RetencionDto> getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(List<RetencionDto> retenciones) {
		this.retenciones = retenciones;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((claveProdServ == null) ? 0 : claveProdServ.hashCode());
		result = prime * result + ((claveUnidad == null) ? 0 : claveUnidad.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((impuestos == null) ? 0 : impuestos.hashCode());
		result = prime * result + ((unidad == null) ? 0 : unidad.hashCode());
		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptoDto other = (ConceptoDto) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (claveProdServ == null) {
			if (other.claveProdServ != null)
				return false;
		} else if (!claveProdServ.equals(other.claveProdServ))
			return false;
		if (claveUnidad == null) {
			if (other.claveUnidad != null)
				return false;
		} else if (!claveUnidad.equals(other.claveUnidad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (impuestos == null) {
			if (other.impuestos != null)
				return false;
		} else if (!impuestos.equals(other.impuestos))
			return false;
		if (unidad == null) {
			if (other.unidad != null)
				return false;
		} else if (!unidad.equals(other.unidad))
			return false;
		if (valorUnitario == null) {
			if (other.valorUnitario != null)
				return false;
		} else if (!valorUnitario.equals(other.valorUnitario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConceptoDto [id=" + id + ", claveProdServ=" + claveProdServ + ", noIdentificacion=" + noIdentificacion
				+ ", cantidad=" + cantidad + ", claveUnidad=" + claveUnidad + ", unidad=" + unidad + ", descripcion="
				+ descripcion + ", valorUnitario=" + valorUnitario + ", importe=" + importe + ", descuento=" + descuento
				+ ", impuestos=" + impuestos + ", retenciones=" + retenciones + "]";
	}

}
