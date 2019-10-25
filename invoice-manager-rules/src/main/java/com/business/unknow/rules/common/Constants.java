package com.business.unknow.rules.common;

/**
 * @author eej000f
 *
 */
public class Constants {

	public static final String PREVALIDATION_SUITE = "PrevalidationSuite";

	public class Prevalidations {
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

}
