package com.business.unknow.commons.factura;

import static javax.xml.parsers.DocumentBuilderFactory.newInstance;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.model.error.InvoiceCommonException;

public class CdfiHelper {

	public boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public String changeDate(String xml, Date fechaTimbrado) throws InvoiceCommonException {
		try {
			SimpleDateFormat format = new SimpleDateFormat(FacturaConstants.FACTURA_DATE_FORMAT);
			String datetime = format.format(fechaTimbrado);
			DocumentBuilderFactory factory = newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().setAttribute(FacturaConstants.FECHA_ATTRIBUTE, datetime);
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}

	public String putsSign(String xml, String sello) throws InvoiceCommonException {
		try {
			DocumentBuilderFactory factory = newInstance();
			DocumentBuilder builder;
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().setAttribute(FacturaConstants.SELLO_ATTRIBUTE, sello);
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}

}