package com.business.unknow.client.interfaces;

import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;

public interface RestSwSapiensClient {

	public SwSapiensConfig authenticate(String usr,String pw) throws SwSapiensClientException;
	
	public SwSapiensConfig validateRfc(String rfc) throws SwSapiensClientException;
	
	public SwSapiensConfig stamp(String xml, String version) throws SwSapiensClientException;
}
