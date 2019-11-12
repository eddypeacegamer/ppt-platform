package com.business.unknow.services.services.evaluations;

import org.apache.http.HttpStatus;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;

public class AbstractFacturaServiceEvaluator {

	protected void validateFacturaContext(FacturaContext facturaContexrt) throws InvoiceManagerException {
		if (!facturaContexrt.isValid()) {
			throw new InvoiceManagerException(facturaContexrt.getRuleErrorDesc(),facturaContexrt.getSuiteError(),
					HttpStatus.SC_BAD_REQUEST);
		}
	}

}
