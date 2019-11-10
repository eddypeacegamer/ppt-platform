package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.facturar.FacturaDatosValidationRule;
import com.business.unknow.rules.facturar.FacturaStatusRule;

public class FacturarSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public FacturarSuite() {
		rules.register(new FacturaStatusRule());
		rules.register(new FacturaDatosValidationRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}