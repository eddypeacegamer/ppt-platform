package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.business.unknow.services.entities.catalogs.StatusFactura;

@Entity
@Table(name = "FACTURAS")
public class Factura implements Serializable {

	private static final long serialVersionUID = 2854049815604653381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FACTURA")
	private Integer id;

	@JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Client cliente;

	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Empresa empresa;

	@Basic(optional = false)
	@Column(name = "FOLIO")
	private String folio;

	@Basic(optional = false)
	@Column(name = "UUID")
	private String uuid;

	@Basic(optional = false)
	@Column(name = "GIRO")
	private String giro;

	@Basic(optional = false)
	@Column(name = "USO_CDFI")
	private String usoCdfi;

	@Basic(optional = false)
	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@JoinColumn(name = "ID_STATUS_FACTURA", referencedColumnName = "ID_STATUS_FACTURA")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private StatusFactura statusFactura;

	@Basic(optional = false)
	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Basic(optional = false)
	@Column(name = "MONEDA")
	private String moneda;

	@Basic(optional = false)
	@Column(name = "FORMA_PAGO")
	private String formaPago;

	@Basic(optional = false)
	@Column(name = "METODO_PAGO")
	private String metodoPago;

	@Basic(optional = false)
	@Column(name = "NOTAS")
	private String notas;

	@Basic(optional = false)
	@Column(name = "CDFI_FLAG")
	private Boolean cdfiFlag;

	@Basic(optional = false)
	@Column(name = "PAGO_TOTAL")
	private double pagoTotal;

	@Basic(optional = false)
	@Column(name = "SUB_TOTAL")
	private double subtotal;

	@Basic(optional = false)
	@Column(name = "IVA")
	private double iva;

	@Basic(optional = false)
	@Column(name = "TOTAL")
	private double total;

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

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getUsoCdfi() {
		return usoCdfi;
	}

	public void setUsoCdfi(String usoCdfi) {
		this.usoCdfi = usoCdfi;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public StatusFactura getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(StatusFactura statusFactura) {
		this.statusFactura = statusFactura;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	public Boolean getCdfiFlag() {
		return cdfiFlag;
	}

	public void setCdfiFlag(Boolean cdfiFlag) {
		this.cdfiFlag = cdfiFlag;
	}

	public double getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(double pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", cliente=" + cliente + ", empresa=" + empresa + ", folio=" + folio + ", uuid="
				+ uuid + ", giro=" + giro + ", usoCdfi=" + usoCdfi + ", regimenFiscal=" + regimenFiscal
				+ ", statusFactura=" + statusFactura + ", tipoDocumento=" + tipoDocumento + ", moneda=" + moneda
				+ ", formaPago=" + formaPago + ", metodoPago=" + metodoPago + ", notas=" + notas + ", cdfiFlag="
				+ cdfiFlag + ", pagoTotal=" + pagoTotal + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
