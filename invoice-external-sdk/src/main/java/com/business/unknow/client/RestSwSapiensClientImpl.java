package com.business.unknow.client;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.Constants;
import com.business.unknow.client.common.AbstractClient;
import com.business.unknow.client.swsapiens.util.SwSapiensConfig;
import com.business.unknow.client.endpoints.SwSapiensEndpoints;
import com.business.unknow.client.interfaces.RestSwSapiensClient;
import com.business.unknow.client.swsapiens.model.SwSapiensCancel;
import com.business.unknow.client.swsapiens.model.SwSapiensConstans;
import com.business.unknow.client.swsapiens.model.SwSapiensErrorMessage;
import com.business.unknow.client.swsapiens.util.SwSapiensClientException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestSwSapiensClientImpl extends AbstractClient implements RestSwSapiensClient {

	private static final Logger log = LoggerFactory.getLogger(RestSwSapiensClientImpl.class);
	
	private String token;
	private Integer time=0;
	private String user;
	private String pw;
	
	public RestSwSapiensClientImpl(String url, String contextPath, String user, String pw) {
		super(url, contextPath);
		this.pw = pw;
		this.user = user;
	}

	protected <T> T parseResponse(Response response, TypeReference<T> entityType) throws SwSapiensClientException {
		T result = null;
		int status = response.getStatus();
		log.info("Status {}", status);
		System.out.println();
		try {
			String content = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			if (response.getStatusInfo().getFamily() == Status.Family.SUCCESSFUL) {
				if (!content.isEmpty()) {
					try (StringReader reader = new StringReader(content)) {
						result = mapper.readValue(reader, entityType);
					}
				}
			} else {
				log.error("Error response: {}", content);
				if (content.contains("message")) {
					try (StringReader reader = new StringReader(content)) {
						SwSapiensErrorMessage error = mapper.readValue(reader,
								new TypeReference<SwSapiensErrorMessage>() {
								});
						log.info(error.toString());
						throw new SwSapiensClientException(error, status);
					}
				} else {
					SwSapiensErrorMessage error = new SwSapiensErrorMessage("Unexpected services response",
							String.format("Error response: %s", content));
					throw new SwSapiensClientException(error, status);
				}
			}
		} catch (IOException e) {
			SwSapiensErrorMessage error = new SwSapiensErrorMessage();
			error.setMessage("Client Error, the deserialization of entity " + entityType.getClass().getName()
					+ " can't be done.");
			error.setMessageDetail(e.getMessage());
			throw new SwSapiensClientException(error, SwSapiensConstans.UNPROCESSABLE_ENTITY);
		} catch (SwSapiensClientException e) {
			throw e;
		} catch (Exception e) {
			SwSapiensErrorMessage error = new SwSapiensErrorMessage();
			error.setMessage("Unhandled Error in client implementation");
			error.setMessageDetail(e.getMessage());
			throw new SwSapiensClientException(error, SwSapiensConstans.UNPROCESSABLE_ENTITY);
		}
		return result;
	}

	@Override
	public SwSapiensConfig authenticateService() throws SwSapiensClientException {
		log.info("authenticate with Sw Sapiens.");
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(SwSapiensConstans.USER, this.user);
		headers.add(SwSapiensConstans.PW, this.pw);
		String endpoint = SwSapiensEndpoints.getAuthenticateEndpoint();
		Response response = post(endpoint, MediaType.APPLICATION_JSON, "", headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}

	@Override
	public SwSapiensConfig validateRfc(String rfc) throws SwSapiensClientException {
		log.info("Validating rfc.");
		authenticate();
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(SwSapiensConstans.TOKEN_PARAMETER, token);
		String endpoint = SwSapiensEndpoints.getValidateRfcEndpoint(rfc);
		Response response = get(endpoint, MediaType.APPLICATION_JSON, headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}

	private void authenticate() throws SwSapiensClientException {
		Date date=new Date(new Long(this.time)*Constants.MILISECONDS);
		if (this.token == null||date.before(new Date())) {
			SwSapiensConfig config = authenticateService();
			this.token = config.getData().getToken();
			this.time=config.getData().getExpires_in();
		}
	}

	@Override
	public SwSapiensConfig stamp(String xml, String version) throws SwSapiensClientException {
		log.info("Stamping the invoice.");
		authenticate();
		String boundary = UUID.randomUUID().toString();
		String raw = SwSapiensConstans.TOKEN_SEPARATOR + boundary + SwSapiensConstans.TOKEN_SUFIX + xml
				+ SwSapiensConstans.TOKEN_ENTER + boundary + SwSapiensConstans.TOKEN_SEPARATOR;
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(SwSapiensConstans.TOKEN_PARAMETER, token);
		headers.add(SwSapiensConstans.CONTENT_TYPE_PARAMETER, SwSapiensConstans.CONTENT_TYPE_VALUE.concat(boundary));
		headers.add(SwSapiensConstans.CONTENT_DISPOSITION_PARAMETER, SwSapiensConstans.CONTENT_DISPOSITION_VALUE);
		String endpoint = SwSapiensEndpoints.getStampByVersionEndpoint(version);
		Response response = post(endpoint, SwSapiensConstans.CONTENT_TYPE_VALUE.concat(boundary), raw, headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}

	@Override
	public SwSapiensConfig cancel(String uuid, String password, String rfc, String cert, String key)
			throws SwSapiensClientException {
		log.info("Canceling the factura {}", uuid);
		authenticate();
		SwSapiensCancel cancel = new SwSapiensCancel(uuid, password, rfc, cert, key);
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(SwSapiensConstans.TOKEN_PARAMETER, token);
		String endpoint = SwSapiensEndpoints.getCancelCsdEndpoint();
		Response response = post(endpoint, MediaType.APPLICATION_JSON, cancel, headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}
}
