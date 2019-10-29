package com.business.unknow.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;

public class FacturaCalculator {
	private final static String DATE_FORMAT = "yyyy-MM-dd-hh:mm:ss";

	public static String folioEncrypt(FacturaDto dto) throws InvoiceManagerException {
		SimpleDateFormat dt1 = new SimpleDateFormat(DATE_FORMAT);
		String cadena = String.format("%s|%s|%s", dto.getRfcEmisor(), dto.getRfcRemitente(),
				dt1.format(dto.getFechaCreacion()));
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(cadena.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new InvoiceManagerException(e.getMessage(), e.getCause().toString(), Constants.INTERNAL_ERROR);
		}
	}
	
	public static void assignFolioInFacturaDto(FacturaDto dto) throws InvoiceManagerException {
		String folio =folioEncrypt(dto);
		dto.setFolio(folio);
		if(dto.getCfdi()!=null) {
			dto.getCfdi().setFolio(folio);
		}
	}

}
