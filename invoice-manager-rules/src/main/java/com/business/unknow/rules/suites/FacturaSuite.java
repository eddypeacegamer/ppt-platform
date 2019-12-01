package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.factura.EmisorValidationRule;
import com.business.unknow.rules.factura.ReceptorValidationRule;


public class FacturaSuite implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public FacturaSuite() {
		rules.register(new EmisorValidationRule());
		rules.register(new ReceptorValidationRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}

}
