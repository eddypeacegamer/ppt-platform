package com.business.unknow.rules.pago.delete;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DeletePagoSuite;

@Rule(name = DeletePagoSuite.DELETE_CREDIT_PAYMANT_RULE, description = DeletePagoSuite.DELETE_CREDIT_PAYMANT)
public class DeleteCreditPaymantRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getCurrentPago().getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue()) && fc.getPagos() != null
				&& fc.getPagos().size() > 1;
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DeletePagoSuite.DELETE_CREDIT_PAYMANT_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DeletePagoSuite.DELETE_PAGO_SUITE));
		fc.setValid(false);
	}
}
