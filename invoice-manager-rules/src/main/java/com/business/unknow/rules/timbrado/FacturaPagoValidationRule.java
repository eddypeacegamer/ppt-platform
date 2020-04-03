package com.business.unknow.rules.timbrado;

import java.math.BigDecimal;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.common.Constants.Timbrado;

@Rule(name = Timbrado.TIMBRADO_PAGO_VALIDATION, description = Timbrado.TIMBRADO_PAGO_VALIDATION_RULE)
public class FacturaPagoValidationRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		
		if(fc.getPagos()!=null && !fc.getPagos().isEmpty()) {
			BigDecimal paymentsAmmount = fc.getPagos().stream()
				.filter(p->RevisionPagosEnum.ACEPTADO.name().equals(p.getStatusPago()))
				.map(p->p.getMonto()).reduce(BigDecimal.ZERO,(p1,p2)->p1.add(p2));
			BigDecimal totalfactura = (fc.getFacturaPadreDto()!=null)? fc.getFacturaPadreDto().getCfdi().getTotal(): fc.getFacturaDto().getCfdi().getTotal();
			return paymentsAmmount.compareTo(totalfactura)!=0;
			
		}else {
			return true;
		}
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Timbrado.TIMBRADO_PAGO_VALIDATION_RULE_DES);
		fc.setSuiteError(String.format("Error durante : %s", Timbrado.TIMBRADO_SUITE));
		fc.setValid(false);
	}
}
