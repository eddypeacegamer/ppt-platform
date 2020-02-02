package com.business.unknow.services.services.evaluations;

import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
	
	public void validatePago(PagoDto currentPagoDto,PagoDto dbPagoDto) throws InvoiceManagerException {
		if(currentPagoDto.getRevisor1().equalsIgnoreCase(dbPagoDto.getSolicitante()) || currentPagoDto.getRevisor1().equalsIgnoreCase(dbPagoDto.getRevisor2())) {
			throw new InvoiceManagerException("Los pagos deven ser validados por al menos dos usuarios diferentes",
					"La actualizacion del pago no puede ser realizada por el mismo usuario de manera consecutiva",
					HttpStatus.CONFLICT.value());
		}
		if(currentPagoDto.getRevisor2().equalsIgnoreCase(dbPagoDto.getSolicitante()) || currentPagoDto.getRevisor2().equalsIgnoreCase(dbPagoDto.getRevisor1())) {
			throw new InvoiceManagerException("Los pagos deven ser validados por al menos dos usuarios diferentes",
					"La actualizacion del pago no puede ser realizada por el mismo usuario de manera consecutiva",
					HttpStatus.CONFLICT.value());
		}
		if(currentPagoDto.getRevision1() && currentPagoDto.getRevision2() && !dbPagoDto.getRevision1() && !dbPagoDto.getRevision2()) {
			throw new InvoiceManagerException("Inconsistencia en los estatus de validacion, un pago no puede ser validado doblemente",
					"La actualizacion del pago no puede ser realizada por el mismo usuario de manera consecutiva",
					HttpStatus.CONFLICT.value());
		}
		if( dbPagoDto.getId()!=null && currentPagoDto.getRevisor1() == null && currentPagoDto.getRevision2() == null) {
			throw new InvoiceManagerException("Inconsistencia en la informacion de revisores, un pago no puede ser validado sin revisores",
					"Intento de falsificacion de validacion de pagos",
					HttpStatus.CONFLICT.value());
		}
		if(!currentPagoDto.getMonto().equals(dbPagoDto.getMonto()) && dbPagoDto.getId()!=null) {
			throw new InvoiceManagerException("Inconsistencia en montos pago",
					"Intento de falsificacion de monto pagos",
					HttpStatus.CONFLICT.value());
		}
		if(!currentPagoDto.getCuenta().equalsIgnoreCase(dbPagoDto.getCuenta()) && dbPagoDto.getId()!=null) {
			throw new InvoiceManagerException("Inconsistencia en cunata de pago",
					"Intento de falsificacion de cuenta de pago",
					HttpStatus.CONFLICT.value());
		}
	}
	
	

}
