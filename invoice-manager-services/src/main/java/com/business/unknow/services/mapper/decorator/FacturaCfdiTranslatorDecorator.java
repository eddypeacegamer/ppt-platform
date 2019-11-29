package com.business.unknow.services.mapper.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import com.business.unknow.Constants;
import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.NumberHelper;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.ComplementoPago;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Impuesto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
import com.business.unknow.services.mapper.FacturaCfdiTranslatorMapper;

public abstract class FacturaCfdiTranslatorDecorator implements FacturaCfdiTranslatorMapper {

	@Autowired
	private FacturaCfdiTranslatorMapper delegate;

	private DateHelper dateHelper = new DateHelper();

	private NumberHelper numberHelper = new NumberHelper();

	@Override
	public Cfdi cdfiRootInfo(FacturaDto facturaDto, EmpresaDto empresaDto) {
		Cfdi cfdi = delegate.cdfiRootInfo(facturaDto, empresaDto);
		cfdi.setTotal(numberHelper.assignPrecision(cfdi.getTotal(), Constants.DEFAULT_SCALE));
		cfdi.setSubtotal(numberHelper.assignPrecision(cfdi.getSubtotal(), Constants.DEFAULT_SCALE));
		if (cfdi.getDescuento() != null) {
			cfdi.setDescuento(numberHelper.assignPrecision(cfdi.getDescuento(), Constants.DEFAULT_SCALE));
		}
		cfdi.setFecha(dateHelper.getStringFromFecha(facturaDto.getFechaActualizacion(),
				FacturaConstants.FACTURA_DATE_FORMAT));
		return cfdi;
	}

	@Override
	public Concepto cfdiConcepto(ConceptoDto dto) {
		Concepto conpeto = delegate.cfdiConcepto(dto);
		conpeto.setImporte(numberHelper.assignPrecision(conpeto.getImporte(), Constants.DEFAULT_SCALE));
		conpeto.setValorUnitario(numberHelper.assignPrecision(conpeto.getValorUnitario(), Constants.DEFAULT_SCALE));
		if (conpeto.getDescuento() != null) {
			conpeto.setDescuento(numberHelper.assignPrecision(conpeto.getDescuento(), Constants.DEFAULT_SCALE));
		}
		Impuesto impuesto = new Impuesto();
		for (ImpuestoDto impuestoDto : dto.getImpuestos()) {
			Translado traslado = delegate.cfdiImpuesto(impuestoDto);
			traslado.setImporte(numberHelper.assignPrecision(traslado.getImporte(), Constants.DEFAULT_SCALE));
			traslado.setBase(numberHelper.assignPrecision(traslado.getBase(), Constants.DEFAULT_SCALE));
			traslado.setTasaOCuota(String.format("%05f", Double.valueOf(traslado.getTasaOCuota())));
			impuesto.getTranslados().add(traslado);
		}
		conpeto.setImpuestos(impuesto);
		return conpeto;
	}

	@Override
	public ComplementoPago complementoComponente(FacturaDto facturaDto, PagoDto pagoDto) {
		ComplementoPago complementoPago = delegate.complementoComponente(facturaDto, pagoDto);
		complementoPago.setMonto(String.format("%.2f%n", pagoDto.getMonto()));
		complementoPago.getComplementoDocRelacionado().setImpPagado(String.format("%.2f%n", pagoDto.getMonto()));
		complementoPago.setFechaPago(
				dateHelper.getStringFromFecha(pagoDto.getFechaPago(), FacturaConstants.FACTURA_DATE_FORMAT));
		return complementoPago;
	}
	
}
