package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.timbrado.FacturaDatosValidationRule;
import com.business.unknow.rules.timbrado.FacturaStatusRule;

public class TimbradoSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public TimbradoSuite() {
		rules.register(new FacturaStatusRule());
		rules.register(new FacturaDatosValidationRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}