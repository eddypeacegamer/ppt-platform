package com.business.unknow.rules.payments;

import java.math.BigDecimal;
import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

@Rule(name = PaymentsSuite.ZERO_AMMOUNT_VALIDATION_RULE, description = PaymentsSuite.ZERO_AMMOUNT_VALIDATION_RULE_DESC)
public class ZeroAmmountValidationRule {
	
	private static final Logger log  = LoggerFactory.getLogger(ZeroAmmountValidationRule.class); 

	@Condition
	public boolean condition(@Fact("payment") PagoDto currentPayment) {
		
		if(currentPayment!=null) {
			
			if(BigDecimal.ZERO.compareTo(currentPayment.getMonto())<0) {
				for (PagoFacturaDto fact : currentPayment.getFacturas()) {
					if(BigDecimal.ZERO.compareTo(fact.getMonto())>=0) {
						return true;
					}
				}
				return false;
			}else {
				return true;
			}	
		}else {
			log.error("One or more missing facts on {} rule",ZeroAmmountValidationRule.class.getName());
			return true;
		}
		 
	}

	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add(PaymentsSuite.ZERO_AMMOUNT_VALIDATION_RULE_DESC);
	}
}
