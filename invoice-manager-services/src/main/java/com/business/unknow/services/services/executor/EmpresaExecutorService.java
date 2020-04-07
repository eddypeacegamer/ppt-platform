package com.business.unknow.services.services.executor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;

@Service
public class EmpresaExecutorService extends AbstractExecutorService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	public EmpresaDto createEmpresa(EmpresaDto empresaDto) throws InvoiceManagerException {
		empresaDto.getInformacionFiscal().setFechaActualizacion(new Date());
		empresaDto.getInformacionFiscal().setFechaCreacion(new Date());
		createResourceFile(empresaDto.getCertificado(), empresaDto.getInformacionFiscal().getRfc(),
				TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.CERT.name());
		createResourceFile(empresaDto.getLlavePrivada(), empresaDto.getInformacionFiscal().getRfc(),
				TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.KEY.name());
		createResourceFile(empresaDto.getLogotipo(), empresaDto.getInformacionFiscal().getRfc(),
				TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.LOGO.name());
		return empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.save(empresaMapper.getEntityFromEmpresaDto(empresaDto)));
	}

}
