package com.business.unknow.services.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.repositories.rowmappers.CuentaBancariaDtoRowMapper;


@Repository
public class CuentasBanncariasRepositoryDto {
	
	@Autowired
	private JdbcTemplate invoiceManagerTemplate;
	
	 private static final String FILTER_CUENTAS_BANCARIAS = "select count(*) OVER() AS TOTAL, "
	 		+ "cb.ID_CUENTA_BANCARIA, cb.BANCO , e.LINEA, cg.NOMBRE as GIRO ,c2.RAZON_SOCIAL ,e.RFC ,"
				+ " cb.NO_CUENTA ,cb.CLABE ,cb.FECHA_CREACION ,cb.FECHA_ACTUALIZACION " + 
				"from CUENTAS_BANCARIAS cb INNER join EMPRESAS e on e.RFC = cb.EMPRESA INNER join CAT_GIROS cg on"
				+ " cg.ID_GIRO = e.GIRO_ID INNER join CONTRIBUYENTES c2 on c2.RFC = e.RFC WHERE cb.BANCO like upper(?)"
				+ " and e.RFC like upper(?) and cb.CLABE like upper(?) and cb.NO_CUENTA like upper(?) " 
				+ "and cb.FECHA_CREACION between ? and ?  ORDER BY cb.FECHA_CREACION DESC LIMIT ? OFFSET ?";
	
	 
	 
	 public List<CuentaBancariaDto> resultQry (String banco, String empresa, String clabe, String cuenta, Date start,Date end, int offset, int size) { 
		
		 return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(FILTER_CUENTAS_BANCARIAS);
				ps.setString(1, banco+ "%");
				ps.setString(2, empresa+ "%");
				ps.setString(3, clabe+ "%");
				ps.setString(4, cuenta+ "%");		
				ps.setDate(5,  convert(start));
				ps.setDate(6, convert(end));
				ps.setInt(7, size);
				ps.setInt(8, offset);
				
				return ps;
			}
		}, new CuentaBancariaDtoRowMapper());}

		 private static java.sql.Date convert(java.util.Date uDate) {
			 DateTime dtOrg = new DateTime(uDate);
			 DateTime dtPlusOne = dtOrg.plusDays(1);
		        java.sql.Date sDate = new java.sql.Date(dtPlusOne.toDate().getTime());
		        return sDate;
		    }
}
