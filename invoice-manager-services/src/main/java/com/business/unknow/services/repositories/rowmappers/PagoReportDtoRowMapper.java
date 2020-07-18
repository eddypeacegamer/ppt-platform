/**
 * 
 */
package com.business.unknow.services.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.business.unknow.commons.builder.PagoReportDtoBuilder;
import com.business.unknow.model.dto.PagoReportDto;

/**
 * @author ralfdemoledor
 *
 */
public class PagoReportDtoRowMapper implements RowMapper<PagoReportDto> {

	@Override
	public PagoReportDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new PagoReportDtoBuilder()
				.setFolio(rs.getString("FOLIO"))
				.setFolioFiscal(rs.getString("UUID"))
				.setFechaEmision(rs.getDate("FECHA"))
				.setRfcEmisor(rs.getString("RFC_EMISOR"))
				.setEmisor(rs.getString("RAZON_SOCIAL_EMISOR"))
				.setRfcReceptor(rs.getString("RFC_REMITENTE"))
				.setReceptor(rs.getString("RAZON_SOCIAL_REMITENTE"))
				.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"))
				.setPackFacturacion(rs.getString("PACK_FACTURACION"))
				.setTipoComprobante(rs.getString("TIPO_COMPROBANTE"))
				.setImpuestosTrasladados(rs.getBigDecimal("IMP_TRASLADADOS"))
				.setImpuestosRetenidos(rs.getBigDecimal("IMP_RETENIDOS"))
				.setSubtotal(rs.getBigDecimal("SUB_TOTAL"))
				.setTotal(rs.getBigDecimal("TOTAL"))
				.setMetodoPago(rs.getString("METODO_PAGO"))
				.setFormaPago(rs.getString("FORMA_PAGO"))
				.setMoneda(rs.getString("MONEDA"))
				.setStatusFactura(rs.getString("STATUS_FACTURA"))
				.setFechaCancelacion(rs.getDate("FECHA_CANCELADO"))
				.setFolioPago(rs.getString("FOLIO_PAGO"))
				.setFolioFiscalPago(rs.getString("UUID_PAGO"))
				.setImportePagado(rs.getBigDecimal("IMPORTE_PAGADO"))
				.setSaldoAnterior(rs.getBigDecimal("IMPORTE_SALDO_ANTERIOR"))
				.setSaldoInsoluto(rs.getBigDecimal("IMPORTE_SALDO_INSOLUTO"))
				.setNumeroParcialidad(rs.getInt("NUM_PARCIALIDAD"))
				.setFechaPago(rs.getString("FECHA_PAGO"))
				.build();
	}

}
