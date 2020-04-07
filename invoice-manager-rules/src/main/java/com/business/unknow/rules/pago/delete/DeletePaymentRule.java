package com.business.unknow.rules.pago.delete;

import java.math.BigDecimal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DeletePagoSuite;

@Rule(name = DeletePagoSuite.DELETE_PAYMENT_RULE, description = DeletePagoSuite.DELETE_PAYMENT)
@Deprecated
public class DeletePaymentRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())
				&& (fc.getPagoCredito() == null || fc.getPagoCredito().getMonto().compareTo(BigDecimal.ZERO) == 0);
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DeletePagoSuite.DELETE_PAYMENT_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DeletePagoSuite.DELETE_PAGO_SUITE));
		fc.setValid(false);
	}
}
