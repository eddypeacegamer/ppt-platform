package com.business.unknow.services.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.business.unknow.model.dto.services.CuentaBancariaDto;
import com.business.unknow.services.services.builder.CuentaBancariaDtoBuilder;


public class CuentaBancariaDtoRowMapper implements RowMapper<CuentaBancariaDto> {

	@Override
	public CuentaBancariaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CuentaBancariaDtoBuilder()
				.setTotal(rs.getInt("TOTAL"))
				.setId(rs.getInt("ID_CUENTA_BANCARIA"))
				.setBanco(rs.getString("BANCO"))
				.setLinea(rs.getString("LINEA"))
				.setGiro(rs.getString("GIRO"))
				.setRazonSocial(rs.getString("RAZON_SOCIAL"))
				.setEmpresa(rs.getString("RFC"))
				.setCuenta(rs.getString("NO_CUENTA"))
				.setClabe(rs.getString("CLABE"))
				.setFechaCreacion(rs.getTimestamp("FECHA_CREACION"))
				.setFechaActualizacion(rs.getTimestamp("FECHA_ACTUALIZACION"))
				.build();
	}
	

}
