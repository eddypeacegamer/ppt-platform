package com.business.unknow.rules.prevalidations;

import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.PagoDto;
import com.business.unknow.rules.common.Constants;

@Rule(name = Constants.Prevalidations.FACTURA_PADRE_PAGOS_RULE, description = Constants.Prevalidations.FACTURA_PADRE_PAGOS)
public class FacturaPadrePagosRule extends AbstractPrevalidations {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		if (fc.getPagos().isEmpty() || fc.getComlpemento().getTotal() == null || fc.getComlpemento().getTotal() == 0) {
			return true;
		} else {
			double pagos = 0.0f;
			for (PagoDto pago : fc.getPagos())
				pagos += pago.getMonto();
			pagos += fc.getComlpemento().getTotal();
			Optional<PagoDto> pagoDto = fc.getPagos().stream()
					.filter(a -> a.getMonto() == fc.getComlpemento().getTotal()).findFirst();
			return (fc.getComlpemento().getTotal() == 0 || pagos == 0 || pagos > fc.getFacturaDto().getTotal()
					|| pagoDto.isPresent());
		}

	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Constants.Prevalidations.FACTURA_PADRE_PAGOS_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Constants.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
