package com.business.unknow.services.services.builder;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.FilesService;

public class AbstractBuilderService {
	
	@Autowired
	private FilesService service;

	public  void getEmpresaFiles(EmpresaDto empresaDto, FacturaDto facturaDto) throws InvoiceManagerException {
		ResourceFileDto certFile = service.getResourceFileByResourceReferenceAndType(ResourceFileEnum.CERT.name(), facturaDto.getRfcEmisor(), TipoRecursoEnum.EMPRESA.name());
		ResourceFileDto keyFile = service.getResourceFileByResourceReferenceAndType(ResourceFileEnum.KEY.name(), facturaDto.getRfcEmisor(), TipoRecursoEnum.EMPRESA.name());
		empresaDto.setCertificado(certFile.getData());
		empresaDto.setLlavePrivada(keyFile.getData());
	}
}
