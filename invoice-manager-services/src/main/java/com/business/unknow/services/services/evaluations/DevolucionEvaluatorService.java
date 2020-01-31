package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.DevolucionSuite;

@Service
public class DevolucionEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	private DevolucionSuite devolucionSuite;


	public void devolucionPpdValidation(FacturaContext context)
			throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(devolucionSuite.getSuite(), facts);
		validateFacturaContext(context);
		
	}

	public void devolucionPueValidation(FacturaContext context)
			throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(devolucionSuite.getSuite(), facts);
		validateFacturaContext(context);
	}

}
