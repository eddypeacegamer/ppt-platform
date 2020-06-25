/**
 * 
 */
package com.business.unknow.services.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.business.unknow.model.dto.files.ResourceFileDto;

/**
 * @author ralfdemoledor
 *
 */
public class ResourceFileRsExtractor implements ResultSetExtractor<Optional<ResourceFileDto>> {

	@Override
	public Optional<ResourceFileDto> extractData(ResultSet rs) throws SQLException {
		if(rs.next()) {
			ResourceFileDto result =new ResourceFileDto();
			result.setId(rs.getInt("FILE_ID"));
			result.setReferencia(rs.getString("REFERENCIA"));
			result.setTipoArchivo(rs.getString("TIPO_ARCHIVO"));
			result.setTipoRecurso(rs.getString("TIPO_RECURSO"));
			result.setData(rs.getString("DATA"));
			result.setFechaCreacion(rs.getTimestamp("FECHA_CREACION"));
			return Optional.of(result);
		}else {
			return Optional.empty();
		}
	}

	
	
}
