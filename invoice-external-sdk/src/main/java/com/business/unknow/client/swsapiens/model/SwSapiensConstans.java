package com.business.unknow.client.swsapiens.model;

public class SwSapiensConstans {
	public static final int UNPROCESSABLE_ENTITY = 422;
	public static final String USER = "user";
	public static final String PW = "password";
	public static final String TOKEN_PARAMETER = "Authorization";
	public static final String TOKEN_SEPARATOR = "--";
	public static final String TOKEN_ENTER = "\r\n--";
	public static final String TOKEN_SUFIX ="\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n";
	public static final String CONTENT_TYPE_PARAMETER = "Content-Type";
	public static final String CONTENT_TYPE_VALUE = "multipart/form-data; boundary=";
	public static final String CONTENT_DISPOSITION_PARAMETER = "Content-Disposition";
	public static final String CONTENT_DISPOSITION_VALUE = "form-data; name=xml; filename=xml";
}
