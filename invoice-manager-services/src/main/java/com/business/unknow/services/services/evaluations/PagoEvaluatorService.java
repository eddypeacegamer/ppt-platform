package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.pagos.DeletePagoSuite;
import com.business.unknow.rules.suites.pagos.PagoPpdSuite;
import com.business.unknow.rules.suites.pagos.PagoPueSuite;

@Service
public class PagoEvaluatorService extends AbstractEvaluatorService {

	@Autowired
	private PagoPpdSuite pagoPpdSuite;

	@Autowired
	private DeletePagoSuite deletePagoSuite;

	@Autowired
	private PagoPueSuite pagoPueSuite;


	public void deletePagoPpdValidation(FacturaContext context) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
		
	}

	public void deletePagoPueValidation(FacturaContext context) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
	}

	public FacturaContext deleteComplementoValidation(FacturaContext context) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", context);
		rulesEngine.fire(deletePagoSuite.getSuite(), facts);
		validateFacturaContext(context);
		return context;
	}

	public void validatePagoPpdCreation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPpdSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
	}

	public void validatePagoPueCreation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPueSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
	}
	
	public void validatePagoCreator(PagoDto currentPagoDto,PagoDto pagoDto) throws InvoiceManagerException {
		if (pagoDto.getUltimoUsuario().equalsIgnoreCase(currentPagoDto.getUltimoUsuario())
				|| pagoDto.getCreateUser().equalsIgnoreCase(currentPagoDto.getUltimoUsuario())) {
			throw new InvoiceManagerException("Mismo usuario no puede actualizar el pago",
					"La actualizacion del pago no puede ser realizada por el mismo usuario de manera consecutiva",
					Constants.HTTP_SSTATUS_CONFLICT);
		}
	}
	
	

}
