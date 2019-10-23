package com.business.unknow.rules.prevalidations;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants;

@Rule(name = Constants.Prevalidations.FACTURA_PADRE_COMPLEMENTO_RULE, description = Constants.Prevalidations.FACTURA_PADRE_COMPLEMENTO)
public class FacturaPadreComplementoRule extends AbstractPrevalidations {

	@Condition
	public boolean firstNameCondition(@Fact("facturaContext") FacturaContext fc) {
		return (fc.getFacturaDto() == null || fc.getFacturaDto().getFechaTimbrado() == null
				|| !fc.getFacturaDto().getMetodoPago().equals(MetodosPagoEnum.PPT.getNombre())
				|| fc.getFacturaDto().getUuid() == null || fc.getComplementoActual() == null);
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Constants.Prevalidations.FACTURA_PADRE_COMPLEMENTO_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Constants.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
