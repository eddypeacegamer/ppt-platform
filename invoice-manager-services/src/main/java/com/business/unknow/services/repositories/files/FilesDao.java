/**
 * 
 */
package com.business.unknow.services.repositories.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;

import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.services.repositories.rowmappers.FacturaFileRsExtractor;
import com.business.unknow.services.repositories.rowmappers.ResourceFileRsExtractor;

/**
 * @author ralfdemoledor
 *
 */
@Repository
public class FilesDao {

	@Autowired
	private JdbcTemplate invoiceManagerTemplate;

	private static final String FIND_FACTURA_FILE_BY_FOLIO_AND_TYPE = "SELECT * FROM FACTURA_FILES WHERE FOLIO = ? AND TIPO_ARCHIVO = ?";

	private static final String DELETE_FACTURA_FILE_BY_FOLIO_AND_TYPE = "DELETE FROM FACTURA_FILES WHERE FOLIO = ? AND TIPO_ARCHIVO = ?";

	private static final String DELETE_FACTURA_FILE_BY_ID = "DELETE FROM FACTURA_FILES WHERE FILE_ID=?";

	private static final String INSERT_FACTURA_FILE = "INSERT INTO FACTURA_FILES (FOLIO, TIPO_ARCHIVO, DATA, FECHA_CREACION) VALUES(?,?,?,?)";

	private static final String FIND_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE = "SELECT * FROM RESOURCE_FILES WHERE 1=1 AND TIPO_ARCHIVO= ? AND REFERENCIA = ? 	AND TIPO_RECURSO = ?  ";

	private static final String DELETE_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE = "DELETE FROM RESOURCE_FILES WHERE TIPO_RECURSO = ? AND TIPO_ARCHIVO= ? AND REFERENCIA = ?";
	
	private static final String DELETE_RESOURCE_FILE_BY_ID = "DELETE FROM RESOURCE_FILES WHERE FILE_ID= ?";

	private static final String INSERT_RESOURCE_FILE = "INSERT INTO RESOURCE_FILES (REFERENCIA, TIPO_ARCHIVO, TIPO_RECURSO, DATA, FECHA_CREACION) VALUES(?,?,?,?,?)";

	public Optional<ResourceFileDto> findResourceFileByResourceTypeAndReference(String resource, String reference,
			String fileType) {
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(FIND_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE);
				ps.setString(1, fileType);
				ps.setString(2, reference);
				ps.setString(3, resource);
				return ps;
			}
		}, new ResourceFileRsExtractor());
	}

	public int deleteResourceFileByResourceTypeAndReference(String resource, String fileType, String reference) {
		return invoiceManagerTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(DELETE_RESOURCE_FILE_BY_RESOURCE_TYPE_AND_REFERENCE);
				ps.setString(1, resource);
				ps.setString(2, fileType);
				ps.setString(3, reference);
				return ps;
			}
		});
	}

	public int deletResourceFileById(int id) {
		return invoiceManagerTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(DELETE_RESOURCE_FILE_BY_ID);
				ps.setInt(1, id);
				return ps;
			}
		});
	}

	public int insertResourceFile(ResourceFileDto dto) {
		return invoiceManagerTemplate.update(INSERT_RESOURCE_FILE,
				new Object[] { dto.getReferencia(), dto.getTipoArchivo(), dto.getTipoRecurso(),
						new SqlLobValue(dto.getData().getBytes()), new Timestamp(System.currentTimeMillis()) },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.TIMESTAMP });
	}

	public int updateResourceFile(int id, ResourceFileDto dto) {
		deletResourceFileById(id);
		return insertResourceFile(dto);
	}
	
	public Optional<FacturaFileDto> findFacturaFileByResourceTypeAndReference(String folio, String fileType) {
		return invoiceManagerTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(FIND_FACTURA_FILE_BY_FOLIO_AND_TYPE);
				ps.setString(1, folio);
				ps.setString(2, fileType);
				return ps;
			}
		}, new FacturaFileRsExtractor());
	}

	public int deleteFacturaFileByResourceTypeAndReference(String folio, String fileType) {
		return invoiceManagerTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(DELETE_FACTURA_FILE_BY_FOLIO_AND_TYPE);
				ps.setString(1, folio);
				ps.setString(2, fileType);
				return ps;
			}
		});
	}

	public int deletFacturaFileById(int id) {
		return invoiceManagerTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(DELETE_FACTURA_FILE_BY_ID);
				ps.setInt(1, id);
				return ps;
			}
		});
	}

	public int insertFacturaFile(FacturaFileDto dto) {
		return invoiceManagerTemplate.update(INSERT_FACTURA_FILE,
				new Object[] { dto.getFolio(), dto.getTipoArchivo(), new SqlLobValue(dto.getData().getBytes()),
						new Timestamp(System.currentTimeMillis()) },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.TIMESTAMP });
	}

	public int updateFacturaFile(FacturaFileDto dto) {
		deletFacturaFileById(dto.getId());
		return insertFacturaFile(dto);
	}

}
