package com.business.unknow.rules.suites.facturas;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.complemento.FacturaPadreComplementoRule;
import com.business.unknow.rules.complemento.FacturaPadreStatusRule;
import com.business.unknow.rules.suites.InvoiceManagerSuite;

public class ComplementoSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public ComplementoSuite() {
		rules.register(new FacturaPadreComplementoRule());
		rules.register(new FacturaPadreStatusRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}
