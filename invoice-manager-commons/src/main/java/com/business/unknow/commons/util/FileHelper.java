package com.business.unknow.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
