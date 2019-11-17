package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.RestSwSapiensClientImpl;
import com.business.unknow.client.interfaces.RestSwSapiensClient;

@Component
public class SwSapiensClient {

	private static final String URL = "http://services.test.sw.com.mx";
	private static final String CONTEXT = "";
	private static final String USR = "demo";
	private static final String PW = "123456789";

	public RestSwSapiensClient getSwSapiensClient() {
		return new RestSwSapiensClientImpl(URL, CONTEXT, USR, PW);
	}
}
