package com.business.unknow.rules.factura;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.FacturaSuite;
import com.business.unknow.rules.complemento.AbstractPrevalidations;

@Rule(name = FacturaSuite.RECEPTOR_VALIDATION_RULE, description = FacturaSuite.RECEPTOR_VALIDATION)
public class ReceptorValidationRule extends AbstractPrevalidations {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getClientDto() != null && !fc.getClientDto().getActivo();

	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(FacturaSuite.RECEPTOR_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", FacturaSuite.FACTURA_SUITE));
		fc.setValid(false);
	}
}
