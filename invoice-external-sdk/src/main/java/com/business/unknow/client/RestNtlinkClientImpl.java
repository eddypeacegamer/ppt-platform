package com.business.unknow.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.client.common.AbstractClient;
import com.business.unknow.client.endpoints.NtlinkEndpoints;
import com.business.unknow.client.interfaces.RestNtlinkClient;
import com.business.unknow.client.ntlink.model.NtlinkCancelRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkCancelResponseModel;
import com.business.unknow.client.ntlink.model.NtlinkClientException;
import com.business.unknow.client.ntlink.model.NtlinkErrorMessage;
import com.business.unknow.client.ntlink.model.NtlinkRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkResponseModel;
import com.business.unknow.client.ntlink.util.NtlinkMessageParser;

public class RestNtlinkClientImpl extends AbstractClient implements RestNtlinkClient {

	private static final Logger log = LoggerFactory.getLogger(FacturacionModernaClientImpl.class);

	public RestNtlinkClientImpl(String url, String contextPath) {
		super(url, contextPath);
	}

	protected <T> T parseResponse(Response response, Class<T> clazz) throws NtlinkClientException {
		int status = response.getStatus();
		log.info("Status {}", status);
		String content = response.readEntity(String.class);
		NtlinkMessageParser soapRequest = new NtlinkMessageParser();
		if (response.getStatusInfo().getFamily() == Status.Family.SUCCESSFUL) {
			return soapRequest.getResponse(content, clazz);
		} else {
			NtlinkResponseModel errorModel = soapRequest.getErrorResponse(content);
			log.error("Error response: {}", content);
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error al timbrar en Ntlink", errorModel.getDescripcionError()),
					response.getStatus());
		}
	}

	protected <T> T parseCancelResponse(Response response, Class<T> clazz) throws NtlinkClientException {
		int status = response.getStatus();
		log.info("Status {}", status);
		String content = response.readEntity(String.class);
		NtlinkMessageParser soapRequest = new NtlinkMessageParser();
		if (response.getStatusInfo().getFamily() == Status.Family.SUCCESSFUL) {
			return soapRequest.getResponseCancel(content, clazz);
		} else {
			NtlinkResponseModel errorModel = soapRequest.getErrorResponse(content);
			log.error("Error response: {}", content);
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error al timbrar en Ntlink", errorModel.getDescripcionError()),
					response.getStatus());
		}
	}

	@Override
	public NtlinkCancelResponseModel cancelar(NtlinkCancelRequestModel request) throws NtlinkClientException {
		NtlinkMessageParser soapRequest = new NtlinkMessageParser();
		log.info("Canceling the invoice.");
		String endpoint = NtlinkEndpoints.getTimbradoEndpoint();
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add("SOAPAction", "https://ntlink.com.mx/IServicioTimbrado/IServicioTimbrado/CancelaCfdi");
		Response response = post(endpoint, MediaType.TEXT_XML, soapRequest.createCancelRequest(request), headers);
		NtlinkCancelResponseModel ntlinkResponse = parseCancelResponse(response, NtlinkCancelResponseModel.class);
		if (ntlinkResponse.getCancelaCfdiResult() != null
				&& ntlinkResponse.getCancelaCfdiResult().contains("Error al cancelar el comprobante")) {
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error al cancelar en Ntlink", ntlinkResponse.getCancelaCfdiResult()),
					response.getStatus());
		} else {
			return ntlinkResponse;
		}
	}

	@Override
	public NtlinkResponseModel stamp(NtlinkRequestModel request) throws NtlinkClientException {
		NtlinkMessageParser soapRequest = new NtlinkMessageParser();
		log.info("Stamping the invoice.");
		String endpoint = NtlinkEndpoints.getTimbradoEndpoint();
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add("SOAPAction", "https://ntlink.com.mx/IServicioTimbrado/IServicioTimbrado/TimbraCfdiQr");
		Response response = post(endpoint, MediaType.TEXT_XML, soapRequest.createStampRequest(request), headers);
		NtlinkResponseModel ntlinkResponse = parseResponse(response, NtlinkResponseModel.class);
		if (ntlinkResponse.getDescripcionError() != null && !ntlinkResponse.getDescripcionError().isEmpty()) {
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error al timbrar en Ntlink", ntlinkResponse.getDescripcionError()),
					response.getStatus());
		} else {
			return ntlinkResponse;
		}
	}
}
