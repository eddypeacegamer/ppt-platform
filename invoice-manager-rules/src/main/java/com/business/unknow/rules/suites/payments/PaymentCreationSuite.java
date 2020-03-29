package com.business.unknow.rules.suites.payments;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.payments.CreateCreditValidationRule;
import com.business.unknow.rules.payments.PaymentAmountValidationRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class PaymentCreationSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public PaymentCreationSuite() {
		rules.register(new PaymentAmountValidationRule());
		rules.register(new CreateCreditValidationRule());
		
	}

	@Override
	public Rules getSuite() {
		return rules;
	}

}
