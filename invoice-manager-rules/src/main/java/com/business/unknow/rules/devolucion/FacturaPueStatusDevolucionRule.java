package com.business.unknow.rules.devolucion;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.DevolucionStatusEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.DevolucionSuite;

@Rule(name = DevolucionSuite.FACTURA_PUE_STATUS_DEVOLUCION_RULE, description = DevolucionSuite.FACTURA_PUE_STATUS_DEVOLUCION)
public class FacturaPueStatusDevolucionRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		return (fc.getFacturaDto() != null
				&& fc.getFacturaDto().getTipoDocumento().equals(TipoDocumentoEnum.FACRTURA.getDescripcion())
				&& fc.getFacturaDto().getStatusDevolucion().equals(DevolucionStatusEnum.DEVUELTA.getValor()));
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DevolucionSuite.FACTURA_PUE_STATUS_DEVOLUCION_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DevolucionSuite.DEVOLUCION_SUITE));
		fc.setValid(false);
	}

}
