/**
 * 
 */
package com.business.unknow.rules.payments;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

/**
 * @author ralfdemoledor
 *
 */
@Rule(name = PaymentsSuite.CREDIT_PAYMENT_VALIDATION_RULE, description = PaymentsSuite.CREDIT_PAYMENT_VALIDATION_RULE_DESC)
public class CreditPaymentRule {
	
	@Condition
	public boolean condition(@Fact("payment") PagoDto currentPayment,
			@Fact("facturas") List<FacturaDto> facturas) {	
		
		for (FacturaDto facturaDto : facturas) {
			if(MetodosPagoEnum.PPD.name().equals(facturaDto.getMetodoPago()) &&  FormaPagoEnum.CREDITO.name().equals(currentPayment.getFormaPago())){
				return true;
			}
		}
		return false;
	}
	
	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add(PaymentsSuite.CREDIT_PAYMENT_VALIDATION_RULE_DESC);
	}

}
