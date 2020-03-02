
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PAGO_DEVOLUCION")
public class PagoDevolucion implements Serializable {

	private static final long serialVersionUID = -1572795797336952518L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGO_DEVOLUCION")
	private Integer id;

	@NotNull
	@Column(name = "MONEDA")
	private String moneda;

	@NotNull
	@Column(name = "TIPO_CAMBIO")
	private BigDecimal tipoCambio;

	@NotNull
	@Column(name = "MONTO")
	private BigDecimal monto;

	@NotNull
	@Column(name = "BENEFICIARIO")
	private String beneficiario;

	@NotNull
	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Column(name = "BANCO")
	private String banco;

	@Column(name = "TIPO_REFERENCIA")
	private String tipoReferencia;
	
	@Column(name = "REFERENCIA")
	private String referencia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;

	@NotNull
	@Column(name = "STATUS")
	private String status;

	@NotNull
	@Column(name = "TIPO_RECEPTOR")
	private String tipoReceptor;
	
	@NotNull
	@Column(name = "RECEPTOR")
	private String receptor;

	@NotNull
	@Column(name = "SOLICITANTE")
	private String solicitante;

	@Column(name = "CUENTA_PAGO")
	private String cuentaPago;

	@Column(name = "RFC_EMPRESA")
	private String rfcEmpresa;

	@Column(name = "AUTORIZADOR")
	private String autorizador;
	
	@Column(name = "COMENTARIOS")
	private String comentarios;
	
	@Column(name = "ID_DEVOLUCION")
	private Integer idDevolucion;
	
	@Column(name = "FOLIO_FACT")
	private String folioFactura;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getTipoReferencia() {
		return tipoReferencia;
	}

	public void setTipoReferencia(String tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoReceptor() {
		return tipoReceptor;
	}

	public void setTipoReceptor(String tipoReceptor) {
		this.tipoReceptor = tipoReceptor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getCuentaPago() {
		return cuentaPago;
	}

	public void setCuentaPago(String cuentaPago) {
		this.cuentaPago = cuentaPago;
	}

	public String getRfcEmpresa() {
		return rfcEmpresa;
	}

	public void setRfcEmpresa(String rfcEmpresa) {
		this.rfcEmpresa = rfcEmpresa;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}
	
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getIdDevolucion() {
		return idDevolucion;
	}

	public void setIdDevolucion(Integer idDevolucion) {
		this.idDevolucion = idDevolucion;
	}
	
	public String getFolioFactura() {
		return folioFactura;
	}

	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
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
		return "PagoDevolucion [id=" + id + ", moneda=" + moneda + ", tipoCambio=" + tipoCambio + ", monto=" + monto
				+ ", beneficiario=" + beneficiario + ", formaPago=" + formaPago + ", banco=" + banco
				+ ", tipoReferencia=" + tipoReferencia + ", referencia=" + referencia + ", fechaPago=" + fechaPago
				+ ", status=" + status + ", tipoReceptor=" + tipoReceptor + ", receptor=" + receptor + ", solicitante="
				+ solicitante + ", cuentaPagop=" + cuentaPago + ", rfcEmpresa=" + rfcEmpresa + ", autorizador="
				+ autorizador + ", idDevolucion=" + idDevolucion + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}
}
