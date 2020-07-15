/**
 * 
 */
package com.business.unknow.commons.builder;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.model.dto.FacturaReportDto;

/**
 * @author ralfdemoledor
 *
 */
public class FacturaReportDtoBuilder extends AbstractBuilder<FacturaReportDto> {

	public FacturaReportDtoBuilder() {
		super(new FacturaReportDto());
	}

	public FacturaReportDtoBuilder setFolio(String folio) {
		instance.setFolio(folio);
		return this;
	}

	public FacturaReportDtoBuilder setFolioFiscal(String folioFiscal) {
		instance.setFolioFiscal(folioFiscal);
		return this;
	}

	public FacturaReportDtoBuilder setFechaEmision(Date fechaEmision) {
		instance.setFechaEmision(fechaEmision);
		return this;
	}

	public FacturaReportDtoBuilder setRfcEmisor(String rfcEmisor) {
		instance.setRfcEmisor(rfcEmisor);
		return this;
	}

	public FacturaReportDtoBuilder setEmisor(String emisor) {
		instance.setEmisor(emisor);
		return this;
	}

	public FacturaReportDtoBuilder setRfcReceptor(String rfcReceptor) {
		instance.setRfcReceptor(rfcReceptor);
		return this;
	}

	public FacturaReportDtoBuilder setReceptor(String receptor) {
		instance.setReceptor(receptor);
		return this;
	}

	public FacturaReportDtoBuilder setTipoDocumento(String tipoDocumento) {
		instance.setTipoDocumento(tipoDocumento);
		return this;
	}

	public FacturaReportDtoBuilder setPackFacturacion(String packFacturacion) {
		instance.setPackFacturacion(packFacturacion);
		return this;
	}

	public FacturaReportDtoBuilder setTipoComprobante(String tipoComprobante) {
		instance.setTipoComprobante(tipoComprobante);
		return this;
	}

	public FacturaReportDtoBuilder setImpuestosTrasladados(BigDecimal impuestosTrasladados) {
		instance.setImpuestosTrasladados(impuestosTrasladados);
		return this;
	}

	public FacturaReportDtoBuilder setImpuestosRetenidos(BigDecimal impuestosRetenidos) {
		instance.setImpuestosRetenidos(impuestosRetenidos);
		return this;
	}

	public FacturaReportDtoBuilder setSubtotal(BigDecimal subtotal) {
		instance.setSubtotal(subtotal);
		return this;
	}

	public FacturaReportDtoBuilder setTotal(BigDecimal total) {
		instance.setTotal(total);
		return this;
	}
	
	public FacturaReportDtoBuilder setSaldoPendiente(BigDecimal saldoPendiente) {
		instance.setSaldoPendiente(saldoPendiente);
		return this;
	}

	public FacturaReportDtoBuilder setMetodoPago(String metodoPago) {
		instance.setMetodoPago(metodoPago);
		return this;
	}

	public FacturaReportDtoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
		return this;
	}

	public FacturaReportDtoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
		return this;
	}

	public FacturaReportDtoBuilder setStatusFactura(String statusFactura) {
		instance.setStatusFactura(statusFactura);
		return this;
	}

	public FacturaReportDtoBuilder setStatusPago(String statusPago) {
		instance.setStatusPago(statusPago);
		return this;
	}

	public FacturaReportDtoBuilder setFechaCancelacion(Date fechaCancelacion) {
		instance.setFechaCancelacion(fechaCancelacion);
		return this;
	}

	public FacturaReportDtoBuilder setCantidad(BigDecimal cantidad) {
		instance.setCantidad(cantidad);
		return this;
	}

	public FacturaReportDtoBuilder setClaveUnidad(String claveUnidad) {
		instance.setClaveUnidad(claveUnidad);
		return this;
	}

	public FacturaReportDtoBuilder setUnidad(String unidad) {
		instance.setUnidad(unidad);
		return this;
	}

	public FacturaReportDtoBuilder setClaveProdServ(Integer claveProdServ) {
		instance.setClaveProdServ(claveProdServ);
		return this;
	}

	public FacturaReportDtoBuilder setDescripcion(String descripcion) {
		instance.setDescripcion(descripcion);
		return this;
	}

	public FacturaReportDtoBuilder setValorUnitario(BigDecimal valorUnitario) {
		instance.setValorUnitario(valorUnitario);
		return this;
	}

	public FacturaReportDtoBuilder setImporte(BigDecimal importe) {
		instance.setImporte(importe);
		return this;
	}

	public FacturaReportDtoBuilder setLineaEmisor(String lineaEmisor) {
		instance.setLineaEmisor(lineaEmisor);
		return this;
	}

	public FacturaReportDtoBuilder setCorreoPromotor(String correoPromotor) {
		instance.setCorreoPromotor(correoPromotor);
		return this;
	}

	public FacturaReportDtoBuilder setPorcentajePromotor(String porcentajePromotor) {
		instance.setPorcentajePromotor(porcentajePromotor);
		return this;
	}

	public FacturaReportDtoBuilder setPorcentajeDespacho(String porcentajeDespacho) {
		instance.setPorcentajeDespacho(porcentajeDespacho);
		return this;
	}

	public FacturaReportDtoBuilder setPorcentajeConcatco(String porcentajeConcatco) {
		instance.setPorcentajeConcatco(porcentajeConcatco);
		return this;
	}

	public FacturaReportDtoBuilder setPorcentajeCliente(String porcentajeCliente) {
		instance.setPorcentajeCliente(porcentajeCliente);
		return this;
	}

}
