/**
 * 
 */
package com.business.unknow.services.entities.files;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ralfdemoledor
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "FACTURA_FILES")
public class FacturaFile implements Serializable {


	private static final long serialVersionUID = 3112040250389476285L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "TIPO_ARCHIVO", unique = false, nullable = false)
	private String tipoArchivo;
	
	@Column(name = "FOLIO", unique = false, nullable = false)
	private String folio;
	
	@Column(name = "DATA", unique = false, nullable = false)
	private byte[] data;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "FacturaFile [id=" + id + ", tipoArchivo=" + tipoArchivo + ", folio=" + folio  + ", fechaCreacion=" + fechaCreacion + "]";
	}

}
