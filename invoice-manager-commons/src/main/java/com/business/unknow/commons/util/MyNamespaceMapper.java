package com.business.unknow.commons.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper {

	private static final String SAT_CFDI = "http://www.sat.gob.mx/cfd/3";
	private static final String SCHEMA="http://www.w3.org/2001/XMLSchema-instance";
	private static final String PAGOS_10 = "http://www.sat.gob.mx/Pagos";
	private static final String TFD = "http://www.sat.gob.mx/TimbreFiscalDigital";
	private static final String SAT_PREFIX = "cfdi";
	private static final String XSI_PREFIX = "xsi";
	private static final String TFD_PREFIX = "tfd";
	private static final String PAGO_PREFIX = "pago10";
	

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		if (SAT_CFDI.equals(namespaceUri)) {
			return SAT_PREFIX;
		} else if (SCHEMA.equals(namespaceUri)) {
			return XSI_PREFIX;
		}  else if (PAGOS_10.equals(namespaceUri)) {
			return PAGO_PREFIX;
		}else if (TFD.equals(namespaceUri)) {
			return TFD_PREFIX;
		}
		return suggestion;
	}

	@Override
	public String[] getPreDeclaredNamespaceUris() {
		return new String[] {SCHEMA};
	}
}
