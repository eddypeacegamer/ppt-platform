package com.business.unknow.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.client.common.AbstractClient;
import com.business.unknow.client.endpoints.FacturacionModernaEndpoints;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaCancelResponseModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaErrorMessage;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaErrorModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaRequestModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaResponseModel;
import com.business.unknow.client.facturacionmoderna.util.FacturaModernaClientException;
import com.business.unknow.client.facturacionmoderna.util.FacturaModernaMessageParser;
import com.business.unknow.client.interfaces.RestFacturacionModernaClient;

public class FacturacionModernaClientImpl extends AbstractClient implements RestFacturacionModernaClient {

	private static final Logger log = LoggerFactory.getLogger(FacturacionModernaClientImpl.class);

	public FacturacionModernaClientImpl(String url, String contextPath) {
		super(url, contextPath);
	}

	protected <T> T parseResponse(Response response, Class<T> clazz) throws FacturaModernaClientException {
		int status = response.getStatus();
		log.info("Status {}", status);
		String content = response.readEntity(String.class);
		FacturaModernaMessageParser soapRequest = new FacturaModernaMessageParser();
		if (response.getStatusInfo().getFamily() == Status.Family.SUCCESSFUL) {
			return soapRequest.getResponse(content, clazz);
		} else {
			FacturaModernaErrorModel errorModel = soapRequest.getErrorResponse(content);
			log.error("Error response: {}", content);
			throw new FacturaModernaClientException(
					new FacturaModernaErrorMessage(errorModel.getFaultstring().concat(": ").concat(errorModel.getFaultcode()),"Error al timbrar en factura moderna"),
					response.getStatus());
		}
	}

	@Override
	public FacturaModernaCancelResponseModel cancelar(FacturaModernaRequestModel request)
			throws FacturaModernaClientException {
		FacturaModernaMessageParser soapRequest = new FacturaModernaMessageParser();
		log.info("Canceling the invoice.");
		String endpoint = FacturacionModernaEndpoints.getTimbradoEndpoint();
		System.out.println(soapRequest.createCancelRequest(request));
		Response response = post(endpoint, MediaType.TEXT_PLAIN, soapRequest.createCancelRequest(request));
		return parseResponse(response, FacturaModernaCancelResponseModel.class);
	}

	@Override
	public FacturaModernaResponseModel stamp(FacturaModernaRequestModel request) throws FacturaModernaClientException {
		FacturaModernaMessageParser soapRequest = new FacturaModernaMessageParser();
		log.info("Stamping the invoice.");
		String endpoint = FacturacionModernaEndpoints.getTimbradoEndpoint();
		Response response = post(endpoint, MediaType.TEXT_PLAIN, soapRequest.createStampRequest(request));
		return parseResponse(response, FacturaModernaResponseModel.class);
	}

}
