package com.business.unknow.commons.validator;

import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class EmpresaValidator extends AbstractValidator {

	public void validatePostEmpresa(EmpresaDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getInformacionFiscal(), "Informacion fiscal");
		checkNotNull(dto.getInformacionFiscal().getRfc(), "Rfc");
		checkNotNull(dto.getInformacionFiscal().getRazonSocial(), "Razon social");
		checkNotEmpty(dto.getInformacionFiscal().getRazonSocial(), "Razon social");
		checkNotNull(dto.getRegimenFiscal(), "Regimen fiscal");
		checkNotNull(dto.getInformacionFiscal().getCp(), "Codigo postal");
		checkNotNull(dto.getInformacionFiscal().getMunicipio(), "Municipio");
		checkNotNull(dto.getInformacionFiscal().getEstado(), "Estado");
		checkNotNull(dto.getInformacionFiscal().getLocalidad(), "Localidad");
		checkNotNull(dto.getInformacionFiscal().getCalle(), "Calle");
		checkNotNull(dto.getGiro(), "Giro");
		checkNotNull(dto.getTipo(), "Tipo");
		checkNotNull(dto.getEncabezado(), "Encabezado");
		checkNotNull(dto.getPiePagina(), "Pie de pagina");
		checkNotNull(dto.getContactoAdmin(), "Contacto");
		checkNotNull(dto.getCorreo(), "Correo");
		checkNotNull(dto.getPwCorreo(), "pw Correo");
		checkNotNull(dto.getPwSat(), "pw Sat");
		checkNotNull(dto.getCertificado(), "Certificado");
		checkNotNull(dto.getLlavePrivada(), "Llave privada");
		checkNotNull(dto.getLogotipo(), "Logotipo");
	}
}
