package com.business.unknow.rules.suites.payments;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.payments.ZeroAmmountValidationRule;
import com.business.unknow.rules.payments.CreditPaymentRule;
import com.business.unknow.rules.payments.PaymentAmountValidationRule;
import com.business.unknow.rules.payments.PaymentInvoiceStatusRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class PaymentCreationSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public PaymentCreationSuite() {
		rules.register(new PaymentAmountValidationRule());
		rules.register(new ZeroAmmountValidationRule());
		rules.register(new PaymentInvoiceStatusRule());
		rules.register(new CreditPaymentRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}

}
