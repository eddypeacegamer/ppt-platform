package com.business.unknow.commons.validator;

import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class EmpresaValidator extends AbstractValidator{

	public void validatePostEmpresa(EmpresaDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getInformacionFiscal(), "Informacion fiscal");
		checkNotNull(dto.getInformacionFiscal().getRfc(), "Rfc");
		checkNotNull(dto.getCertificado(), "Certificado");
		checkNotNull(dto.getLlavePrivada(), "Llave privada");
		checkNotNull(dto.getLogotipo(), "Logotipo");
	}
}


