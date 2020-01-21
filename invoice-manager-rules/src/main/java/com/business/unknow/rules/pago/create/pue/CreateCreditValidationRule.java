package com.business.unknow.rules.pago.create.pue;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.PagoPueSuite;;

@Rule(name = PagoPueSuite.CREATE_CREDIT_VALIDATION_RULE, description = PagoPueSuite.CREATE_CREDIT_VALIDATION)
public class CreateCreditValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getPagoCredito() == null
				&& fc.getCurrentPago().getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(PagoPueSuite.CREATE_CREDIT_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", PagoPueSuite.PAGO_PUE_SUITE));
		fc.setValid(false);
	}
}
