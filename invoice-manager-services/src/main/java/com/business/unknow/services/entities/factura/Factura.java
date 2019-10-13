package com.business.unknow.services.entities.factura;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

import com.business.unknow.services.entities.catalogs.StatusFactura;

@Entity
@Table(name = "FACTURAS")
public class Factura implements Serializable {

	private static final long serialVersionUID = 2854049815604653381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FACTURA")
	private Integer id;

	@Column(name = "RFC_EMISOR")
	private String rfcEmisor;

	@Column(name = "RFC_REMITENTE")
	private String rfcRemitente;

	@Column(name = "RAZON_SOCIAL_EMISOR")
	private String razonSocialEmisor;

	@Column(name = "RAZON_SOCIAL_REMITENTE")
	private String razonSocialRemitente;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "FOLIO_PADRE")
	private String folioPadre;

	@Column(name = "UUID")
	private String uuid;

	@JoinColumn(name = "ID_STATUS_FACTURA", referencedColumnName = "ID_STATUS_FACTURA") // TODO remove this ID use
																						// ID_FACTURA
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private StatusFactura statusFactura;

	@Column(name = "STATUS_DETAIL")
	private String statusDetail;

	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Column(name = "NOTAS")
	private String notas;

	@Column(name = "TOTAL")
	private Double total;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_TIMBRADO")
	private Date fechaTimbrado;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getRfcRemitente() {
		return rfcRemitente;
	}

	public void setRfcRemitente(String rfcRemitente) {
		this.rfcRemitente = rfcRemitente;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFolioPadre() {
		return folioPadre;
	}

	public void setFolioPadre(String folioPadre) {
		this.folioPadre = folioPadre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public StatusFactura getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(StatusFactura statusFactura) {
		this.statusFactura = statusFactura;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}

	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
	}

	public String getRazonSocialRemitente() {
		return razonSocialRemitente;
	}

	public void setRazonSocialRemitente(String razonSocialRemitente) {
		this.razonSocialRemitente = razonSocialRemitente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", rfcEmisor=" + rfcEmisor + ", rfcRemitente=" + rfcRemitente
				+ ", razonSocialEmisor=" + razonSocialEmisor + ", razonSocialRemitente=" + razonSocialRemitente
				+ ", folio=" + folio + ", folioPadre=" + folioPadre + ", uuid=" + uuid + ", statusFactura="
				+ statusFactura + ", statusDetail=" + statusDetail + ", tipoDocumento=" + tipoDocumento + ", formaPago="
				+ formaPago + ", metodoPago=" + metodoPago + ", notas=" + notas + ", total=" + total
				+ ", fechaActualizacion=" + fechaActualizacion + ", fechaTimbrado=" + fechaTimbrado + "]";
	}

}