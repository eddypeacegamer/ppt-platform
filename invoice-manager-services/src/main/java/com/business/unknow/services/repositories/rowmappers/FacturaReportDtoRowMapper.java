/**
 * 
 */
package com.business.unknow.services.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.business.unknow.commons.builder.FacturaReportDtoBuilder;
import com.business.unknow.model.dto.FacturaReportDto;

/**
 * @author ralfdemoledor
 *
 */
public class FacturaReportDtoRowMapper implements RowMapper<FacturaReportDto> {

	@Override
	public FacturaReportDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new FacturaReportDtoBuilder()
				.setFolio(rs.getString("FOLIO"))
				.setLineaEmisor(rs.getString("LINEA_EMISOR"))
				.setCorreoPromotor(rs.getString("CORREO_PROMOTOR"))
				.setPorcentajeCliente(rs.getString("PORCENTAJE_CLIENTE"))
				.setPorcentajeConcatco(rs.getString("PORCENTAJE_CONTACTO"))
				.setPorcentajeDespacho(rs.getString("PORCENTAJE_DESPACHO"))
				.setPorcentajePromotor(rs.getString("PORCENTAJE_PROMOTOR"))
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
				.setSaldoPendiente(rs.getBigDecimal("SALDO_PENDIENTE"))
				.setMetodoPago(rs.getString("METODO_PAGO"))
				.setFormaPago(rs.getString("FORMA_PAGO"))
				.setMoneda(rs.getString("MONEDA"))
				.setStatusFactura(rs.getString("STATUS_FACTURA"))
				.setFechaCancelacion(rs.getDate("FECHA_CANCELADO"))
				.setCantidad(rs.getBigDecimal("CANTIDAD"))
				.setClaveUnidad(rs.getString("CLAVE_UNIDAD"))
				.setUnidad(rs.getString("UNIDAD"))
				.setClaveProdServ(rs.getInt("CLAVE_PROD_SERV"))
				.setDescripcion(rs.getString("DESCRIPCION"))
				.setValorUnitario(rs.getBigDecimal("VALOR_UNITARIO"))
				.setImporte(rs.getBigDecimal("IMPORTE"))
				.build();
	}

}
