package com.business.unknow.rules.complemento;

import java.math.BigDecimal;
import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.rules.common.Constants.Prevalidations;

@Rule(name = Prevalidations.FACTURA_PADRE_PAGOS_RULE, description = Prevalidations.FACTURA_PADRE_PAGOS)
public class FacturaPadrePagosRule extends AbstractPrevalidations {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		if (fc.getPagos().isEmpty() || fc.getFacturaDto().getCfdi().getTotal() == null
				|| fc.getFacturaDto().getCfdi().getTotal().compareTo(BigDecimal.ZERO) == 0) {
			return true;
		} else {
			BigDecimal pagos = new BigDecimal("0");
			for (PagoDto pago : fc.getPagos())
				pagos.add(pago.getMonto());
			Optional<PagoDto> pagoDto = fc.getPagos().stream()
					.filter(a -> fc.getFacturaDto().getCfdi().getTotal().compareTo(a.getMonto()) == 0// TODO
					// here is a potential
					// issue when the
					// complement will be
					// created with one
					// ammount equal to a
					// previous ammount
					).findFirst();
			return (fc.getFacturaDto().getCfdi().getTotal().compareTo(BigDecimal.ZERO) == 0
					|| pagos.compareTo(BigDecimal.ZERO) == 0
					// || pagos > fc.getFacturaDto().getTotal() //validate how to handle this
					// condition, this is not allowing generate complements
					|| !pagoDto.isPresent());
		}

	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(Prevalidations.FACTURA_PADRE_PAGOS_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", Prevalidations.PREVALIDATION_SUITE));
		fc.setValid(false);
	}
}
