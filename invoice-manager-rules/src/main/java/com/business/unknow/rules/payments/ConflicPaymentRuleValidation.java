/**
 * 
 */
package com.business.unknow.rules.payments;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

/**
 * @author ralfdemoledor
 *
 */
@Rule(name = PaymentsSuite.CONFLICT_PAYMENT_VALIDATION_RULE, description = PaymentsSuite.CONFLICT_PAYMENT_VALIDATION_RULE_DESC)
public class ConflicPaymentRuleValidation {
	
	private static final Logger log  = LoggerFactory.getLogger(ConflicPaymentRuleValidation.class); 
	
	@Condition
	public boolean condition(@Fact("payment") PagoDto currentPayment,
			@Fact("dbPayment") PagoDto dbPayment,@Fact("results") List<String>results) {	
		
		if(currentPayment.getRevisor1()!=null) {
			if(currentPayment.getRevisor1().equalsIgnoreCase(dbPayment.getSolicitante()) || currentPayment.getRevisor1().equalsIgnoreCase(dbPayment.getRevisor2())) {
				results.add("Los pagos deben ser validados por al menos dos usuarios diferentes");
				return true;
			}
		}
		
		if(currentPayment.getRevisor2()!=null) {
			if(currentPayment.getRevisor2().equalsIgnoreCase(dbPayment.getSolicitante()) || currentPayment.getRevisor2().equalsIgnoreCase(dbPayment.getRevisor1())) {
				results.add("Los pagos deven ser validados por al menos dos usuarios diferentes");
				return true;
			}
		}
		
		if (dbPayment.getRevision1() && !currentPayment.getRevision2()&&!currentPayment.getRevision1().equals(dbPayment.getRevision1())) {
			results.add("Ya se realizo la primera validacion.");
			return true;
		}
		if (!dbPayment.getRevision1() && currentPayment.getRevision2()) {
			results.add("Incongruencia en la validacion de pagos, el segundo pago no puede ser validado si el primer pago ya fue validado");
			return true;
		}
		if(currentPayment.getRevision1() && currentPayment.getRevision2() && !dbPayment.getRevision1() && !dbPayment.getRevision2()) {
			results.add("Inconsistencia en los estatus de validacion, un pago no puede ser validado doblemente");
			return true;
		}
		
		if( dbPayment.getId()!=null && currentPayment.getRevisor1() == null && currentPayment.getRevision2() == null) {
			results.add("Inconsistencia en la informacion de revisores, un pago no puede ser validado sin revisores");
			log.warn("Inconsistencia en la informacion de revisores, un pago no puede ser validado sin revisores");
			return true;
		}
		if(dbPayment.getId()!=null && currentPayment.getMonto().compareTo(dbPayment.getMonto())!= 0) {
			results.add("Inconsistencia en montos pago,Intento de modificacion de monto pagos");
			log.warn("Inconsistencia en montos pago,Intento de modificacion de monto pagos");
			return true;
		}
		if(!currentPayment.getCuenta().equalsIgnoreCase(dbPayment.getCuenta()) && dbPayment.getId()!=null) {
			results.add("Inconsistencia en la cuenta de pago,intento de falsificacion de cuenta de pago");
			log.warn("Inconsistencia en la cuenta de pago,intento de falsificacion de cuenta de pago");
			return true;
		}
		return false;
	}
	
	@Action
	public void execute(@Fact("results") List<String>results) {
		log.info("ConflicPaymentRuleValidation has been trigered");
	}

}
