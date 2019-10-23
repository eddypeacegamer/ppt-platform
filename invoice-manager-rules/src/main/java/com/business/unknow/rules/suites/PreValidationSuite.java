package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.prevalidations.FacturaPadreComplementoRule;

public class PreValidationSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public PreValidationSuite() {
		rules.register(new FacturaPadreComplementoRule());

	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}
