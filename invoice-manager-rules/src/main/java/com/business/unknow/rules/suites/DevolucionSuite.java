package com.business.unknow.rules.suites;

import org.jeasy.rules.api.Rules;

import com.business.unknow.rules.devolucion.ClientValidationRule;
import com.business.unknow.rules.devolucion.FacturaPpdStatusDevolucionRule;
import com.business.unknow.rules.devolucion.FacturaPueStatusDevolucionRule;
import com.business.unknow.rules.devolucion.FacturaValidationRule;
import com.business.unknow.rules.devolucion.PagoDevolcuionRule;

public class DevolucionSuite  implements InvoiceManagerSuite {

	private Rules rules = new Rules();

	public DevolucionSuite() {
		rules.register(new ClientValidationRule());
		rules.register(new FacturaValidationRule());
		rules.register(new FacturaPpdStatusDevolucionRule());
		rules.register(new FacturaPueStatusDevolucionRule());
		rules.register(new PagoDevolcuionRule());
	}

	@Override
	public Rules getSuite() {
		return rules;
	}
}