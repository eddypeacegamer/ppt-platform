package com.business.unknow.services.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.business.unknow.model.dto.FacturaReportDto;
import com.business.unknow.services.repositories.rowmappers.FacturaReportDtoRowMapper;

/**
 * @author ralfdemoledor
 *
 */


@Repository
public class PagosDao {
	
	
	@Autowired
	private JdbcTemplate invoiceManagerTemplate;
	
	
	private static final String PAGOS_QUERY = "SELECT * FROM PAGOS P LEFT JOIN PAGO_FACTURAS PF ON PF.ID_PAGO=P.ID_PAGO  WHERE P.ID_PAGO IN (353,354,355)";
	
	
	
	public List<FacturaReportDto> getPagodetailsByPreFolios(List<String> prefolios){
		String questionMarks = prefolios.stream().reduce("", (a,b)->a.concat(",?")).replaceFirst(",", "");
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(String.format(PAGOS_QUERY,questionMarks));
				int i = 1;
				for (String prefolio : prefolios) {
					ps.setString(i, prefolio);
					i++;
				}
				return ps;
			} // TODO make a PagoReportRowMapper & PagoReportDto
		}, new FacturaReportDtoRowMapper());
	}
	
	

}
