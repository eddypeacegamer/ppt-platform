package com.business.unknow.commons.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;

public class AbstractValidator {

	protected static final String ATTRIBUTE_REQUIRED = "Attribute required.";
	protected static final String ATTRIBUTE_REQUIRED_MESSAGE = "Error, el campo [%s] no fue enviado.";
	private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String CHAR_REGEX = "^[A-Za-z0-9ÁÉÍÓÚáéíóú.,&\\-\\s]+";

	protected void checkNotNull(Object var, String attribute) throws InvoiceManagerException {
		if (var == null) {
			throw new InvoiceManagerException(String.format(ATTRIBUTE_REQUIRED_MESSAGE, attribute), ATTRIBUTE_REQUIRED,
					Constants.BAD_REQUEST);
		}
	}

	protected void checkNotEmpty(String var, String attribute) throws InvoiceManagerException {
		if (var.isEmpty()) {
			throw new InvoiceManagerException(String.format(ATTRIBUTE_REQUIRED_MESSAGE, attribute), ATTRIBUTE_REQUIRED,
					Constants.BAD_REQUEST);
		}
	}
	
	protected void checkNotEquals(String var, String comp) throws InvoiceManagerException {
		if (var.equals(comp)) {
			throw new InvoiceManagerException(String.format("La cadena %s no debe ser igual a %s",var,comp), ATTRIBUTE_REQUIRED,
					Constants.BAD_REQUEST);
		}
	}

	protected void checkValidEmail(String email) throws InvoiceManagerException {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new InvoiceManagerException(String.format("El correo %s: es incorrecto", email),
					String.format("El correo %s: es incorrecto", email), Constants.BAD_REQUEST);
		}
	}

	protected void checkValidString(String cadena) throws InvoiceManagerException {
		Pattern pattern = Pattern.compile(CHAR_REGEX);
		Matcher matcher = pattern.matcher(cadena);
		if (!matcher.matches()) {
			throw new InvoiceManagerException(String.format("La cadena %s: es incorrecta", cadena),
					String.format("La cadena %s: es incorrecta", cadena), Constants.BAD_REQUEST);
		}
	}

}
