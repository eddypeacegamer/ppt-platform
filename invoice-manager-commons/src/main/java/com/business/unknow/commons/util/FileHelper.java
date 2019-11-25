package com.business.unknow.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import com.business.unknow.model.error.InvoiceCommonException;

public class FileHelper {

	public String readFile(String path, String fileName) throws InvoiceCommonException {
		try {
			return new String(Files.readAllBytes(Paths.get(path.concat(fileName))));
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvoiceCommonException(e.getMessage());
		}
	}

	public String stringToBase64(String cadena) {
		return Base64.getEncoder().encodeToString(cadena.getBytes());
	}
}
