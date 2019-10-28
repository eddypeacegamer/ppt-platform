package com.business.unknow.commons.util;

import java.util.Date;

import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;

public class FacturaDefaultValues {

	public static void assignaDefaultsFactura(FacturaDto facturaDto) throws InvoiceManagerException {
		facturaDto.setFechaCreacion(new Date());
		facturaDto.setFechaActualizacion(new Date());
		facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
		facturaDto.setStatusPago(PagoStatusEnum.SIN_PAGAR.getValor());
		facturaDto.setStatusDevolucion(DevolucionStatusEnum.SIN_DEVOLVER.getValor());
		FacturaCalculator.assignFolioInFacturaDto(facturaDto);
		facturaDto.setFolio(FacturaCalculator.folioEncrypt(facturaDto));
	}
}
