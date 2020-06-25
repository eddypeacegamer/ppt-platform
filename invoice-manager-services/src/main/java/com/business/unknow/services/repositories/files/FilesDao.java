/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.services.repositories.rowmappers.ResourceFileRsExtractor;

/**
 * @author ralfdemoledor
 *
 */
@Repository
public class FilesDao {
	
	
	@Autowired
	private JdbcTemplate invoiceManagerTemplate;
	
	
	private static final String FIND_FACTURA_FILE_BY_FOLIO_AND_TYPE = "SELECT * FROM FACTURA_FILES WHERE TIPO_ARCHIVO = ? AND FOLIO = ?";
	
	private static final String DELETE_FACTURA_FILE_BY_FOLIO_AND_TYPE = "DELETE FROM FACTURA_FILES WHERE TIPO_ARCHIVO = ? AND FOLIO = ?";
	
	private static final String DELETE_FACTURA_FILE_BY_ID = "DELETE FROM FACTURA_FILES WHERE FILE_ID=?";
	
	
	private static final String FIND_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE = "SELECT * FROM RESOURCE_FILES WHERE TIPO_RECURSO = ? AND TIPO_ARCHIVO= ? AND REFERENCIA = ?";
	
	private static final String DELETE_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE = "DELETE FROM RESOURCE_FILES WHERE TIPO_RECURSO = ? AND TIPO_ARCHIVO= ? AND REFERENCIA = ?";
	
	private static final String DELETE_RESOURCE_FILE_BY_ID = "DELETE FROM RESOURCE_FILES WHERE FILE_ID= ?";
	
	
	public Optional<ResourceFileDto> findResourceFileByResourceTypeAndReference(String resource, String fileType, String reference) {
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(FIND_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE);
				ps.setString(1, resource);
				ps.setString(2, fileType);
				ps.setString(3, reference);
				return ps;
			}
		}, new ResourceFileRsExtractor());
	}

}
