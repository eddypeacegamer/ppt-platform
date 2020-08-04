package com.business.unknow.services.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

public class FacturaAmountDocument implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return rs.getInt("CANTIDAD");
		} else {
			return 0;
		}
	}

}
