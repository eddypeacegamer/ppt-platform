package com.business.unknow.commons.validator;

import java.math.BigDecimal;

import com.business.unknow.Constants;
import com.business.unknow.model.dto.services.ClientDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class ClienteValidator extends AbstractValidator {

	public void validatePostCliente(ClientDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getInformacionFiscal(), "Informacion fiscal");
		checkNotNull(dto.getInformacionFiscal().getRazonSocial(), "Razon Social");
		checkNotEmpty(dto.getInformacionFiscal().getRazonSocial(), "Razon Social");
		checkValidString(dto.getInformacionFiscal().getRazonSocial());
		checkNotNull(dto.getInformacionFiscal().getEstado(), "Estado");
		checkNotEmpty(dto.getInformacionFiscal().getEstado(), "Estado");
		checkNotNull(dto.getInformacionFiscal().getCalle(), "calle");
		checkNotEmpty(dto.getInformacionFiscal().getCalle(), "calle");
		checkNotNull(dto.getInformacionFiscal().getCp(), "Codigo postal");
		checkNotEmpty(dto.getInformacionFiscal().getCp(), "Codigo postal");
		checkNotNull(dto.getInformacionFiscal().getLocalidad(), "Localidad");
		checkNotEmpty(dto.getInformacionFiscal().getLocalidad(), "Localidad");
		checkNotNull(dto.getInformacionFiscal().getMunicipio(), "Municipio");
		checkNotEmpty(dto.getInformacionFiscal().getMunicipio(), "Municipio");
		checkNotNull(dto.getInformacionFiscal().getRfc(), "RFC");
		checkNotEmpty(dto.getInformacionFiscal().getRfc(), "RFC");
		checkNotNull(dto.getInformacionFiscal().getCorreo(), "Correo");
		checkNotEmpty(dto.getInformacionFiscal().getCorreo(), "Correo");
		if (dto.getPorcentajeCliente().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvoiceManagerException("El porcentaje debe ser positivo",
					String.format("El porcentaje %s debe ser positivo", dto.getPorcentajeCliente().toString()),
					Constants.BAD_REQUEST);
		}
		if (dto.getPorcentajeContacto().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvoiceManagerException("El porcentaje debe ser positivo",
					String.format("El porcentaje %s debe ser positivo", dto.getPorcentajeContacto().toString()),
					Constants.BAD_REQUEST);
		}
		if (dto.getPorcentajeDespacho().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvoiceManagerException("El porcentaje debe ser positivo",
					String.format("El porcentaje %s debe ser positivo", dto.getPorcentajeDespacho().toString()),
					Constants.BAD_REQUEST);
		}
		if (dto.getPorcentajePromotor().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvoiceManagerException("El porcentaje debe ser positivo",
					String.format("El porcentaje %s debe ser positivo", dto.getPorcentajePromotor().toString()),
					Constants.BAD_REQUEST);
		}
		if (dto.getInformacionFiscal().getCp().matches("[a-zA-Z]+")) {
			throw new InvoiceManagerException("El codigo postal esta incorrect", "No debe llevar letras",
					Constants.BAD_REQUEST);
		}
		if (dto.getInformacionFiscal().getCorreo() != null && !dto.getInformacionFiscal().getCorreo().isEmpty()) {
			checkValidEmail(dto.getInformacionFiscal().getCorreo());
		}
		if (dto.getPorcentajeContacto().compareTo(BigDecimal.ZERO) > 0) {
			checkNotNull(dto.getCorreoContacto(), "Conotacto");
			checkNotEmpty(dto.getCorreoContacto(), "Conotacto");
		}
		if (dto.getPorcentajeContacto().compareTo(BigDecimal.ZERO) == 0) {
			if (dto.getCorreoContacto() != null && !dto.getCorreoContacto().isEmpty()) {
				throw new InvoiceManagerException("No debe haber contacto si no se le asigno porcentaje",
						"Se debe poner el contacto si se le asigno un porcentaje", Constants.BAD_REQUEST);
			}
		}
	}

}
