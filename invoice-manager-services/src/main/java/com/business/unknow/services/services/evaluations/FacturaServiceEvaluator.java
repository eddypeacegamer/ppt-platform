package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.PreValidationSuite;

@Service
public class FacturaServiceEvaluator extends AbstractFacturaServiceEvaluator {

	@Autowired
	private PreValidationSuite preValidationSuite;

	@Autowired
	private RulesEngine rulesEngine;

	public FacturaContext facruraContexctValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(preValidationSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return facturaContext;

	}

}
