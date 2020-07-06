/**
 * 
 */
package com.business.unknow.services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ralfdemoledor
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PAGO_FACTURAS")
public class PagoFactura implements Serializable{
	
	private static final long serialVersionUID = 8969604798644430857L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
		
	@Column(name = "ID_CFDI")
	private Integer idCfdi;
	
	@Column(name = "FOLIO")
	private String folio;
	
	@Column(name = "MONTO")
	private BigDecimal monto;
	
	@Column(name = "TOTAL_FACTURA")
	private BigDecimal totalFactura;
	
	@Column(name = "ACREDOR")
	private String acredor;
	
	@Column(name = "DEUDOR")
	private String deudor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_PAGO", nullable = false)
	private Pago pago;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCfdi() {
		return idCfdi;
	}

	public void setIdCfdi(Integer idCfdi) {
		this.idCfdi = idCfdi;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(BigDecimal totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getAcredor() {
		return acredor;
	}

	public void setAcredor(String acredor) {
		this.acredor = acredor;
	}

	public String getDeudor() {
		return deudor;
	}

	public void setDeudor(String deudor) {
		this.deudor = deudor;
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

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	@Override
	public String toString() {
		return "PagoFactura [id=" + id + ", idCfdi=" + idCfdi + ", folio=" + folio + ", monto=" + monto
				+ ", totalFactura=" + totalFactura + ", acredor=" + acredor + ", deudor=" + deudor + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", pago=" + pago + "]";
	}

}
