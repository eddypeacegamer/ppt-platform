package com.business.unknow.services.services.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.DevolucionDtoBuilder;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.services.FacturaService;

@Service
public class DevolucionesBuilderService {
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private DevolucionMapper devolucionMapper;

	public Devolucion buildDevolucion(String foliofFact, Integer idPago, BigDecimal montoBase, Integer porcentaje,
			String receptor, String tipoReceptor) {
		return devolucionMapper.getEntityFromDevolucionDto(new DevolucionDtoBuilder()
				.setMonto((montoBase.multiply(new BigDecimal(porcentaje)).divide(new BigDecimal(16), 2,
						RoundingMode.HALF_UP)))
				.setFolio(foliofFact).setIdPagoOrigen(idPago).setReceptor(receptor).setTipoReceptor(tipoReceptor)
				.setStatusDevolucion("POR SOLICITAR").build());
	}

	public FacturaContext buildFacturaContextForPueDevolution(FacturaDto facturaDto, PagoDto pagoDto) {
		FacturaContextBuilder fcb = new FacturaContextBuilder().setFacturaDto(facturaDto).setCurrentPago(pagoDto);
		return fcb.build();
	}

	public FacturaContext buildFacturaContextForComplementoDevolution(FacturaDto facturaDto, PagoDto pagoDto) {
		FacturaContextBuilder fcb = new FacturaContextBuilder().setFacturaDto(facturaDto).setCurrentPago(pagoDto)
				.setFacturaPadreDto(facturaService.getFacturaByFolio(pagoDto.getFolioPadre()));
		return fcb.build();
	}

}
