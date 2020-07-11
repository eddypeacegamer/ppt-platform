package com.business.unknow.enums;

public enum TipoEmail {

	SEMEL_JACK("", "587","",""), GMAIL("smtp.gmail.com", "587","re-envio_facts@semmeljack.com","D3s4rr0ll0-2021.*");

	private String host;
	private String port;
	private String email;
	private String pw;

	private TipoEmail(String host, String port,String email,String pw) {
		this.host = host;
		this.port = port;
		this.email = email;
		this.pw = pw;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}

}