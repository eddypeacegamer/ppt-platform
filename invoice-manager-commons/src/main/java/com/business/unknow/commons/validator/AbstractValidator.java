package com.business.unknow.commons.validator;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;

public class AbstractValidator {

	protected static final String ATTRIBUTE_REQUIRED = "Attribute required.";
	protected static final String ATTRIBUTE_REQUIRED_MESSAGE = "Error, el campo [%s] no fue enviado.";

	protected void checkNotNull(Object var, String attribute) throws InvoiceManagerException {
		if (var == null) {
			throw new InvoiceManagerException(String.format(ATTRIBUTE_REQUIRED_MESSAGE, attribute),ATTRIBUTE_REQUIRED,
					Constants.BAD_REQUEST);
		}
	}
}
