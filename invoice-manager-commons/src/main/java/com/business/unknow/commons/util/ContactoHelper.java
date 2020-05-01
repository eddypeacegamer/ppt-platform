package com.business.unknow.commons.util;

import java.math.BigDecimal;

public class ContactoHelper {

	public String translateContacto(String contactoCliente, String promotor) {
		StringBuilder sb = new StringBuilder();
		if (contactoCliente == null || contactoCliente.isEmpty()) {
			return null;
		}
		String[] contactos = contactoCliente.split(",");
		String[] promotores = promotor.split("@");
		int split = 0;
		for (String contacto : contactos) {
			if (split > 0) {
				sb.append(",");
			}
			if (contacto.contains("@")) {
				sb.append(contacto);
			} else {
				sb.append(contacto).append("@").append(promotores[0]).append(".com");
			}
			split++;
		}
		return sb.toString();
	}

	public String translateContacto(String rfc, String promotor, BigDecimal porcentajeContacto) {
		StringBuilder sb = new StringBuilder();
		if (porcentajeContacto.compareTo(BigDecimal.ZERO) > 0) {
			sb.append(rfc).append("_").append(promotor);
			return sb.toString();
		} else {
			return null;
		}

	}

}
