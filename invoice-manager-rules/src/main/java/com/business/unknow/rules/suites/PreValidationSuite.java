package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.prevalidations.FacturaPadreComplementoRule;
import com.business.unknow.rules.prevalidations.FacturaPadrePagosRule;
import com.business.unknow.rules.prevalidations.FacturaPadreStatusRule;

public class PreValidationSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public PreValidationSuite() {
		rules.register(new FacturaPadreComplementoRule());
		rules.register(new FacturaPadrePagosRule());
		rules.register(new FacturaPadreStatusRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}
