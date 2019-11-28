package com.business.unknow.commons.util;

import java.io.IOException;
import java.io.InputStream;


import com.business.unknow.model.error.InvoiceCommonException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringHelper {

	private ObjectMapper objectMapper = new ObjectMapper();

	public String readStringFromFile(String path, String filename) throws InvoiceCommonException {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(String.format("menus/%s.json", filename));
			if (is != null) {

				return objectMapper.readValue(is, String.class);
			} else {
				throw new InvoiceCommonException(String.format("Error reading the file %s", filename));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvoiceCommonException(String.format("Error reading the file %s", e.getMessage()));
		}
	}

}