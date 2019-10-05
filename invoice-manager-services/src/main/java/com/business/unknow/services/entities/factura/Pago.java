package com.business.unknow.services.entities.factura;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "PAGOS")
public class Pago implements Serializable {

	private static final long serialVersionUID = 8371622895161409889L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGO")
	private Integer id;
	
	@Column(name = "FOLIO")
	private String folio;
	
	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;
	
	@Column(name = "DOCUMENTO")
	private String documento;
	
	@Column(name = "CANTIDAD")
	private Double candtidad;
	
	@Column(name = "TIPO_PAGO")
	private String tipoPago;
	
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Double getCandtidad() {
		return candtidad;
	}

	public void setCandtidad(Double candtidad) {
		this.candtidad = candtidad;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		return "Pagos [id=" + id + ", folio=" + folio + ", tipoDocumento=" + tipoDocumento + ", documento=" + documento
				+ ", candtidad=" + candtidad + ", tipoPago=" + tipoPago + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}
	
}
