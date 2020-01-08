package com.business.unknow.services.util;

import java.util.Date;

import com.business.unknow.commons.util.FacturaCalculator;
import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.Pago;

public class FacturaDefaultValues {

	private FacturaCalculator facturaCalculator = new FacturaCalculator();

	public void assignaDefaultsFactura(FacturaDto facturaDto) throws InvoiceManagerException {
		facturaDto.setFechaCreacion(new Date());
		facturaDto.setFechaActualizacion(new Date());
		facturaDto.setStatusPago(PagoStatusEnum.SIN_PAGAR.getValor());
		facturaDto.setStatusDevolucion(DevolucionStatusEnum.SIN_DEVOLVER.getValor());
		facturaDto.setPackFacturacion(PackFacturarionEnum.SW_SAPIENS.getNombre());
		if (facturaDto.getStatusFactura() == null) {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
		}
		facturaCalculator.assignFolioInFacturaDto(facturaDto);
	}

	public Pago assignaDefaultsFacturaPPD(FacturaDto facturaDto) {
		Pago payment = new Pago();// TODO move this logic to other place
		payment.setBanco("N/A");
		payment.setCreateUser("Sistema");
		payment.setComentarioPago("Pago Automatico por sistema");
		payment.setFechaPago(new Date());
		payment.setFolio(facturaDto.getFolio());
		payment.setFolioPadre(facturaDto.getFolio());
		payment.setFormaPago("CREDITO");
		payment.setMoneda("MXN");
		payment.setMonto(facturaDto.getTotal());
		payment.setRevision1(false);
		payment.setRevision2(false);
		payment.setTipoDeCambio(1.00D);
		payment.setStatusPago("ACEPTADO");
		payment.setTipoPago("INGRESO");
		return payment;
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
