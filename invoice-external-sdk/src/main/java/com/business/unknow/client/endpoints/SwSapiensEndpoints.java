package com.business.unknow.client.endpoints;

public class SwSapiensEndpoints {
	
	
	private static final String SECURITY = "/security";
	private static final String AUTHENTICATE = "/authenticate";
	private static final String RFC = "/lrfc";
	
	public static String getAuthenticateEndpoint(){
		return SECURITY.concat(AUTHENTICATE);
	}
	
	public static String getValidateRfcEndpoint(String rfc){
		return RFC.concat("/").concat(rfc);
	}

}
