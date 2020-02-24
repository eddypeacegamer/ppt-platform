package com.business.unknow.services.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sw")
public class SwProperties {

	@Value("${sw.username}")
	private String user;
	@Value("${sw.password}")
	private String password;
	@Value("${sw.host}")
	private String host;
	@Value("${sw.context}")
	private String context;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "SwProperties [user=" + user + ", password=" + password + ", host=" + host + ", context=" + context
				+ "]";
	}

}
