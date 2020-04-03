package com.business.unknow.rules.payments;

import java.util.List;
import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

@Rule(name = PaymentsSuite.CREATE_CREDIT_VALIDATION_RULE, description = PaymentsSuite.CREATE_CREDIT_VALIDATION)
public class CreateCreditValidationRule {
	
	private static final Logger log  = LoggerFactory.getLogger(CreateCreditValidationRule.class); 

	@Condition
	public boolean condition(@Fact("currentPayment") PagoDto currentPayment,
			@Fact("payments") List<PagoDto> payments) {
		
		if(payments!= null && currentPayment!=null) {
			Optional<PagoDto> existingCredit = payments.stream().filter(p->FormaPagoEnum.CREDITO.getPagoValue().equals(p.getFormaPago())).findAny(); 
			return existingCredit.isPresent() && FormaPagoEnum.CREDITO.getPagoValue().equals(currentPayment.getFormaPago());	
		}else {
			log.error("One or more missing facts on {} rule",CreateCreditValidationRule.class.getName());
			return true;
		}
		 
	}

	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add("No se puede cargar mas de un pago a credito por factura.");
	}
}
