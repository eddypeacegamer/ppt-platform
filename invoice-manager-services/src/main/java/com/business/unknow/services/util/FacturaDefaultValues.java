package com.business.unknow.services.util;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.Constants.PagoPpdCreditoDefaults;
import com.business.unknow.commons.util.FacturaCalculator;
import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;

public class FacturaDefaultValues {

	private FacturaCalculator facturaCalculator = new FacturaCalculator();

	public void assignaDefaultsFactura(FacturaDto facturaDto) throws InvoiceManagerException {
		facturaDto.setStatusPago(PagoStatusEnum.SIN_PAGAR.getValor());
		facturaDto.setStatusDevolucion(DevolucionStatusEnum.SIN_DEVOLVER.getValor());
		facturaDto.setPackFacturacion(PackFacturarionEnum.SW_SAPIENS.name());
		if (facturaDto.getStatusFactura() == null) {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
		}
		facturaCalculator.assignFolioInFacturaDto(facturaDto);
	}

	public PagoDto assignaDefaultsPagoPPD(CfdiDto cfdi) {
		PagoBuilder pb = new PagoBuilder().setBanco(PagoPpdCreditoDefaults.BANCO)
				.setCreateUser(PagoPpdCreditoDefaults.USER).setComentarioPago(PagoPpdCreditoDefaults.COMENTARIO)
				.setFechaPago(new Date()).setFolio(cfdi.getFolio()).setFolioPadre(cfdi.getFolio())
				.setFormaPago(PagoPpdCreditoDefaults.FORMA_PAGO).setMoneda(PagoPpdCreditoDefaults.MONEDA)
				.setMonto(cfdi.getTotal()).setRevision1(false).setRevision2(false)
				.setTipoDeCambio(new BigDecimal(PagoPpdCreditoDefaults.TIPO_CAMBIO))
				.setStatusPago(PagoPpdCreditoDefaults.STATUS_PAGO).setTipoPago(PagoPpdCreditoDefaults.TIPO_CAMBIO);
		return pb.build();
	}

	public void assignaDefaultsComplemento(FacturaDto facturaDto) throws InvoiceManagerException {
		facturaDto.setFechaCreacion(new Date());
		facturaDto.setFechaActualizacion(new Date());
		facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_TESORERIA.getValor());
		facturaDto.setStatusPago(PagoStatusEnum.SIN_PAGAR.getValor());
		facturaDto.setStatusDevolucion(DevolucionStatusEnum.SIN_DEVOLVER.getValor());
		facturaCalculator.assignFolioInFacturaDto(facturaDto);
	}
	
	
}
