package com.business.unknow.commons.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.user", emailConfig.getEmisor());
		props.put("mail.smtp.clave", emailConfig.getPwEmisor());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(emailConfig.getEmisor()));
			for (String receptor : emailConfig.getReceptor()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			}
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
			transport.connect("smtpout.secureserver.net", emailConfig.getEmisor(), emailConfig.getPwEmisor());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
			throw new InvoiceCommonException(String.format("Error mandando Email de %s para %s",
					emailConfig.getEmisor(), emailConfig.getReceptor()), me.getMessage());
		}
	}
	
}