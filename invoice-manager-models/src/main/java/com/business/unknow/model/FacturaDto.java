package com.business.unknow.model;

import java.util.Date;

import com.business.unknow.model.catalogs.StatusFacturaDto;

public class FacturaDto {

	private Integer id;
	private ClientDto cliente;
	private EmpresaDto empresa;
	private String folio;
	private String uuid;
	private String giro;
	private String usoCdfi;
	private String regimenFiscal;
	private StatusFacturaDto statusFactura;
	private String tipoDocumento;
	private String moneda;
	private String formaPago;
	private String metodoPago;
	private String notas;
	private Boolean cdfiFlag;
	private double pagoTotal;
	private double subtotal;
	private double iva;
	private double total;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClientDto getCliente() {
		return cliente;
	}

	public void setCliente(ClientDto cliente) {
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

	public StatusFacturaDto getStatusFactura() {
		return statusFactura;
	}

	public void setStatusFactura(StatusFacturaDto statusFactura) {
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

	public EmpresaDto getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDto empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "FacturaDto [id=" + id + ", cliente=" + cliente + ", empresa=" + empresa + ", folio=" + folio + ", uuid="
				+ uuid + ", giro=" + giro + ", usoCdfi=" + usoCdfi + ", regimenFiscal=" + regimenFiscal
				+ ", statusFactura=" + statusFactura + ", tipoDocumento=" + tipoDocumento + ", moneda=" + moneda
				+ ", formaPago=" + formaPago + ", metodoPago=" + metodoPago + ", notas=" + notas + ", cdfiFlag="
				+ cdfiFlag + ", pagoTotal=" + pagoTotal + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total
				+ ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
