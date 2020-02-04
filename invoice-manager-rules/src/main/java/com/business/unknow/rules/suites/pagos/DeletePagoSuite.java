package com.business.unknow.rules.suites.pagos;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.pago.delete.DeleteCreditPaymantRule;
import com.business.unknow.rules.pago.delete.DeletePpdPaymentRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class DeletePagoSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public DeletePagoSuite() {
		rules.register(new DeletePpdPaymentRule());
		rules.register(new DeleteCreditPaymantRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}
