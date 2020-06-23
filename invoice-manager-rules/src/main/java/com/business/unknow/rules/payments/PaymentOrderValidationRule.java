package com.business.unknow.rules.payments;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

@Rule(name = PaymentsSuite.ORDER_PAYMENT_VALIDATION_RULE, description = PaymentsSuite.ORDER_PAYMENT_VALIDATION_RULE_DESC)
public class PaymentOrderValidationRule {
	
	@Condition
	public boolean condition(@Fact("payment") PagoDto currentPayment,
			@Fact("dbPayment") PagoDto dbPayment) {	
		return !dbPayment.getRevision1() && currentPayment.getRevision2();
		 
	}
	
	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add(PaymentsSuite.ORDER_PAYMENT_VALIDATION_RULE_DESC);
	}

}
