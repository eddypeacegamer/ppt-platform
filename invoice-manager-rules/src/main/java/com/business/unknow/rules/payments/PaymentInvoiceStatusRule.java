/**
 * 
 */
package com.business.unknow.rules.payments;

import java.math.BigDecimal;
import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.rules.common.Constants.PaymentsSuite;

/**
 * @author ralfdemoledor
 *
 */
@Rule(name = PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE, description = PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE_DESC)
public class PaymentInvoiceStatusRule {

	@Condition
	public boolean condition(@Fact("facturas") List<FacturaDto> facturas,@Fact("results") List<String>results,
			@Fact("payment") PagoDto currentPayment) {
		for (FacturaDto factura : facturas) {
			if(MetodosPagoEnum.PPD.getClave().equals(factura.getMetodoPago())) {
				if(!RevisionPagosEnum.RECHAZADO.name().equals(currentPayment.getStatusPago()) &&
						(FacturaStatusEnum.CANCELADA.getValor().equals(factura.getStatusFactura())
							|| FacturaStatusEnum.POR_TIMBRAR.getValor().equals(factura.getStatusFactura())
							|| FacturaStatusEnum.RECHAZO_OPERACIONES.getValor().equals(factura.getStatusFactura()))
								|| BigDecimal.ZERO.compareTo(factura.getSaldoPendiente())>= 0) {
						results.add(String.format("La factura con pre folio %d no es valida", factura.getIdCfdi()));
						return true;
				}
			}
			
			if(MetodosPagoEnum.PUE.getClave().equals(factura.getMetodoPago())) {
				if(!RevisionPagosEnum.RECHAZADO.name().equals(currentPayment.getStatusPago()) && 
						(FacturaStatusEnum.CANCELADA.getValor().equals(factura.getStatusFactura())
							|| FacturaStatusEnum.RECHAZO_OPERACIONES.getValor().equals(factura.getStatusFactura()))
								|| BigDecimal.ZERO.compareTo(factura.getSaldoPendiente())>= 0) {
						results.add(String.format("La factura con pre folio %d no es valida", factura.getIdCfdi()));
						return true;
				}
			}
			
			
		}
		return false;
	}

	@Action
	public void execute(@Fact("results") List<String> results) {
		results.add(PaymentsSuite.INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE_DESC);
	}

}
