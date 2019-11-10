package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.CancelacionSuite;
import com.business.unknow.rules.suites.ComplementoSuite;
import com.business.unknow.rules.suites.FacturarSuite;
import com.business.unknow.services.services.translators.FacturaTranslator;
import com.business.unknow.services.services.translators.TimbradoService;

@Service
public class FacturaServiceEvaluator extends AbstractFacturaServiceEvaluator {

	@Autowired
	private ComplementoSuite complementoSuite;

	@Autowired
	private CancelacionSuite cancelacionSuite;

	@Autowired
	private FacturarSuite FacturarSuite;

	@Autowired
	private FacturaTranslator facturaTranslator;

	@Autowired
	private TimbradoService timbradoService;

	@Autowired
	private RulesEngine rulesEngine;

	public FacturaContext facturaComplementoValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(complementoSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);

		return facturaContext;
	}

	public FacturaContext facturaCancelacionValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(cancelacionSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		timbradoService.cancelarFactura(facturaContext);
		return facturaContext;
	}

	public FacturaContext facturaTimbradoValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(FacturarSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		facturaTranslator.translateFactura(facturaContext);
		if (facturaContext.getTipoFactura().equals(MetodosPagoEnum.PUE.getNombre())) {
			return timbradoService.timbrarFactura(facturaContext);
		} else {
			return timbradoService.timbraComplemento(facturaContext);
		}
	}

}
