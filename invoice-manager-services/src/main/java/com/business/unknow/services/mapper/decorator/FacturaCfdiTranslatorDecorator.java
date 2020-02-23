package com.business.unknow.services.mapper.decorator;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.ComplementoPago;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Impuesto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.services.mapper.factura.FacturaCfdiTranslatorMapper;

public abstract class FacturaCfdiTranslatorDecorator implements FacturaCfdiTranslatorMapper {

	@Autowired
	private FacturaCfdiTranslatorMapper delegate;

	private DateHelper dateHelper = new DateHelper();

	@Override
	public Cfdi complementoRootInfo(CfdiDto cfdiDto, EmpresaDto empresaDto) {
		Cfdi cfdi = delegate.complementoRootInfo(cfdiDto,empresaDto);
		cfdi.setTotal(cfdi.getTotal().setScale(0,BigDecimal.ROUND_DOWN));
		cfdi.setSubtotal(cfdi.getSubtotal().setScale(0,BigDecimal.ROUND_DOWN));
		return cfdi;
	}
	

	@Override
	public Cfdi cdfiRootInfo(FacturaDto facturaDto, EmpresaDto empresaDto) {
		Cfdi cfdi = delegate.cdfiRootInfo(facturaDto, empresaDto);
		cfdi.setFecha(dateHelper.getStringFromFecha(facturaDto.getFechaActualizacion(),
				FacturaConstants.FACTURA_DATE_FORMAT));
		return cfdi;
	}

	@Override
	public Concepto cfdiConcepto(ConceptoDto dto) {
		Concepto conpeto = delegate.cfdiConcepto(dto);
		if (!dto.getImpuestos().isEmpty()) {
			Impuesto impuesto = new Impuesto();
			impuesto.setTranslados(new ArrayList<>());
			for (ImpuestoDto impuestoDto : dto.getImpuestos()) {
				Translado traslado = delegate.cfdiImpuesto(impuestoDto);
				traslado.setTasaOCuota(String.format("%05f", Double.valueOf(traslado.getTasaOCuota())));
				impuesto.getTranslados().add(traslado);
			}
			conpeto.setImpuestos(impuesto);
		}
		return conpeto;
	}
	
	@Override
	public Concepto complementoConcepto(ConceptoDto dto) {
		Concepto conpeto = delegate.complementoConcepto(dto);
		conpeto.setValorUnitario(conpeto.getValorUnitario().setScale(0,BigDecimal.ROUND_DOWN));
		conpeto.setImporte(conpeto.getImporte().setScale(0,BigDecimal.ROUND_DOWN));
		return conpeto;
	}
	
	
	
	@Override
	public ComplementoPago complementoComponente(CfdiPagoDto cfdiPago) {
		ComplementoPago complementoPago = delegate.complementoComponente(cfdiPago);
		complementoPago.setFechaPago(
				dateHelper.getStringFromFecha(cfdiPago.getFechaPago(), FacturaConstants.FACTURA_DATE_FORMAT));
		complementoPago.setMonto(String.format("%.2f", cfdiPago.getMonto()));
		complementoPago.getComplementoDocRelacionado().setImpPagado(String.format("%.2f", cfdiPago.getMonto()));
		return complementoPago;
	}

}
