package com.business.unknow.client.common;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClient {

	private String url;
	private String contextPath;

	private Logger log = LoggerFactory.getLogger(AbstractClient.class);

	protected AbstractClient(String url, String contextPath) {
		this.url = url;
		this.contextPath = contextPath;
	}

	protected AbstractClient(String url, String contextPath, String username, String password) {
		this.url = url;
		this.contextPath = contextPath;
	}

	public AbstractClient(String url, String contextPath, int maxRetries, int delay, TimeUnit unit) {
		this.url = url;
		this.contextPath = contextPath;
	}

	protected WebTarget createClient(String path) {
		String assembledPath = assembleEndpoint(path);
		log.info(assembledPath);
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 600000);
		client.property(ClientProperties.READ_TIMEOUT, 600000);
		WebTarget target = client.target(assembledPath);
		return target;
	}

	private String assembleEndpoint(String path) {
		return url.concat(contextPath).concat(path);
	}

	protected Response get(String endpoint, String type) {
		WebTarget client = createClient(endpoint);
		return client.request(type).get();
	}

	protected Response get(String endpoint, String type, MultivaluedMap<String, Object> headers) {
		WebTarget client = createClient(endpoint);
		return client.request(type).headers(headers).get();
	}

	protected Response put(String endpoint, String type, Object entity) {
		WebTarget client = createClient(endpoint);
		return client.request(type).put(Entity.entity(entity, type));
	}

	protected Response put(String endpoint, String type, Object entity, MultivaluedMap<String, Object> headers) {
		WebTarget client = createClient(endpoint);
		return client.request(type).headers(headers).put(Entity.entity(entity, type));
	}

	protected Response post(String endpoint, String type, Object entity) {
		WebTarget client = createClient(endpoint);
		return client.request(type).post(Entity.entity(entity, type));
	}

	protected Response post(String endpoint, String type, Object entity, MultivaluedMap<String, Object> headers) {
		WebTarget client = createClient(endpoint);
		return client.request(type).headers(headers).post(Entity.entity(entity, type));
	}

}
