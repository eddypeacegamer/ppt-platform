package com.business.unknow.commons.validator;

import java.math.BigDecimal;

import com.business.unknow.Constants;
import com.business.unknow.model.dto.services.ClientDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class ClienteValidator extends AbstractValidator {

	public void validatePostCliente(ClientDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getInformacionFiscal(), "Informacion fiscal");
		checkNotNull(dto.getInformacionFiscal().getRazonSocial(), "Razon Social");
		checkNotNull(dto.getInformacionFiscal().getEstado(), "Estado");
		checkNotNull(dto.getInformacionFiscal().getCp(), "Codigo postal");
		checkNotNull(dto.getInformacionFiscal().getLocalidad(), "Localidad");
		checkNotNull(dto.getInformacionFiscal().getMunicipio(), "Municipio");
		checkNotNull(dto.getInformacionFiscal().getRfc(), "RFC");
		checkNotNull(dto.getInformacionFiscal().getCorreo(), "Correo");
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
	}

}
