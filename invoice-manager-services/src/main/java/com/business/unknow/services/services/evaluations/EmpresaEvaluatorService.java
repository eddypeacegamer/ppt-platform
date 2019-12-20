package com.business.unknow.services.services.evaluations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.client.swsapiens.util.SwSapiensClientException;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.executor.SwSapinsExecutorService;

@Service
public class EmpresaEvaluatorService extends AbstractEvaluatorService {
	
	@Autowired
	private SwSapinsExecutorService swSapinsExecutorService;

	public EmpresaDto validateEmpresa(EmpresaDto empresaDto) throws InvoiceManagerException {
		try {
			swSapinsExecutorService.validateLco(empresaDto.getNoCertificado());
			createResourceFile(empresaDto.getCertificado(), empresaDto.getInformacionFiscal().getRfc(),
					TipoRecursoEnum.EMPRESA.getDescripcion(),ResourceFileEnum.CERT.getDescripcion());
			createResourceFile(empresaDto.getLlavePrivada(), empresaDto.getInformacionFiscal().getRfc(),
					TipoRecursoEnum.EMPRESA.getDescripcion(),ResourceFileEnum.KEY.getDescripcion());
			createResourceFile(empresaDto.getLogotipo(), empresaDto.getInformacionFiscal().getRfc(),
					TipoRecursoEnum.EMPRESA.getDescripcion(),ResourceFileEnum.LOGO.getDescripcion());
			return empresaMapper
					.getEmpresaDtoFromEntity(empresaRepository.save(empresaMapper.getEntityFromEmpresaDto(empresaDto)));
		} catch (SwSapiensClientException e) {
			throw new InvoiceManagerException("Error validando la empresa en LCO", e.getMessage(), e.getHttpStatus());
		}
	}

}
