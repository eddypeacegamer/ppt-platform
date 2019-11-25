package com.business.unknow.commons.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.error.InvoiceCommonException;

public class FacturaHelper {

	public String facturaCfdiToXml(Cfdi cfdi) throws InvoiceCommonException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Cfdi.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(cfdi, sw);
			return sw.toString();
		} catch (JAXBException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}
}
