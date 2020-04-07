package com.business.unknow.rules.suites.payments;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.pago.delete.DeleteCreditPaymantRule;
import com.business.unknow.rules.pago.delete.StatusDeletePaymentRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class DeletePagoSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public DeletePagoSuite() {
		rules.register(new StatusDeletePaymentRule());
		rules.register(new DeleteCreditPaymantRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}
