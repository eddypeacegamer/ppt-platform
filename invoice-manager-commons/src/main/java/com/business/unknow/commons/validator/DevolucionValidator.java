package com.business.unknow.commons.validator;

import com.business.unknow.model.dto.pagos.PagoDevolucionDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class DevolucionValidator extends AbstractValidator{

	public void validatePostDevolucionPago(PagoDevolucionDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getMoneda(), "Moneda");
		checkNotNull(dto.getMonto(), "Monto");
		checkNotNull(dto.getFormaPago(), "Forma de pago");
		checkNotNull(dto.getBeneficiario(), "Beneficiario");
		checkNotNull(dto.getTipoReferencia(), "Tipo referencia");
		checkNotNull(dto.getTipoReceptor(), "Tipo receptor");
		checkNotNull(dto.getReceptor(), "Receptor");
		
		
	}
}
