package com.business.unknow.rules.timbrado;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Timbrado;

@Rule(name = Timbrado.TIMBRADO_PADRE_STATUS, description = Timbrado.TIMBRADO_PADRE_STATUS_RULE)
public class FacturaPadreStatusValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return fc.getFacturaPadreDto() != null
				&& !fc.getFacturaPadreDto().getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor());
						
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Timbrado.TIMBRADO_STATUS_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Timbrado.TIMBRADO_SUITE));
		fc.setValid(false);
	}
}
