package com.business.unknow.rules.cancelar;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.CancelacionSuite;

@Rule(name = CancelacionSuite.CANCELAR_STATUS_VALIDATION, description = CancelacionSuite.CANCELAR_STATUS_VALIDATION_RULE)
public class CancelarStatusValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return !fc.getFacturaDto().getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(CancelacionSuite.CANCELAR_SUITE);
		fc.setSuiteError(String.format("Error durante : %s", CancelacionSuite.CANCELAR_STATUS_VALIDATION_RULE_DESC));
		fc.setValid(false);
	}

}
