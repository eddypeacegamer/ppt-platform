package com.business.unknow.client.model.facturacionmoderna;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.business.unknow.Constants.FacturacionModernaRequest;
import com.business.unknow.model.error.InvoiceCommonException;

public class SoapRequest {

	public String createSoapEnvelope(FacturaModernaRequestModel requestModel) throws InvoiceCommonException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPPart soapPart = soapMessage.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("ns1", "https://t1demo.facturacionmoderna.com/timbrado/soap");
			envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
			envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
			envelope.addNamespaceDeclaration("SOAP-ENV", "http://schemas.xmlsoap.org/soap/encoding/");
			SOAPBody soapBody = envelope.getBody();
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			SOAPBodyElement element = soapBody.addBodyElement(envelope.createName("requestTimbrarCFDI", "ns1", ""));
			SOAPElement element2 = element.addChildElement("request")
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER), "SOAP-ENC:Struct");
			element2.addChildElement(FacturacionModernaRequest.USER_PASS_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.getUserPass());
			element2.addChildElement(FacturacionModernaRequest.USER_ID_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.getUser());
			element2.addChildElement(FacturacionModernaRequest.EMISOR_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.getRfc());
			element2.addChildElement(FacturacionModernaRequest.TEXT_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.getXml());
			element2.addChildElement(FacturacionModernaRequest.GENERAR_TEXT_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.BOOLEAN_TYPE_PARAMETER)
					.addTextNode(requestModel.isGenerarTxt().toString());
			element2.addChildElement(FacturacionModernaRequest.GENERAR_PDF_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.isGenerarPdf().toString());
			element2.addChildElement(FacturacionModernaRequest.GENERAR_CBB_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.BOOLEAN_TYPE_PARAMETER)
					.addTextNode(requestModel.isGenerarCbb().toString());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			soapMessage.writeTo(baos);
			return baos.toString();
		} catch (SOAPException | IOException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}

}
