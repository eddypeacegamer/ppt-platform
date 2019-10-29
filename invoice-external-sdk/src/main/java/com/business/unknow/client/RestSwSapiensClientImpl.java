package com.business.unknow.client;

import java.io.IOException;
import java.io.StringReader;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.business.unknow.client.common.AbstractClient;
import com.business.unknow.client.endpoints.SwSapiensEndpoints;
import com.business.unknow.client.interfaces.RestSwSapiensClient;
import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;
import com.business.unknow.client.model.swsapiens.SwSapiensErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestSwSapiensClientImpl extends AbstractClient implements RestSwSapiensClient {

	private static final int UNPROCESSABLE_ENTITY = 422;
	private static final String USER = "user";
	private static final String PW = "password";
	private static final String TOKEN = "Authorization";
	private String token;

	public RestSwSapiensClientImpl(String url, String contextPath) {
		super(url, contextPath);
	}

	private static final Logger log = LoggerFactory.getLogger(RestSwSapiensClientImpl.class);

	@Override
	protected <T> T parseResponse(Response response, TypeReference<T> entityType) throws SwSapiensClientException {
		T result = null;
		int status = response.getStatus();
		log.info("Status {}", status);
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
			throw new SwSapiensClientException(error, UNPROCESSABLE_ENTITY);
		} catch (SwSapiensClientException e) {
			throw e;
		} catch (Exception e) {
			SwSapiensErrorMessage error = new SwSapiensErrorMessage();
			error.setMessage("Unhandled Error in client implementation");
			error.setMessageDetail(e.getMessage());
			throw new SwSapiensClientException(error, UNPROCESSABLE_ENTITY);
		}
		return result;
	}

	@Override
	public SwSapiensConfig authenticate(String usr, String pw) throws SwSapiensClientException {
		log.info("Creating associate pre stage.");
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(USER, usr);
		headers.add(PW, pw);
		String endpoint = SwSapiensEndpoints.getAuthenticateEndpoint();
		Response response = post(endpoint, MediaType.APPLICATION_JSON, null, headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}

	@Override
	public SwSapiensConfig validateRfc(String rfc) throws SwSapiensClientException {
		log.info("Creating associate pre stage.");
		authenticate();
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(TOKEN, token);
		String endpoint = SwSapiensEndpoints.getValidateRfcEndpoint(rfc);
		Response response = get(endpoint, MediaType.APPLICATION_JSON, headers);
		return parseResponse(response, new TypeReference<SwSapiensConfig>() {
		});
	}

	private void authenticate() throws SwSapiensClientException {
		if (token == null) {
			SwSapiensConfig config = authenticate("demo", "123456789");
			token = config.getData().getToken();
		}
	}

	public static void main(String[] args) {
		RestSwSapiensClientImpl client = new RestSwSapiensClientImpl("http://services.test.sw.com.mx", "");
		try {
			SwSapiensConfig result = client.validateRfc("RASE8810158B9");
			System.out.println(result);
		} catch (SwSapiensClientException e) {
			e.printStackTrace();
		}
	}

}
