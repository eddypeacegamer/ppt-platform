package com.business.unknow.services.services.executor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.dto.files.ResourceFileDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.services.FilesService;

@Service
public class EmpresaExecutorService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	@Autowired
	private FilesService filesService;	

	public EmpresaDto createEmpresa(EmpresaDto empresaDto) {
		empresaDto.getInformacionFiscal().setFechaActualizacion(new Date());
		empresaDto.getInformacionFiscal().setFechaCreacion(new Date());

		String logo = empresaDto.getLogotipo();
		filesService.upsertResourceFile(new ResourceFileDto(TipoRecursoEnum.EMPRESA.name(),
				empresaDto.getInformacionFiscal().getRfc(), ResourceFileEnum.CERT.name(), empresaDto.getCertificado()));
		filesService.upsertResourceFile(new ResourceFileDto(TipoRecursoEnum.EMPRESA.name(),
				empresaDto.getInformacionFiscal().getRfc(), ResourceFileEnum.KEY.name(), empresaDto.getLlavePrivada()));
		filesService.upsertResourceFile(
				new ResourceFileDto(TipoRecursoEnum.EMPRESA.name(), empresaDto.getInformacionFiscal().getRfc(),
						ResourceFileEnum.LOGO.name(), logo.substring(logo.indexOf("base64") + 7)));

		return empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.save(empresaMapper.getEntityFromEmpresaDto(empresaDto)));
	}

	public void updateLogo( String rfc, String data) {

		if(data!=null) {
			filesService.upsertResourceFile(
					new ResourceFileDto(ResourceFileEnum.LOGO.name(), rfc,
							TipoRecursoEnum.EMPRESA.name(), data.substring(data.indexOf("base64") + 7)));
		}
		
	}

	public void updateCertificado(String rfc, String data) {
		if(data!=null) {
		filesService.upsertResourceFile(new ResourceFileDto(TipoRecursoEnum.EMPRESA.name(),
				rfc, ResourceFileEnum.CERT.name(), data));
		}
	}

	public void updateKey(String rfc, String data) {
		if(data!=null) {
		filesService.upsertResourceFile(new ResourceFileDto(TipoRecursoEnum.EMPRESA.name(),
				rfc, ResourceFileEnum.KEY.name(), data));
		}
	}

}
