package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.FacturacionModernaClientImpl;
import com.business.unknow.client.interfaces.RestFacturacionModernaClient;

@Component
public class FacturacionModernaClient {

	private static final String URL = "https://t1demo.facturacionmoderna.com";
	private static final String CONTEXT = "";

	public RestFacturacionModernaClient getFacturacionModernaClient() {
		return new FacturacionModernaClientImpl(URL, CONTEXT);
	}

}
