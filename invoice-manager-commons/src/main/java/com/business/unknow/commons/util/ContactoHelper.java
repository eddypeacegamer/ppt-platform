package com.business.unknow.commons.util;

public class ContactoHelper {

	public String translateContacto(String contactoCliente, String promotor) {
		StringBuilder sb = new StringBuilder();
		if(contactoCliente==null||contactoCliente.isEmpty()) {
			return null;
		}
		String[] contactos = contactoCliente.split(",");
		String[] promotores = promotor.split("@");
		int split=0;
		for (String contacto : contactos) {
			if(split>0) {
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

}
