package com.business.unknow.rules.timbrado;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Timbrado;

@Rule(name = Timbrado.TIMBRADO_DATOS_VALIDATION, description = Timbrado.TIMBRADO_DATOS_VALIDATION_RULE)
public class FacturaDatosValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		if (fc.getFacturaDto().getUuid()!=null || fc.getFacturaDto().getFechaTimbrado() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Timbrado.TIMBRADO_DATOS_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Timbrado.TIMBRADO_SUITE));
		fc.setValid(false);
	}
}
