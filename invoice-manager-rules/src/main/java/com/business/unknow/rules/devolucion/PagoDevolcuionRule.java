package com.business.unknow.rules.devolucion;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DevolucionSuite;

@Rule(name = DevolucionSuite.PAGO_DEVOLUCION_RULE, description = DevolucionSuite.PAGO_DEVOLUCION)
public class PagoDevolcuionRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getCurrentPago() == null && fc.getCurrentPago().getStatusPago().equals("DEVOLUCION");
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DevolucionSuite.PAGO_DEVOLUCION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DevolucionSuite.DEVOLUCION_SUITE));
		fc.setValid(false);
	}

}
