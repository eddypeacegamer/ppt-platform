package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.FacturacionModernaClientImpl;
import com.business.unknow.client.interfaces.RestFacturacionModernaClient;

@Component
public class FacturacionModernaClient {

	public RestFacturacionModernaClient getFacturacionModernaClient(String url, String context) {
		return new FacturacionModernaClientImpl(url, context);
	}

}
