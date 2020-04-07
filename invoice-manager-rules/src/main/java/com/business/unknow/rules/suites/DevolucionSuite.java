package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.devolucion.FacturaPpdStatusDevolucionRule;
import com.business.unknow.rules.devolucion.FacturaPueStatusDevolucionRule;

public class DevolucionSuite  implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public DevolucionSuite() {
		rules.register(new FacturaPpdStatusDevolucionRule());
		rules.register(new FacturaPueStatusDevolucionRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}