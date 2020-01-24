package com.business.unknow.rules.pago.delete;

import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.rules.common.Constants.DeletePagoSuite;

@Rule(name = DeletePagoSuite.DELETE_PPD_PAYMENT_RULE, description = DeletePagoSuite.DELETE_PPD_PAYMENT)
public class DeletePpdPaymentRule {

	@Condition
	public boolean condition(@Fact("facturaContext") FacturaContext fc) {
		Optional<PagoDto> pagoDto = fc.getPagos().stream().findFirst();
		return !pagoDto.isPresent()
				|| pagoDto.get().getStatusPago().equals(RevisionPagosEnum.ACEPTADO.name())
						|| fc.getFacturaDto().getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor())
						|| fc.getFacturaDto().getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor());
	}

	@Action
	public void execute(@Fact("facturaContext") FacturaContext fc) {
		fc.setRuleErrorDesc(DeletePagoSuite.DELETE_PPD_PAYMENT_RULE_DESC);
		fc.setSuiteError(String.format("Error durante : %s", DeletePagoSuite.DELETE_PAGO_SUITE));
		fc.setValid(false);
	}

}
