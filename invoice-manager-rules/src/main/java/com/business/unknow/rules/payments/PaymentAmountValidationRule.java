package com.business.unknow.rules.payments;

import java.math.BigDecimal;
import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

@Rule(name = PaymentsSuite.MONTO_PAGO_VALIDATION_RULE, description = PaymentsSuite.MONTO_PAGO_VALIDATION)
public class PaymentAmountValidationRule {
	
	private static final Logger log  = LoggerFactory.getLogger(PaymentAmountValidationRule.class); 

	@Condition
	public boolean condition(
			@Fact("payment") PagoDto currentPayment,
			@Fact("facturas") List<FacturaDto> facturas) {
		if(facturas!=null && currentPayment!=null) {
			BigDecimal total = currentPayment.getFacturas().stream().map(PagoFacturaDto:: getMonto).reduce(BigDecimal.ZERO,(p1,p2)->p1.add(p2));
			return total.subtract(currentPayment.getMonto()).setScale(1, BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ZERO)!=0;
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
