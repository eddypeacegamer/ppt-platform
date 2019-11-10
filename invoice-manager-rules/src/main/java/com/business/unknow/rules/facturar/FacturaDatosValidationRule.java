package com.business.unknow.rules.facturar;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.FacturaSuite;

@Rule(name = FacturaSuite.FACTURA_DATOS_VALIDATION, description = FacturaSuite.FACTURA_DATOS_VALIDATION_RULE)
public class FacturaDatosValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getFacturaDto().getFechaTimbrado() == null || fc.getFacturaDto().getUuid() == null
				|| (fc.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PUE.getNombre())
						&& fc.getFacturaDto().getFolioPadre() != null)
				|| (fc.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PPD.getNombre())
						&& fc.getFacturaDto().getFolioPadre() == null);
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(FacturaSuite.FACTURA_DATOS_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", FacturaSuite.FACTURAR_SUITE));
		fc.setValid(false);
	}
}
