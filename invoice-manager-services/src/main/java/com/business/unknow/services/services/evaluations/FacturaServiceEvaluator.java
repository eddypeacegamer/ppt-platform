package com.business.unknow.services.services.evaluations;

import org.apache.http.HttpStatus;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.CancelacionSuite;
import com.business.unknow.rules.suites.ComplementoSuite;
import com.business.unknow.rules.suites.FacturarSuite;
import com.business.unknow.services.services.executor.SwSapinsExecutorService;
import com.business.unknow.services.services.translators.FacturaTranslator;

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
	private SwSapinsExecutorService swSapinsExecutorService;

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
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.cancelarFactura(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.SC_BAD_REQUEST);
		}
		updateCanceladoValues(facturaContext);
		return facturaContext;
	}

	public FacturaContext facturaTimbradoValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(FacturarSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		if (facturaContext.getTipoDocumento().equals(TipoDocumentoEnum.FACRTURA.getDescripcion())) {
			facturaContext = facturaTranslator.translateFactura(facturaContext);
			switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
			case SW_SAPIENS:
				swSapinsExecutorService.stamp(facturaContext);
				break;
			default:
				throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
						HttpStatus.SC_BAD_REQUEST);
			}
		} else {
			facturaContext = facturaTranslator.translateComplemento(facturaContext);
			switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
			case SW_SAPIENS:
				 swSapinsExecutorService.stamp(facturaContext);
				 break;
			default:
				throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
						HttpStatus.SC_BAD_REQUEST);
			}
		}
		updateFacturaAndCfdiValues(facturaContext);
		return facturaContext;
	}

}
