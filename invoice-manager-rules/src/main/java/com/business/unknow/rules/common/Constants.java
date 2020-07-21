package com.business.unknow.rules.common;

/**
 * @author eej000f
 *
 */
public class Constants {
	
	public class FacturaSuite {
		
		public static final String FACTURA_SUITE = "FacturaSuite";
		
		public static final String EMISOR_VALIDATION = "EmisorValidation";
		public static final String EMISOR_VALIDATION_RULE = "EmisorValidationRule";
		public static final String EMISOR_VALIDATION_RULE_DESC = "La empresa Emisora no se encunetra activa en el sistema";
		
		public static final String RECEPTOR_VALIDATION = "ReceptorValidation";
		public static final String RECEPTOR_VALIDATION_RULE = "ReceptorValidationRule";
		public static final String RECEPTOR_VALIDATION_RULE_DESC = "La informacion del receptor no es valida";
		
	}
	
	public class DeletePagoSuite {
		public static final String DELETE_PAGO_SUITE = "DeletePagoSuite";
		
		public static final String DELETE_STATUS_PAYMENT = "DeletePpdPayment";
		public static final String DELETE_STATUS_PAYMENT_RULE = "DeletePpdPaymentRule";
		public static final String DELETE_STATUS_PAYMENT_RULE_DESC = "El estatus del pago de una o varias facturas no permiten el borrado";
		
		public static final String DELETE_PAYMENT = "DeletePayment";
		public static final String DELETE_PAYMENT_RULE = "DeletePaymentRule";
		public static final String DELETE_PAYMENT_RULE_DESC = "No se puede borrar el pago por que hay incosistencias";
		
		public static final String DELETE_CREDIT_PAYMANT = "DeleteCreditPaymant";
		public static final String DELETE_CREDIT_PAYMANT_RULE = "DeleteCreditPaymantRule";
		public static final String DELETE_CREDIT_PAYMANT_RULE_DESC = "En facturas PPD no puede ser borrado el pago a credito";
	}
	
	
	public class FacturaValidationSuite {
		public static final String FACTURA_VALIDATION_SUITE = "FacturaValidationSuite";
		
		public static final String FACTURA_VALIDATION_PUE_RULE = "ValidacionFacturaPueRule";
		public static final String FACTURA_VALIDATION_PUE_RULE_DESC = "Validar estado de la factura PUE";

		public static final String FACTURA_VALIDATION_PPD_RULE = "ValidacionFacturaPpdRule";
		public static final String FACTURA_VALIDATION_PPD_RULE_DESC = "Validar estado de la factura PPD";
		
		public static final String FACTURA_VALIDATION_COMP_RULE = "ValidacionFacturaComplementoRule";
		public static final String FACTURA_VALIDATION_COMP_RULE_DESC = "Validar estado del complemento";
	}
	
	public class PaymentsSuite {
		
		public static final String PAGO_PPD_SUITE = "PagoPpdSuite";
		public static final String PAGO_PUE_SUITE = "PagoPueSuite";
		
		public static final String MONTO_PAGO_VALIDATION = "MontoPagoValidation";
		public static final String MONTO_PAGO_VALIDATION_RULE = "MontoPagoValidationRule";
		public static final String MONTO_PAGO_VALIDATION_RULE_DESC = "Monto invalido de pago, la suma de los pagos no puede ser superior o diferente al moto total del pago raiz";
		
		public static final String ZERO_AMMOUNT_VALIDATION = "ZeroAmountValidationRule";
		public static final String ZERO_AMMOUNT_VALIDATION_RULE = "ZeroAmountValidationRule";
		public static final String ZERO_AMMOUNT_VALIDATION_RULE_DESC = "Uno o varios pagos asignados son  iguales o menores a $0.00.";
		
		public static final String ORDER_PAYMENT_VALIDATION = "PaymentOrderValidation";
		public static final String ORDER_PAYMENT_VALIDATION_RULE = "PaymentOrderValidationRule";
		public static final String ORDER_PAYMENT_VALIDATION_RULE_DESC = "Incongruencia en la validacion de pagos, el segundo pago no puede ser validado si el primer pago no ha sido validado.";
		
		public static final String INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION = "UpdatePaymentInvoiceStatusRules";
		public static final String INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE = "UpdatePaymentInvoiceStatusRulesRule";
		public static final String INVOICE_STATUS_PAYMENT_UPADTE_VALIDATION_RULE_DESC = "Incongruencia en el estatus de alguna de las facturas, los pagos de facturas rechazadas, canceladas no pueden validar pagos.";
		
		public static final String DOUBLE_PAYMENT_VALIDATION = "DoubleOrderValidation";
		public static final String DOUBLE_PAYMENT_VALIDATION_RULE = "DoubleOrderValidationRule";
		public static final String DOUBLE_PAYMENT_VALIDATION_RULE_DESC = "Incongruencia en la validacion del segundo pago, el primer pago  no ha sido validado.";
		
		public static final String CREDIT_PAYMENT_VALIDATION = "CreditPaymentRule";
		public static final String CREDIT_PAYMENT_VALIDATION_RULE = "CreditPaymentRule";
		public static final String CREDIT_PAYMENT_VALIDATION_RULE_DESC = "Las facturas PPD no pueden cargar pagos a credito.";
		
		public static final String CONFLICT_PAYMENT_VALIDATION = "ConflictOrderValidation";
		public static final String CONFLICT_PAYMENT_VALIDATION_RULE = "ConflictOrderValidationRule";
		public static final String CONFLICT_PAYMENT_VALIDATION_RULE_DESC = "Incongruencia en la validacion del pago.";
		
	}
	

	public class Prevalidations {
		public static final String PREVALIDATION_SUITE = "PrevalidationSuite";
		
		public static final String FACTURA_PADRE_COMPLEMENTO = "FacturaPadreComplemento";
		public static final String FACTURA_PADRE_COMPLEMENTO_RULE = "FacturaPadreComplementoRule";
		public static final String FACTURA_PADRE_COMPLEMENTO_RULE_DESC = "La factura padre no cumple los requerimientos minimos para poder tener complementos";

		public static final String FACTURA_PADRE_STATUS = "FacturaPadrePagos";
		public static final String FACTURA_PADRE_STATUS_RULE = "FacturaPadrePagosRule";
		public static final String FACTURA_PADRE_STATUS_RULE_DESC = "Los estatus de la factura padre no son correctos";
	}

	public class Timbrado {
		public static final String TIMBRADO_SUITE = "FacturarSuite";
		
		public static final String TIMBRADO_STATUS = "FacturaStatus";
		public static final String TIMBRADO_STATUS_RULE = "FacturaStatusRule";
		public static final String TIMBRADO_STATUS_RULE_DESC = "La estatus de la factura no es correcta.";
		
		public static final String TIMBRADO_DATOS_VALIDATION = "FacturaDatosValidation";
		public static final String TIMBRADO_DATOS_VALIDATION_RULE = "FacturaDatosValidationRule";
		public static final String TIMBRADO_DATOS_VALIDATION_RULE_DESC = "Los datos de la factura tienen una inconsistencia.";
		
		public static final String TIMBRADO_PADRE_STATUS = "FacturaPadreStatusValidation";
		public static final String TIMBRADO_PADRE_STATUS_RULE = "FacturaPadreStatusValidationRule";
		public static final String TIMBRADO_PADRE_STATUS_RULE_DESC = "Los datos de la factura padre tienen una inconsistencia.";
		
		public static final String TIMBRADO_PAGO_VALIDATION = "FacturaPagoValidation";
		public static final String TIMBRADO_PAGO_VALIDATION_RULE = "FacturaPagoValidationRule";
		public static final String TIMBRADO_PAGO_VALIDATION_RULE_DES = "Los pagos de la factura tienen incosistencias";
	}
	
	public class CancelacionSuite {
		public static final String CANCELAR_SUITE = "CancelarSuite";
		
		public static final String CANCELAR_STATUS_VALIDATION = "StatusCancelarValidation";
		public static final String CANCELAR_STATUS_VALIDATION_RULE = "StatusCancelarValidationRule";
		public static final String CANCELAR_STATUS_VALIDATION_RULE_DESC = "Los status de la facura son incorrectos.";
		
		
	}

	public class DevolucionSuite {
		public static final String DEVOLUCION_SUITE = "DevolucionSuite";
		
		public static final String CLIENT_VALIDATION = "ClientValidation";
		public static final String CLIENT_VALIDATION_RULE = "ClientValidationRule";
		public static final String CLIENT_VALIDATION_RULE_DESC = "No se pueden generar devoluciones a clientes inactivos.";
		
		public static final String FACTURA_VALIDATION = "FacturaValidation";
		public static final String FACTURA_VALIDATION_RULE = "FacturaValidationRule";
		public static final String FACTURA_VALIDATION_RULE_DESC = "La factura debe estar timbrada para solicitar la devolucion.";
		
		public static final String FACTURA_PUE_STATUS_DEVOLUCION = "FacturaPueStatusDevolucion";
		public static final String FACTURA_PUE_STATUS_DEVOLUCION_RULE = "FacturaPueStatusDevolucionRule";
		public static final String FACTURA_PUE_STATUS_DEVOLUCION_RULE_DESC = "El estatus de devolucion de la factura Pue es incorrecto.";
		
		public static final String FACTURA_PPD_STATUS_DEVOLUCION = "FacturaPpdStatusDevolucion";
		public static final String FACTURA_PPD_STATUS_DEVOLUCION_RULE = "FacturaPpdStatusDevolucionRule";
		public static final String FACTURA_PPD_STATUS_DEVOLUCION_RULE_DESC = "El estatus de devolucion deL complemento es incorrecto.";
		
		public static final String PAGO_DEVOLUCION = "PagoDevolcuion";
		public static final String PAGO_DEVOLUCION_RULE = "PagoDevolcuionRule";
		public static final String PAGO_DEVOLUCION_RULE_DESC = "El estatus del pago es incorrecto.";
		
		
		
	}
}
