package com.business.unknow.services.services.builder;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.repositories.files.ResourceFileRepository;

public class AbstractBuilderService {
	
	@Autowired
	private ResourceFileRepository resourceFileRepository;
	
	private FileHelper fileHelper = new FileHelper();

	public  void getEmpresaFiles(EmpresaDto empresaDto, FacturaDto facturaDto) throws InvoiceManagerException {
		ResourceFile certFile = resourceFileRepository
				.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.name(), facturaDto.getRfcEmisor(),
						ResourceFileEnum.CERT.name())
				.orElseThrow(() -> new InvoiceManagerException("Empresa certificate not found",
						String.format("La empresa con el rfc no tiene certificado", facturaDto.getRfcEmisor()),
						HttpStatus.SC_NOT_FOUND));
		ResourceFile keyFile = resourceFileRepository
				.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.name(), facturaDto.getRfcEmisor(),
						ResourceFileEnum.KEY.name())
				.orElseThrow(() -> new InvoiceManagerException("Empresa certificate not found",
						String.format("La empresa con el rfc no tiene certificado", facturaDto.getRfcEmisor()),
						HttpStatus.SC_NOT_FOUND));
		empresaDto.setCertificado(fileHelper.getStringFileSource((certFile.getData())));
		empresaDto.setLlavePrivada(fileHelper.getStringFileSource((keyFile.getData())));
	}
}
