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
@Table(name = "RESOURCE_FILES")
public class ResourceFile implements Serializable {
	

	private static final long serialVersionUID = -6395159214337997690L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "TIPO_ARCHIVO",nullable = false)
	private String tipoArchivo;
	
	@Column(name = "REFERENCIA",nullable = false)
	private String referencia;
	
	@Column(name = "TIPO_RECURSO",nullable = false)
	private String tipoRecurso;
	
	@Column(name = "DATA", unique = false, nullable = false,columnDefinition="longblob")
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

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
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
		return "ResourceFile [id=" + id + ", tipoArchivo=" + tipoArchivo + ", referencia=" + referencia
				+ ", tipoRecurso=" + tipoRecurso + ", fechaCreacion="
				+ fechaCreacion + "]";
	}

}
