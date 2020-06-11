package com.business.unknow.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.PreencodedMimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.config.EmailConfig;
import com.business.unknow.model.config.FileConfig;
import com.business.unknow.model.error.InvoiceCommonException;

public class MailHelper {

	public void enviarCorreo(EmailConfig emailConfig) throws InvoiceCommonException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", emailConfig.getDominio());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.user", emailConfig.getEmisor());
	    props.put("mail.smtp.clave", emailConfig.getPwEmisor());
		props.put("mail.smtp.ssl.trust", emailConfig.getDominio());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", emailConfig.getPort());

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getEmisor(), emailConfig.getPwEmisor());
			}
		});
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(emailConfig.getEmisor()));
			for (String receptor : emailConfig.getReceptor()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("inv-manager@ovjme.com"));
			message.setSubject(emailConfig.getAsunto());
			message.setText(emailConfig.getCuerpo());
			if (emailConfig.getArchivos() != null) {
				Multipart multipart = new MimeMultipart();
				BodyPart text = new MimeBodyPart();
				text.setContent(emailConfig.getCuerpo(), TipoArchivoEnum.TXT.getByteArrayData());
				multipart.addBodyPart(text);
				for (FileConfig file : emailConfig.getArchivos()) {
					BodyPart fileBodyPart = new PreencodedMimeBodyPart("base64");
					fileBodyPart.setText(file.getTipòArchivo().getDescripcion());
					ByteArrayDataSource rawData = new ByteArrayDataSource(file.getBase64Content().getBytes(),
							file.getTipòArchivo().getByteArrayData());
					fileBodyPart.setFileName(file.getNombre().concat(file.getTipòArchivo().getFormat()));
					fileBodyPart.setDataHandler(new DataHandler(rawData));
					multipart.addBodyPart(fileBodyPart);
				}
				message.setContent(multipart);
			}

			Transport transport = session.getTransport("smtp");
			transport.connect(emailConfig.getDominio(), emailConfig.getEmisor(), emailConfig.getPwEmisor());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
			throw new InvoiceCommonException(String.format("Error mandando Email de %s para %s",
					emailConfig.getEmisor(), emailConfig.getReceptor()), me.getMessage());
		}
	}
	
	public static void main(String[] args) throws InvoiceCommonException {
		MailHelper mh = new MailHelper();
		EmailConfig ec= new EmailConfig();
		ec.setEmisor("re-envio_facts@semmeljack.com");
		ec.setDominio("smtp.gmail.com");
		ec.setPwEmisor("D3s4rr0ll0-2021.*");
		
//		ec.setEmisor("facturacion01@blakeintegral.com");
//		ec.setDominio("p3plcpnl1041.prod.phx3.secureserver.net");
//		ec.setPwEmisor("D3s4rr0ll0-2020.*");
		List<String> lista = new ArrayList<String>();
		lista.add("edcgamer@gmail.com");
		ec.setReceptor(lista);
 		ec.setPort("587");
 		ec.setAsunto("eddy");
 		ec.setCuerpo("te amo");
 		mh.enviarCorreo(ec);
 		
	}
	
	
}