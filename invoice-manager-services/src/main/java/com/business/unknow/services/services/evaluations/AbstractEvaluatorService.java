package com.business.unknow.services.services.evaluations;

import org.apache.http.HttpStatus;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.services.executor.AbstractExecutorService;;

public class AbstractEvaluatorService extends AbstractExecutorService {

	@Autowired
	protected RulesEngine rulesEngine;
	
	protected void validateFacturaContext(FacturaContext facturaContexrt) throws InvoiceManagerException {
		if (!facturaContexrt.isValid()) {
			throw new InvoiceManagerException(facturaContexrt.getRuleErrorDesc(), facturaContexrt.getSuiteError(),
					HttpStatus.SC_BAD_REQUEST);
		}
	}

}
