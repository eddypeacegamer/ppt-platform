package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.RestNtlinkClientImpl;

@Component
public class NtlinkClient {

	private static final String URL = "http://pruebas.ntlink.com.mx";
	private static final String CONTEXT = "/CertificadorWs33";
	
	public RestNtlinkClientImpl getNtlinkClient() {
		return new RestNtlinkClientImpl(URL,CONTEXT);
	}
}
