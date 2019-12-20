package com.business.unknow.rules.suites.facturas;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.cancelar.CancelarStatusValidationRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class CancelacionSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public CancelacionSuite() {
		rules.register(new CancelarStatusValidationRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}

}
