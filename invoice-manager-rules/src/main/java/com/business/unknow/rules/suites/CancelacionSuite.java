package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.cancelar.CancelarStatusValidationRule;

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
