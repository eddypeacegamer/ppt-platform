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
		public static final String EMISOR_VALIDATION_RULE_DESC = "La informacion del emisor no es valida";
		
		public static final String RECEPTOR_VALIDATION = "ReceptorValidation";
		public static final String RECEPTOR_VALIDATION_RULE = "ReceptorValidationRule";
		public static final String RECEPTOR_VALIDATION_RULE_DESC = "La informacion del receptor no es valida";
		
	}
	
	public class DeletePagoSuite {
		public static final String DELETE_PAGO_SUITE = "DeletePagoSuite";
		
		public static final String DELETE_PPD_PAYMENT = "DeletePpdPayment";
		public static final String DELETE_PPD_PAYMENT_RULE = "DeletePpdPaymentRule";
		public static final String DELETE_PPD_PAYMENT_RULE_DESC = "No se puede borrar el pago ppd por que hay incosistencias";
		
		public static final String DELETE_PAYMENT = "DeletePayment";
		public static final String DELETE_PAYMENT_RULE = "DeletePaymentRule";
		public static final String DELETE_PAYMENT_RULE_DESC = "No se puede borrar el pago por que hay incosistencias";
		
		public static final String DELETE_CREDIT_PAYMANT = "DeleteCreditPaymant";
		public static final String DELETE_CREDIT_PAYMANT_RULE = "DeleteCreditPaymantRule";
		public static final String DELETE_CREDIT_PAYMANT_RULE_DESC = "No se puede borrar el credito , hay un pago regular";
	}
	
	public class PagoPpdSuite {
		public static final String PAGO_PPD_SUITE = "PagoPpdSuite";
		
		public static final String MONTO_PAGO_VALIDATION = "MontoPagoValidation";
		public static final String MONTO_PAGO_VALIDATION_RULE = "MontoPagoValidationRule";
		public static final String MONTO_PAGO_VALIDATION_RULE_DESC = "El monto del pago actual contiene una incongruencia con su credito";
		
	}
	
	public class PagoPueSuite {
		public static final String PAGO_PUE_SUITE = "PagoPueSuite";
		
		public static final String PAYMENT_CREDIT_PUE= "PaymantPueWithCredit";
		public static final String PAYMENT_CREDIT_PUE_RULE = "PaymantPueWithCreditRule";
		public static final String PAYMENT_CREDIT_PUE_RULE_DESC = "El Credito requerido para tu factura PUE esta mal";
		
		public static final String PAYMENT_PUE_NOT_CREDIT= "PaymentPueNotCredit";
		public static final String PAYMENT_PUE_NOT_CREDIT_RULE = "PaymentPueNotCreditRule";
		public static final String PAYMENT_PUE_NOT_CREDIT_RULE_DESC = "Los datos del pago de la factura pue son incorrectos";
		
		public static final String CREATE_CREDIT_VALIDATION = "CreateCreditValidation";
		public static final String CREATE_CREDIT_VALIDATION_RULE = "CreateCreditValidationRule";
		public static final String CREATE_CREDIT_VALIDATION_RULE_DESC = "Los datos de credito Pue son incorrectos";
	}

	public class Prevalidations {
		public static final String PREVALIDATION_SUITE = "PrevalidationSuite";
		
		public static final String FACTURA_PADRE_COMPLEMENTO = "FacturaPadreComplemento";
		public static final String FACTURA_PADRE_COMPLEMENTO_RULE = "FacturaPadreComplementoRule";
		public static final String FACTURA_PADRE_COMPLEMENTO_RULE_DESC = "La factura padre no cumple los requerimientos minimos para poder tener complementos";

		public static final String FACTURA_PADRE_PAGOS = "FacturaPadrePagos";
		public static final String FACTURA_PADRE_PAGOS_RULE = "FacturaPadrePagosRule";
		public static final String FACTURA_PADRE_PAGOS_RULE_DESC = "Existe una inconsitencia con los pagos";

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
		public static final String TIMBRADO_PAGO_VALIDATION_RULE_DES = "Los datos de la factura padre tienen una inconsistencia.";
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
