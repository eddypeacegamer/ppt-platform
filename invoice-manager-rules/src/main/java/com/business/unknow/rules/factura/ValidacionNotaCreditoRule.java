package com.business.unknow.rules.factura;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.rules.common.Constants.FacturaValidationSuite;

@Rule(name = FacturaValidationSuite.FACTURA_VALIDATION_NOTA_CREDITO_RULE, description = FacturaValidationSuite.FACTURA_VALIDATION_NOTA_CREDITO_RULE_DESC)
public class ValidacionNotaCreditoRule {

	@Condition
	public boolean condition(@Fact("factura") FacturaDto facturaDto) {
		if ((FacturaStatusEnum.VALIDACION_OPERACIONES.getValor().equals(facturaDto.getStatusFactura())
				|| FacturaStatusEnum.VALIDACION_TESORERIA.getValor().equals(facturaDto.getStatusFactura())
				|| FacturaStatusEnum.RECHAZO_TESORERIA.getValor().equals(facturaDto.getStatusFactura()))
				&& facturaDto.getTipoDocumento().equals(TipoDocumentoEnum.NOTA_CREDITO.getDescripcion())) {
			return true;
		}
		return false;

	}

	@Action
	public void execute(@Fact("factura") FacturaDto facturaDto) {
		if (facturaDto.getValidacionOper() && facturaDto.getValidacionTeso()) {
			facturaDto.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR.getValor());
		} else if (facturaDto.getValidacionOper() && !facturaDto.getValidacionTeso()) {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_TESORERIA.getValor());
		} else if (!facturaDto.getValidacionOper() && facturaDto.getValidacionTeso()) {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
		} else {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
		}
	}
}
