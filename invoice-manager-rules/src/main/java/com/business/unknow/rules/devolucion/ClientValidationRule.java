package com.business.unknow.rules.devolucion;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DevolucionSuite;

@Rule(name = DevolucionSuite.CLIENT_VALIDATION_RULE, description = DevolucionSuite.CLIENT_VALIDATION)
public class ClientValidationRule {
	
	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return (fc == null|| fc.getClientDto() == null
				|| !fc.getClientDto().getActivo());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DevolucionSuite.CLIENT_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DevolucionSuite.DEVOLUCION_SUITE));
		fc.setValid(false);
	}
}
