package com.business.unknow.rules.pago.create.pue;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.PagoPueSuite;

@Rule(name = PagoPueSuite.PAYMENT_CREDIT_PUE_RULE, description = PagoPueSuite.PAYMENT_CREDIT_PUE)
public class PaymantPueWithCreditRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getPagoCredito() != null && !fc.getPagos().isEmpty() && (fc.getPagos().size() > 2
				|| fc.getCurrentPago().getFormaPago().equals(fc.getPagoCredito().getFormaPago()));
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(PagoPueSuite.PAYMENT_CREDIT_PUE_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", PagoPueSuite.PAGO_PUE_SUITE));
		fc.setValid(false);
	}

}
