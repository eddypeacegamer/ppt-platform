package com.business.unknow.rules.pago.delete;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.DeletePagoSuite;

@Rule(name = DeletePagoSuite.DELETE_CREDIT_PAYMANT_RULE, description = DeletePagoSuite.DELETE_CREDIT_PAYMANT_RULE_DESC)
public class DeleteCreditPaymantRule {

	@Condition
	public boolean condition(@Fact("factura")FacturaDto invoice,@Fact("payment") PagoDto payment ) {
		return FormaPagoEnum.CREDITO.getPagoValue().equals(payment.getFormaPago()) && MetodosPagoEnum.PPD.getClave().equals(invoice.getMetodoPago());
	}

	@Action
	public void execute(@Fact("results") List<String> results) {
		results.add(DeletePagoSuite.DELETE_CREDIT_PAYMANT_RULE_DESC);
	}
}
