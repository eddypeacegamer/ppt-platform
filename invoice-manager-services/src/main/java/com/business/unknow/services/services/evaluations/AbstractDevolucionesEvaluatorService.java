package com.business.unknow.services.services.evaluations;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.services.ClientService;
import com.business.unknow.services.services.FacturaService;

public class AbstractDevolucionesEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	protected ClientService clientService;

	@Autowired
	protected FacturaService facturaService;

	@Autowired
	protected DevolucionRepository devolucionRepository;

	public FacturaContext buildFacturaContextForPueDevolution(FacturaDto facturaDto, PagoDto pagoDto) {
		FacturaContextBuilder fcb = new FacturaContextBuilder()
				.setClientDto(clientService.getClientByRFC(facturaDto.getRfcRemitente())).setFacturaDto(facturaDto)
				.setCurrentPago(pagoDto);
		return fcb.build();
	}

	public FacturaContext buildFacturaContextForComplementoDevolution(FacturaDto facturaDto, PagoDto pagoDto) {
		FacturaContextBuilder fcb = new FacturaContextBuilder()
				.setClientDto(clientService.getClientByRFC(facturaDto.getRfcRemitente())).setFacturaDto(facturaDto)
				.setCurrentPago(pagoDto).setFacturaPadreDto(facturaService.getfacturaByFolio(pagoDto.getFolioPadre()));
		return fcb.build();
	}
}
