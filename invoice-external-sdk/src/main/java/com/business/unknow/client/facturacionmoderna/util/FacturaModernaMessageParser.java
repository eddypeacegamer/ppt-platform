package com.business.unknow.client.facturacionmoderna.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.http.HttpStatus;
import com.business.unknow.Constants.FacturacionModernaRequest;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaErrorMessage;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaErrorModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaRequestModel;

public class FacturaModernaMessageParser {

	public String createCancelRequest(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			SOAPElement element = createStampHeaders(soapMessage, FacturacionModernaRequest.REQUEST_CANCELADO);
			SOAPElement element2 = element.addChildElement(FacturacionModernaRequest.REQUEST).addAttribute(
					soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
					FacturacionModernaRequest.SOAP_ENV_STRUCT);
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
			element2.addChildElement(FacturacionModernaRequest.UUID_PARAMETER)
					.addAttribute(soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
							FacturacionModernaRequest.STRING_TYPE_PARAMETER)
					.addTextNode(requestModel.getUuid());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			soapMessage.writeTo(baos);
			return baos.toString();
		} catch (SOAPException | IOException e) {
			throw new FacturaModernaClientException(
					new FacturaModernaErrorMessage("Error creating Factura moderna Stamp Message", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	public String createStampRequest(FacturaModernaRequestModel requestModel) throws FacturaModernaClientException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			SOAPElement element = createStampHeaders(soapMessage, FacturacionModernaRequest.REQUEST_TIMBRADO);
			SOAPElement element2 = element.addChildElement(FacturacionModernaRequest.REQUEST).addAttribute(
					soapFactory.createName(FacturacionModernaRequest.TYPE_PARAMETER),
					FacturacionModernaRequest.SOAP_ENV_STRUCT);
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
			throw new FacturaModernaClientException(
					new FacturaModernaErrorMessage("Error creating Factura moderna Stamp Message", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	private SOAPBodyElement createStampHeaders(SOAPMessage soapMessage, String requestType) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(FacturacionModernaRequest.NS1, FacturacionModernaRequest.URL_TIMBRADO);
		envelope.addNamespaceDeclaration(FacturacionModernaRequest.XSD, FacturacionModernaRequest.URL_SCHEMA);
		envelope.addNamespaceDeclaration(FacturacionModernaRequest.XSI, FacturacionModernaRequest.URL_SCHEMA_INSTANCE);
		envelope.addNamespaceDeclaration(FacturacionModernaRequest.SOAP_ENC, FacturacionModernaRequest.URL_ENCODING);
		envelope.addNamespaceDeclaration(FacturacionModernaRequest.SOAP_ENV, FacturacionModernaRequest.URL_ENCODING);
		SOAPBody soapBody = envelope.getBody();
		return soapBody.addBodyElement(envelope.createName(requestType, FacturacionModernaRequest.NS1, ""));
	}

	public FacturaModernaErrorModel getErrorResponse(String errorResponse) throws FacturaModernaClientException {
		try {
			InputStream is = new ByteArrayInputStream(errorResponse.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			SOAPBody soapBody = request.getSOAPBody();
			SOAPElement responseSoap = (SOAPElement) soapBody.getChildElements().next();
			FacturaModernaErrorModel response = unmarshallError(responseSoap);
			return response;
		} catch (IOException | SOAPException | JAXBException e) {
			e.printStackTrace();
			throw new FacturaModernaClientException(
					new FacturaModernaErrorMessage("Error parsing Error Response", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	public <T> T getResponse(String stringResponse, Class<T> clazz) throws FacturaModernaClientException {
		try {
			InputStream is = new ByteArrayInputStream(stringResponse.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			SOAPBody soapBody = request.getSOAPBody();
			SOAPElement responseTimbrar = (SOAPElement) soapBody.getChildElements().next();
			SOAPElement responsefull = (SOAPElement) responseTimbrar.getChildElements().next();
			return unmarshall(responsefull, clazz);
		} catch (IOException | SOAPException | JAXBException e) {
			throw new FacturaModernaClientException(
					new FacturaModernaErrorMessage("Error parsing Response", e.getMessage()), HttpStatus.SC_CONFLICT);
		}
	}

	protected FacturaModernaErrorModel unmarshallError(SOAPElement xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(FacturaModernaErrorModel.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		FacturaModernaErrorModel obj = unmarshaller.unmarshal(xml, FacturaModernaErrorModel.class).getValue();
		return obj;
	}

	protected <T> T unmarshall(SOAPElement xml, Class<T> clazz) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		T obj = clazz.cast(unmarshaller.unmarshal(xml));
		return obj;
	}

}
