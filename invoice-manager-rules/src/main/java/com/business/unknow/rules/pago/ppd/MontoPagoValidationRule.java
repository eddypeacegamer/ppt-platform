package com.business.unknow.rules.pago.ppd;

import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.PagoPpdSuite;

@Rule(name = PagoPpdSuite.MONTO_PAGO_VALIDATION, description = PagoPpdSuite.MONTO_PAGO_VALIDATION)
public class MontoPagoValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		Optional<PagoDto> pagoDto = fc.getPagos().stream().findFirst();
		return !pagoDto.isPresent() || fc.getPagoCredito() == null
				|| pagoDto.get().getMonto() > fc.getPagoCredito().getMonto();
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(PagoPpdSuite.MONTO_PAGO_VALIDATION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", PagoPpdSuite.PAGO_PPD_SUITE));
		fc.setValid(false);
	}
}
