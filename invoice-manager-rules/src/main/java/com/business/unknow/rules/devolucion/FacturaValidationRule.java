package com.business.unknow.rules.devolucion;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DevolucionSuite;

@Rule(name = DevolucionSuite.FACTURA_VALIDATION_RULE, description = DevolucionSuite.FACTURA_VALIDATION)
public class FacturaValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return (fc.getFacturaDto() == null|| !fc.getFacturaDto().getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor()));
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DevolucionSuite.FACTURA_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DevolucionSuite.DEVOLUCION_SUITE));
		fc.setValid(false);
	}
}
