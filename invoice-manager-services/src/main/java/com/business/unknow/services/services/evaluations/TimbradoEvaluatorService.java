package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.TimbradoSuite;
import com.business.unknow.rules.suites.facturas.CancelacionSuite;

@Service
public class TimbradoEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	private CancelacionSuite cancelacionSuite;

	@Autowired
	private TimbradoSuite facturarSuite;

	@Autowired
	private RulesEngine rulesEngine;

	public void facturaCancelacionValidation(FacturaContext facturaContext)
			throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(cancelacionSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		
	}

	public void facturaTimbradoValidation(FacturaContext facturaContext)
			throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(facturarSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
	}

}
