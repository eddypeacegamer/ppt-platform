package com.business.unknow.client.interfaces;

import com.business.unknow.client.swsapiens.util.SwSapiensClientException;
import com.business.unknow.client.swsapiens.util.SwSapiensConfig;

public interface RestSwSapiensClient {

	public SwSapiensConfig authenticateService() throws SwSapiensClientException;

	public SwSapiensConfig validateRfc(String rfc) throws SwSapiensClientException;

	public SwSapiensConfig stamp(String xml, String version) throws SwSapiensClientException;

	public void cancel(String uuid, String password, String rfc, String cert, String key)
			throws SwSapiensClientException;

	public SwSapiensConfig validateLco(String noCertificado) throws SwSapiensClientException;
}
