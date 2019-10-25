package com.business.unknow.rules.prevalidations;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.PagoStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants;

@Rule(name = Constants.Prevalidations.FACTURA_PADRE_STATUS, description = Constants.Prevalidations.FACTURA_PADRE_STATUS_RULE)
public class FacturaPadreStatusRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		if (fc.getFacturaDto().getStatusPago().equals(FacturaStatusEnum.TIMBRADA.getValor())
				|| fc.getFacturaDto().getStatusPago().equals(PagoStatusEnum.PARCIALMENTE_PAGADA.getValor())
				|| fc.getFacturaDto().getStatusPago().equals(PagoStatusEnum.SIN_PAGAR.getValor())
				|| fc.getFacturaDto().getStatusDevolucion().equals(DevolucionStatusEnum.SIN_DEVOLVER.getValor())
				|| fc.getFacturaDto().getStatusDevolucion()
						.equals(DevolucionStatusEnum.PARCIALMENTE_DEVUELTA.getValor()))
			;

		return true;
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Constants.Prevalidations.FACTURA_PADRE_STATUS_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Constants.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
