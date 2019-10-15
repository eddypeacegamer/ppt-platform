package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.RestSwSapiensClientImpl;
import com.business.unknow.client.interfaces.RestSwSapiensClient;

@Component
public class SwSapiensClient {

	private static final String URL = "http://services.test.sw.com.mx";
	private static final String CONTEXT = "";

	public RestSwSapiensClient getSwSapiensClient() {
		return new RestSwSapiensClientImpl(URL, CONTEXT);
	}
}