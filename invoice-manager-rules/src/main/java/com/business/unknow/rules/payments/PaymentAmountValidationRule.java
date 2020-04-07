package com.business.unknow.rules.payments;

import java.math.BigDecimal;
import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

@Rule(name = PaymentsSuite.MONTO_PAGO_VALIDATION_RULE, description = PaymentsSuite.MONTO_PAGO_VALIDATION)
public class PaymentAmountValidationRule {
	
	private static final Logger log  = LoggerFactory.getLogger(PaymentAmountValidationRule.class); 

	@Condition
	public boolean condition(
			@Fact("currentPayment") PagoDto currentPayment,
			@Fact("cfdi") CfdiDto cfdi, 
			@Fact("payments") List<PagoDto> payments) {
		
		if(cfdi!=null && payments!= null && currentPayment!=null) {
			BigDecimal paymentsSum = payments.stream()
					.filter(p->!FormaPagoEnum.CREDITO.getPagoValue().equalsIgnoreCase(p.getFormaPago())) // credit payments do not sum
					.map(p->p.getMonto()).reduce(BigDecimal.ZERO,(p1,p2)->p1.add(p2));
			return currentPayment.getMonto().compareTo(cfdi.getTotal().subtract(paymentsSum))>0 || BigDecimal.ZERO.compareTo(currentPayment.getMonto())>=0;
		}else {
			log.error("One or more missing facts on {} rule",PaymentAmountValidationRule.class.getName());
			return true;
		}
	}

	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add(PaymentsSuite.MONTO_PAGO_VALIDATION_RULE_DESC);
	}
}
