package com.business.unknow.commons.validator;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;

public class FacturaValidator {

	private static final String ATTRIBUTE_REQUIRED = "Attribute required.";
	private static final String ATTRIBUTE_REQUIRED_MESSAGE = "Error, attribute [%s] can't be null.";

	public void validatePostFactura(FacturaDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getRfcEmisor(), "Rfc Emisor");
		checkNotNull(dto.getRazonSocialEmisor(), "Razon Social Emisor");
		checkNotNull(dto.getRfcRemitente(), "Rfc Remitente");
		checkNotNull(dto.getRazonSocialRemitente(), "Razon Social Remitente");
	}
	
	public void validatePostCfdi(CfdiDto dto,String folio) throws InvoiceManagerException {
		if(!folio.equals(dto.getFolio())) {
			throw new InvoiceManagerException("Error al crear Cfdi","Los folios son diferentes",
					Constants.BAD_REQUEST);
		}
	}

	public void checkNotNull(Object var, String attribute) throws InvoiceManagerException {
		if (var == null) {
			throw new InvoiceManagerException(ATTRIBUTE_REQUIRED, String.format(ATTRIBUTE_REQUIRED_MESSAGE, attribute),
					Constants.BAD_REQUEST);
		}
	}

}
