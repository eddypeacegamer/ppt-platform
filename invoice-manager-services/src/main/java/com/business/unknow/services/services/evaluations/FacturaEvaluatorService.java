package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.facturas.FacturaSuite;
import com.business.unknow.rules.suites.facturas.FacturaValidationSuite;

@Service
public class FacturaEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	private FacturaSuite facturaSuite;
	
	
	@Autowired
	private FacturaValidationSuite facturaValidationSuite;

	public FacturaContext facturaEvaluation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(facturaSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return facturaContext;
	}
	
	public void facturaStatusValidation(FacturaDto facturaDto){
		Facts facts = new Facts();
		facts.put("factura", facturaDto);
		rulesEngine.fire(facturaValidationSuite.getSuite(), facts);
	}

}
