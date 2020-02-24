package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.RestSwSapiensClientImpl;
import com.business.unknow.client.interfaces.RestSwSapiensClient;

@Component
public class SwSapiensClient {


	public RestSwSapiensClient getSwSapiensClient(String url,String context,String usr,String pw) {
		return new RestSwSapiensClientImpl(url, context, usr, pw);
	}
}
