package com.business.unknow.rules.complemento;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Prevalidations;

@Rule(name = Prevalidations.FACTURA_PADRE_COMPLEMENTO_RULE, description = Prevalidations.FACTURA_PADRE_COMPLEMENTO)
public class FacturaPadreComplementoRule extends AbstractPrevalidations {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return (fc.getFacturaPadreDto() == null|| fc.getFacturaDto() == null
				|| fc.getFacturaPadreDto().getFechaTimbrado() == null
				|| !fc.getFacturaPadreDto().getCfdi().getMetodoPago().equals(MetodosPagoEnum.PPD.getNombre())
				|| fc.getFacturaPadreDto().getUuid() == null);
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Prevalidations.FACTURA_PADRE_COMPLEMENTO_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Prevalidations.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
