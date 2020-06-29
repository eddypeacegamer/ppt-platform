/**
 * 
 */
package com.business.unknow.services.repositories.rowmappers;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.business.unknow.model.dto.files.FacturaFileDto;

/**
 * @author ralfdemoledor
 *
 */
public class FacturaFileRsExtractor implements ResultSetExtractor<Optional<FacturaFileDto>> {

	@Override
	public Optional<FacturaFileDto> extractData(ResultSet rs) throws SQLException {
		LobHandler lobHandler = new DefaultLobHandler();
		if(rs.next()) {
			FacturaFileDto result =new FacturaFileDto();
			result.setId(rs.getInt("FILE_ID"));
			result.setFolio(rs.getString("FOLIO"));
			result.setTipoArchivo(rs.getString("TIPO_ARCHIVO"));
			result.setFechaCreacion(rs.getTimestamp("FECHA_CREACION"));
			byte[] fileData = lobHandler.getBlobAsBytes(rs,"DATA");
			result.setData(new String(fileData,StandardCharsets.UTF_8));
			return Optional.of(result);
		}else {
			return Optional.empty();
		}
	}

	
	
}
