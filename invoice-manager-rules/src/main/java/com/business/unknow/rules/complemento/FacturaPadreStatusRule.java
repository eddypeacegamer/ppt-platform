package com.business.unknow.rules.complemento;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Prevalidations;

@Rule(name = Prevalidations.FACTURA_PADRE_STATUS, description = Prevalidations.FACTURA_PADRE_STATUS_RULE)
public class FacturaPadreStatusRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return  !fc.getFacturaPadreDto().getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor()) 
				 || fc.getFacturaPadreDto().getStatusDevolucion().equals(DevolucionStatusEnum.DEVUELTA.getValor());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Prevalidations.FACTURA_PADRE_STATUS_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Prevalidations.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
