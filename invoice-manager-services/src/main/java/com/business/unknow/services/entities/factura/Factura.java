
package com.business.unknow.services.entities.factura;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

	@Column(name = "LINEA_EMISOR")
	private String lineaEmisor;

	@Column(name = "RAZON_SOCIAL_REMITENTE")
	private String razonSocialRemitente;

	@Column(name = "LINEA_REMITENTE")
	private String lineaRemitente;

	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Column(name = "SOLICITANTE")
	private String solicitante;

	@Column(name = "FOLIO")
	private String folio;

	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Column(name = "STATUS_FACTURA")
	private Integer statusFactura;

	@Column(name = "STATUS_DETAIL")
	private String statusDetail;

	@Column(name = "UUID")
	private String uuid;

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Column(name = "SALDO_PENDIENTE")
	private BigDecimal saldoPendiente;

	@Column(name = "PACK_FACTURACION")
	private String packFacturacion;

	@Column(name = "NOTAS")
	private String notas;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Column(name = "FECHA_CANCELADO")
	private Date fechaCancelacion;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "CADENA_ORIGINAL_TIMBRADO")
	private String cadenaOriginalTimbrado;

	@Column(name = "SELLO_CFD")
	private String selloCfd;

	@Column(name = "ID_CFDI")
	private Integer idCfdi;

	@Column(name = "VALIDACION_TESO", columnDefinition = "TINYINT")
	private Boolean validacionTeso;

	@Column(name = "VALIDACION_OPER", columnDefinition = "TINYINT")
	private Boolean validacionOper;

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

	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}

	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
	}

	public String getLineaEmisor() {
		return lineaEmisor;
	}

	public void setLineaEmisor(String lineaEmisor) {
		this.lineaEmisor = lineaEmisor;
	}

	public String getRazonSocialRemitente() {
		return razonSocialRemitente;
	}

	public void setRazonSocialRemitente(String razonSocialRemitente) {
		this.razonSocialRemitente = razonSocialRemitente;
	}

	public String getLineaRemitente() {
		return lineaRemitente;
	}

	public void setLineaRemitente(String lineaRemitente) {
		this.lineaRemitente = lineaRemitente;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Integer getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(Integer statusFactura) {
		this.statusFactura = statusFactura;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSaldoPendiente() {
		return saldoPendiente;
	}

	public void setSaldoPendiente(BigDecimal saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}

	public String getPackFacturacion() {
		return packFacturacion;
	}

	public void setPackFacturacion(String packFacturacion) {
		this.packFacturacion = packFacturacion;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCadenaOriginalTimbrado() {
		return cadenaOriginalTimbrado;
	}

	public void setCadenaOriginalTimbrado(String cadenaOriginalTimbrado) {
		this.cadenaOriginalTimbrado = cadenaOriginalTimbrado;
	}

	public String getSelloCfd() {
		return selloCfd;
	}

	public void setSelloCfd(String selloCfd) {
		this.selloCfd = selloCfd;
	}

	public Integer getIdCfdi() {
		return idCfdi;
	}

	public void setIdCfdi(Integer idCfdi) {
		this.idCfdi = idCfdi;
	}

	public Boolean getValidacionTeso() {
		return validacionTeso;
	}

	public void setValidacionTeso(Boolean validacionTeso) {
		this.validacionTeso = validacionTeso;
	}

	public Boolean getValidacionOper() {
		return validacionOper;
	}

	public void setValidacionOper(Boolean validacionOper) {
		this.validacionOper = validacionOper;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", rfcEmisor=" + rfcEmisor + ", rfcRemitente=" + rfcRemitente
				+ ", razonSocialEmisor=" + razonSocialEmisor + ", lineaEmisor=" + lineaEmisor
				+ ", razonSocialRemitente=" + razonSocialRemitente + ", lineaRemitente=" + lineaRemitente
				+ ", tipoDocumento=" + tipoDocumento + ", solicitante=" + solicitante + ", folio=" + folio
				+ ", metodoPago=" + metodoPago + ", statusFactura=" + statusFactura + ", statusDetail=" + statusDetail
				+ ", uuid=" + uuid + ", total=" + total + ", saldoPendiente=" + saldoPendiente + ", packFacturacion="
				+ packFacturacion + ", notas=" + notas + ", fechaActualizacion=" + fechaActualizacion
				+ ", fechaCancelacion=" + fechaCancelacion + ", fechaCreacion=" + fechaCreacion
				+ ", cadenaOriginalTimbrado=" + cadenaOriginalTimbrado + ", selloCfd=" + selloCfd + ", idCfdi=" + idCfdi
				+ ", validacionTeso=" + validacionTeso + ", validacionOper=" + validacionOper + "]";
	}

}