/**
 * 
 */
package com.business.unknow.rules.payments;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

/**
 * @author ralfdemoledor
 *
 */
@Rule(name = PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE, description = PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE_DESC)
public class UpdatePaymentInvoiceStatusRule {
	
	@Condition
	public boolean condition(@Fact("factura") FacturaDto factura, @Fact("currentPayment") PagoDto currentPayment) {
		return (RevisionPagosEnum.RECHAZADO.name().equals(currentPayment.getStatusPago())) ? false : // rechazo de pago
			FacturaStatusEnum.CANCELADA.getValor().equals(factura.getStatusFactura()) ||
				 FacturaStatusEnum.POR_TIMBRAR.getValor().equals(factura.getStatusFactura()) ||
				 	FacturaStatusEnum.RECHAZO_OPERACIONES.getValor().equals(factura.getStatusFactura());
	}

	@Action
	public void execute(@Fact("results") List<String>results) {
		results.add(PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE_DESC);
	}

}
