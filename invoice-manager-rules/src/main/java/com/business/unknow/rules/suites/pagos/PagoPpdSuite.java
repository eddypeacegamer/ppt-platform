package com.business.unknow.rules.suites.pagos;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.pago.create.ppd.MontoPagoValidationRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class PagoPpdSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public PagoPpdSuite() {
		rules.register(new MontoPagoValidationRule());
		
	}

	@Override
	public Rules getSuite() {
		return rules;
	}

}