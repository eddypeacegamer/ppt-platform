package com.business.unknow.client.endpoints;

public class SwSapiensEndpoints {

	private static final String SECURITY = "/security";
	private static final String AUTHENTICATE = "/authenticate";
	private static final String RFC = "/lrfc";
	private static final String CFDI = "/cfdi33";
	private static final String STAMP = "/stamp";
	private static final String CANCEL = "/cancel";
	private static final String CSD = "/csd";
	private static final String LCO = "/lco";
	
	
	public static String getAuthenticateEndpoint() {
		return SECURITY.concat(AUTHENTICATE);
	}
	
	public static String getValidateLcoEndpoint(String noCertificado) {
		return LCO.concat("/").concat(noCertificado);
	}

	public static String getValidateRfcEndpoint(String rfc) {
		return RFC.concat("/").concat(rfc);
	}

	public static String getStampByVersionEndpoint(String version) {
		return CFDI.concat(STAMP).concat("/").concat(version);
	}
	
	public static String getCancelCsdEndpoint() {
		return CFDI.concat(CANCEL).concat(CSD);
	}
}
