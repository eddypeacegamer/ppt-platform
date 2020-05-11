/**
 * 
 */
package com.business.unknow.services.repositories.facturas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.model.dto.PagoReportDto;
import com.business.unknow.services.repositories.rowmappers.FacturaReportDtoRowMapper;
import com.business.unknow.services.repositories.rowmappers.FacturaReportDtoRsExtractor;
import com.business.unknow.services.repositories.rowmappers.PagoReportDtoRowMapper;
import com.business.unknow.services.repositories.rowmappers.PagoReportDtoRsExtractor;

/**
 * @author hha0009
 *
 */


@Repository
public class FacturaDao {
	
	@Autowired
	private JdbcTemplate invoiceManagerTemplate;
	
	private static final String  INVOICE_BY_FOLIO = "SELECT \n" + 
			"	f.FOLIO,f.LINEA_EMISOR,cts.CORREO_PROMOTOR,cts.PORCENTAJE_CLIENTE,cts.PORCENTAJE_CONTACTO,cts.PORCENTAJE_DESPACHO,cts.PORCENTAJE_PROMOTOR,f.UUID,cfdi.FECHA,f.RAZON_SOCIAL_EMISOR,f.RFC_EMISOR,f.RAZON_SOCIAL_REMITENTE,f.RFC_REMITENTE,f.TIPO_DOCUMENTO,f.PACK_FACTURACION,cfdi.TIPO_COMPROBANTE,\n" + 
			"					cfdi.IMP_TRASLADADOS,cfdi.IMP_RETENIDOS,cfdi.SUB_TOTAL,cfdi.TOTAL,cfdi.METODO_PAGO,cfdi.FORMA_PAGO,cfdi.MONEDA,f.STATUS_PAGO,f.STATUS_FACTURA,f.FECHA_CANCELADO,ctos.CANTIDAD,ctos.CLAVE_UNIDAD,ctos.UNIDAD,\n" + 
			"					ctos.CLAVE_PROD_SERV,ctos.DESCRIPCION,ctos.VALOR_UNITARIO,ctos.IMPORTE\n" + 
			"FROM FACTURAS f\n" + 
			"INNER JOIN CFDI cfdi ON f.ID_CFDI = cfdi.ID_CFDI \n" + 
			"INNER JOIN CFDI_CONCEPTOS ctos ON cfdi.ID_CFDI = ctos.ID_CFDI \n" + 
			"LEFT JOIN CLIENTES  cts ON cts.RFC = f.RFC_REMITENTE \n" + 
			"WHERE cfdi.FOLIO= ?";
	
	private static final String  INVOICES_BY_FOLIOS = "SELECT \n" + 
			"	f.FOLIO,f.LINEA_EMISOR,cts.CORREO_PROMOTOR,cts.PORCENTAJE_CLIENTE,cts.PORCENTAJE_CONTACTO,cts.PORCENTAJE_DESPACHO,cts.PORCENTAJE_PROMOTOR,f.UUID,cfdi.FECHA,f.RAZON_SOCIAL_EMISOR,f.RFC_EMISOR,f.RAZON_SOCIAL_REMITENTE,f.RFC_REMITENTE,f.TIPO_DOCUMENTO,f.PACK_FACTURACION,cfdi.TIPO_COMPROBANTE,\n" + 
			"					cfdi.IMP_TRASLADADOS,cfdi.IMP_RETENIDOS,cfdi.SUB_TOTAL,cfdi.TOTAL,cfdi.METODO_PAGO,cfdi.FORMA_PAGO,cfdi.MONEDA,f.STATUS_PAGO,f.STATUS_FACTURA,f.FECHA_CANCELADO,ctos.CANTIDAD,ctos.CLAVE_UNIDAD,ctos.UNIDAD,\n" + 
			"					ctos.CLAVE_PROD_SERV,ctos.DESCRIPCION,ctos.VALOR_UNITARIO,ctos.IMPORTE\n" + 
			"FROM FACTURAS f\n" + 
			"INNER JOIN CFDI cfdi ON f.ID_CFDI = cfdi.ID_CFDI \n" + 
			"INNER JOIN CFDI_CONCEPTOS ctos ON cfdi.ID_CFDI = ctos.ID_CFDI \n" + 
			"LEFT JOIN CLIENTES  cts ON cts.RFC = f.RFC_REMITENTE \n" + 
			"WHERE cfdi.FOLIO IN (%s)";
	
	private static final String COMPLEMENT_BY_FOLIO = "SELECT f.FOLIO_PADRE AS FOLIO,p.ID_DOCUMENTO AS UUID,cfdi.FECHA,f.RAZON_SOCIAL_EMISOR,f.RFC_EMISOR,f.RAZON_SOCIAL_REMITENTE,f.RFC_REMITENTE,f.TIPO_DOCUMENTO,f.PACK_FACTURACION,"
			+ "	cfdi.TIPO_COMPROBANTE,cfdi.IMP_TRASLADADOS,cfdi.IMP_RETENIDOS,cfdi.SUB_TOTAL,cfdi.TOTAL,p.MONEDA,p.FORMA_PAGO,cfdi.METODO_PAGO,f.STATUS_PAGO,f.STATUS_FACTURA,f.FECHA_CANCELADO,f.FOLIO AS FOLIO_PAGO,f.UUID AS UUID_PAGO,"
			+ "p.IMPORTE_PAGADO,p.IMPORTE_SALDO_ANTERIOR,p.IMPORTE_SALDO_INSOLUTO,p.NUM_PARCIALIDAD,p.FECHA_PAGO FROM FACTURAS f INNER JOIN CFDI cfdi ON f.ID_CFDI = cfdi.ID_CFDI INNER JOIN CFDI_PAGOS p ON cfdi.ID_CFDI = p.ID_CFDI "
			+"WHERE cfdi.FOLIO= ?";
	
	private static final String COMPLEMENTS_BY_FOLIOS = "SELECT f.FOLIO_PADRE AS FOLIO,p.ID_DOCUMENTO AS UUID,cfdi.FECHA,f.RAZON_SOCIAL_EMISOR,f.RFC_EMISOR,f.RAZON_SOCIAL_REMITENTE,f.RFC_REMITENTE,f.TIPO_DOCUMENTO,f.PACK_FACTURACION,"
			+ "	cfdi.TIPO_COMPROBANTE,cfdi.IMP_TRASLADADOS,cfdi.IMP_RETENIDOS,cfdi.SUB_TOTAL,cfdi.TOTAL,p.MONEDA,p.FORMA_PAGO,cfdi.METODO_PAGO,f.STATUS_PAGO,f.STATUS_FACTURA,f.FECHA_CANCELADO,f.FOLIO AS FOLIO_PAGO,f.UUID AS UUID_PAGO,"
			+ "p.IMPORTE_PAGADO,p.IMPORTE_SALDO_ANTERIOR,p.IMPORTE_SALDO_INSOLUTO,p.NUM_PARCIALIDAD,p.FECHA_PAGO FROM FACTURAS f INNER JOIN CFDI cfdi ON f.ID_CFDI = cfdi.ID_CFDI INNER JOIN CFDI_PAGOS p ON cfdi.ID_CFDI = p.ID_CFDI "
			+"WHERE cfdi.FOLIO IN (%s)";
	
	public Optional<FacturaReportDto> getInvoiceDetailByFolio(String folio) {
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(INVOICE_BY_FOLIO);
				ps.setString(1, folio);
				return ps;
			}
		}, new FacturaReportDtoRsExtractor());
	}
	
	
	public List<FacturaReportDto> getInvoiceDetailsByFolios(List<String> folios){
		String questionMarks = folios.stream().reduce("", (a,b)->a.concat(",?")).replaceFirst(",", "");
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(String.format(INVOICES_BY_FOLIOS,questionMarks));
				int i = 1;
				for (String folio : folios) {
					ps.setString(i, folio);
					i++;
				}
				return ps;
			}
		}, new FacturaReportDtoRowMapper());
	}
	
	
	public Optional<PagoReportDto> getComplementDetailByFolio(String folio) {
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(COMPLEMENT_BY_FOLIO);
				ps.setString(1, folio);
				return ps;
			}
		}, new PagoReportDtoRsExtractor());
	}
	
	public List<PagoReportDto> getComplementsDetailsByFolios(List<String> folios){
		String questionMarks = folios.stream().reduce("", (a,b)->a.concat(",?")).replaceFirst(",", "");
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(String.format(COMPLEMENTS_BY_FOLIOS,questionMarks));
				int i = 1;
				for (String folio : folios) {
					ps.setString(i, folio);
					i++;
				}
				return ps;
			}
		}, new PagoReportDtoRowMapper());
	}
	
	

}
