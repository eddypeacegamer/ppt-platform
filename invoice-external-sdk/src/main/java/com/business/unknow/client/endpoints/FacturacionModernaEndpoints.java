package com.business.unknow.client.endpoints;

public class FacturacionModernaEndpoints {

	private static final String TIMBRADO = "/timbrado";
	private static final String SOAP = "/soap";

	public static String getTimbradoEndpoint() {
		return TIMBRADO.concat(SOAP);
	}
}
