package com.business.unknow.rules.pago.create.pue;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.BancoEnum;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.PagoPueSuite;

@Rule(name = PagoPueSuite.PAYMENT_PUE_NOT_CREDIT_RULE, description = PagoPueSuite.PAYMENT_PUE_NOT_CREDIT)
public class PaymentPueNotCreditRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return  !fc.getCurrentPago().getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())
				&& fc.getCurrentPago().getBanco().equals(BancoEnum.NO_APLICA.getDescripcion());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(PagoPueSuite.PAYMENT_PUE_NOT_CREDIT_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", PagoPueSuite.PAGO_PUE_SUITE));
		fc.setValid(false);
	}
}
