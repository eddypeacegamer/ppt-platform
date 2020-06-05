/**
 * 
 */
package com.business.unknow.commons.builder;

import java.math.BigDecimal;
import java.util.Date;
import com.business.unknow.model.dto.PagoReportDto;

/**
 * @author hha0009
 *
 */
public class PagoReportDtoBuilder extends AbstractBuilder<PagoReportDto> {

	public PagoReportDtoBuilder() {
		super(new PagoReportDto());
	}
	
	
	public PagoReportDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}
	
	public PagoReportDtoBuilder setFolioFiscal(String folioFiscal) {
		instance.setFolioFiscal(folioFiscal);
		return this;
	}
	
	public PagoReportDtoBuilder setFechaEmision(Date fechaEmision) {
		instance.setFechaEmision(fechaEmision);
		return this;
	}
	
	public PagoReportDtoBuilder setRfcEmisor(String rfcEmisor) {
		instance.setRfcEmisor(rfcEmisor);
		return this;
	}
	
	public PagoReportDtoBuilder setEmisor(String emisor) {
		instance.setEmisor(emisor);
		return this;
	}
	
	public PagoReportDtoBuilder setRfcReceptor(String rfcReceptor) {
		instance.setRfcReceptor(rfcReceptor);
		return this;
	}
	
	public PagoReportDtoBuilder setReceptor(String receptor) {
		instance.setReceptor(receptor);
		return this;
	}
	
	public PagoReportDtoBuilder setTipoDocumento(String tipoDocumento) {
		instance.setTipoDocumento(tipoDocumento);
		return this;
	}
	
	public PagoReportDtoBuilder setPackFacturacion(String packFacturacion) {
		instance.setPackFacturacion(packFacturacion);
		return this;
	}
	
	public PagoReportDtoBuilder setTipoComprobante(String tipoComprobante) {
		instance.setTipoComprobante(tipoComprobante);
		return this;
	}
	
	public PagoReportDtoBuilder setImpuestosTrasladados(BigDecimal impuestosTrasladados) {
		instance.setImpuestosTrasladados(impuestosTrasladados);
		return this;
	}
	
	public PagoReportDtoBuilder setImpuestosRetenidos(BigDecimal impuestosRetenidos) {
		instance.setImpuestosRetenidos(impuestosRetenidos);
		return this;
	}
	
	public PagoReportDtoBuilder setSubtotal(BigDecimal subtotal) {
		instance.setSubtotal(subtotal);
		return this;
	}
	
	public PagoReportDtoBuilder setTotal(BigDecimal total) {
		instance.setTotal(total);
		return this;
	}
	
	public PagoReportDtoBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}
	
	public PagoReportDtoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}
	
	public PagoReportDtoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}
	
	public PagoReportDtoBuilder setStatusFactura(String statusFactura) {
		instance.setStatusFactura(statusFactura);
		return this;
	}
	
	public PagoReportDtoBuilder setStatusPago(String statusPago) {
		instance.setStatusPago(statusPago);
		return this;
	}
	
	public PagoReportDtoBuilder setFechaCancelacion(Date fechaCancelacion) {
		instance.setFechaCancelacion(fechaCancelacion);
		return this;
	}
	
	public PagoReportDtoBuilder setFolioPago(String folioPago) {
		instance.setFolioPago(folioPago);
		return this;
	}
	
	public PagoReportDtoBuilder setFolioFiscalPago(String folioFiscalPago) {
		instance.setFolioFiscalPago(folioFiscalPago);
		return this;
	}
	
	public PagoReportDtoBuilder setImportePagado(BigDecimal importePagado) {
		instance.setImportePagado(importePagado);
		return this;
	}
	
	public PagoReportDtoBuilder setSaldoAnterior(BigDecimal saldoAnterior) {
		instance.setSaldoAnterior(saldoAnterior);
		return this;
	}
	
	public PagoReportDtoBuilder setSaldoInsoluto(BigDecimal saldoInsoluto) {
		instance.setSaldoInsoluto(saldoInsoluto);
		return this;
	}
	
	public PagoReportDtoBuilder setNumeroParcialidad(Integer numeroParcialidad) {
		instance.setNumeroParcialidad(numeroParcialidad);
		return this;
	}
	
	public PagoReportDtoBuilder setFechaPago(String fechaPago) {
		instance.setFechaPago(fechaPago);
		return this;
	}

}
