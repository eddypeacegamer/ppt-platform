package com.business.unknow.services.services.executor;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.files.ResourceFile;
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
		String logo = empresaDto.getLogotipo();
		createResourceFile(logo.substring(logo.indexOf("base64")+7), empresaDto.getInformacionFiscal().getRfc(),
				TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.LOGO.name());
		return empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.save(empresaMapper.getEntityFromEmpresaDto(empresaDto)));
	}
	
	public void updateLogo(String rfc , String data) {
		if(data!=null) {
			Optional<ResourceFile> logoOpt = resourceFileRepository.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.name(), rfc,  ResourceFileEnum.LOGO.name());
			if(logoOpt.isPresent()) {
				ResourceFile resource= logoOpt.get(); 
				resource.setData(data.substring(data.indexOf("base64")+7).getBytes());
				resourceFileRepository.save(resource);
			}else {
				createResourceFile(data, rfc,
						TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.LOGO.name());
			}
		}
	}
	
	public void updateCerrtificado(String rfc , String data) {
		if(data!=null) {
			Optional<ResourceFile> logoOpt = resourceFileRepository.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.name(), rfc,  ResourceFileEnum.CERT.name());
			if(logoOpt.isPresent()) {
				ResourceFile resource= logoOpt.get(); 
				resource.setData(data.getBytes());
				resourceFileRepository.save(resource);
			}else {
				createResourceFile(data, rfc,
						TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.CERT.name());
			}
		}
	}
	
	public void updateKey(String rfc , String data) {
		if(data!=null) {
			Optional<ResourceFile> logoOpt = resourceFileRepository.findByTipoRecursoAndReferenciaAndTipoArchivo(TipoRecursoEnum.EMPRESA.name(), rfc,  ResourceFileEnum.KEY.name());
			if(logoOpt.isPresent()) {
				ResourceFile resource= logoOpt.get(); 
				resource.setData(data.getBytes());
				resourceFileRepository.save(resource);
			}else {
				createResourceFile(data, rfc,
						TipoRecursoEnum.EMPRESA.name(), ResourceFileEnum.KEY.name());
			}
		}
	}
	


}
