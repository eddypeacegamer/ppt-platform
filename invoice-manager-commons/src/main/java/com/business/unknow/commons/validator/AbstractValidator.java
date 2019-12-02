package com.business.unknow.commons.validator;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;

public class AbstractValidator {

	protected static final String ATTRIBUTE_REQUIRED = "Attribute required.";
	protected static final String ATTRIBUTE_REQUIRED_MESSAGE = "Error, attribute [%s] can't be null.";

	protected void checkNotNull(Object var, String attribute) throws InvoiceManagerException {
		if (var == null) {
			throw new InvoiceManagerException(ATTRIBUTE_REQUIRED, String.format(ATTRIBUTE_REQUIRED_MESSAGE, attribute),
					Constants.BAD_REQUEST);
		}
	}
}
