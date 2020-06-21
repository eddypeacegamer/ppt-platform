package com.business.unknow.rules.pago.delete;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.DeletePagoSuite;

@Rule(name = DeletePagoSuite.DELETE_STATUS_PAYMENT_RULE, description = DeletePagoSuite.DELETE_STATUS_PAYMENT_RULE_DESC)
public class StatusDeletePaymentRule {

	@Condition
	public boolean condition(@Fact("payment") PagoDto payment, @Fact("factura") FacturaDto invoice) {
		
		return RevisionPagosEnum.ACEPTADO.name().equals(payment.getStatusPago())
						|| FacturaStatusEnum.TIMBRADA.getValor().equals(invoice.getStatusFactura())
						|| FacturaStatusEnum.CANCELADA.getValor().equals(invoice.getStatusFactura());
	}

	@Action
	public void execute(@Fact("results") List<String> results) {
		results.add(DeletePagoSuite.DELETE_STATUS_PAYMENT_RULE_DESC);
	}

}
