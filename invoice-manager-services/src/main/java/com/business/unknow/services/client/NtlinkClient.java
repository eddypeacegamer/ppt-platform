package com.business.unknow.services.client;

import org.springframework.stereotype.Component;

import com.business.unknow.client.RestNtlinkClientImpl;

@Component
public class NtlinkClient {

	
	public RestNtlinkClientImpl getNtlinkClient(String url,String context) {
		return new RestNtlinkClientImpl(url,context);
	}
}
