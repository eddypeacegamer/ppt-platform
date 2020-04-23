package com.business.unknow.client.ntlink.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.apache.http.HttpStatus;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.business.unknow.Constants.NtlinkModernaRequest;
import com.business.unknow.client.ntlink.model.NtlinkCancelRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkClientException;
import com.business.unknow.client.ntlink.model.NtlinkErrorMessage;
import com.business.unknow.client.ntlink.model.NtlinkRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkResponseModel;

public class NtlinkMessageParser {

	public String createCancelRequest(NtlinkCancelRequestModel requestModel) throws NtlinkClientException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPElement element = createStampHeaders(soapMessage, NtlinkModernaRequest.CANCELA_CFDI);
			element.addChildElement(NtlinkModernaRequest.USER, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getUser());
			element.addChildElement(NtlinkModernaRequest.PASS, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getUserPass());
			element.addChildElement(NtlinkModernaRequest.UUID, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getUuid());
			element.addChildElement(NtlinkModernaRequest.RFC_EMISOR, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getRfcEmisor());
			element.addChildElement(NtlinkModernaRequest.EXPRESION, NtlinkModernaRequest.ISER)
			.addTextNode(requestModel.getExpresion());
			element.addChildElement(NtlinkModernaRequest.RFC_RECEPTOR, NtlinkModernaRequest.ISER)
			.addTextNode(requestModel.getRfcReceptor());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			soapMessage.writeTo(baos);
			System.out.println(baos.toString());
			return baos.toString();
		} catch (SOAPException | IOException e) {
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error creating Ntlink Cancelar Message", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	public String createStampRequest(NtlinkRequestModel requestModel) throws NtlinkClientException {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPElement element = createStampHeaders(soapMessage, NtlinkModernaRequest.TIMBRA_CFDI);
			element.addChildElement(NtlinkModernaRequest.USER, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getUser());
			element.addChildElement(NtlinkModernaRequest.PASS, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getUserPass());
			element.addChildElement(NtlinkModernaRequest.COMPROBANTE, NtlinkModernaRequest.ISER)
					.addTextNode(requestModel.getXml());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			soapMessage.writeTo(baos);
			return baos.toString();
		} catch (SOAPException | IOException e) {
			throw new NtlinkClientException(
					new NtlinkErrorMessage("Error creating  Ntlink Stamp Message", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	private SOAPBodyElement createStampHeaders(SOAPMessage soapMessage, String requestType) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(NtlinkModernaRequest.ISER, NtlinkModernaRequest.ISER_URL);
		envelope.addNamespaceDeclaration(NtlinkModernaRequest.SOAP_ENV, NtlinkModernaRequest.SOAP_ENV_URL);
		SOAPBody soapBody = envelope.getBody();
		return soapBody.addBodyElement(envelope.createName(requestType, NtlinkModernaRequest.ISER, ""));
	}

	public NtlinkResponseModel getErrorResponse(String errorResponse) throws NtlinkClientException {
		try {
			InputStream is = new ByteArrayInputStream(errorResponse.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			SOAPBody soapBody = request.getSOAPBody();
			SOAPElement responseSoap = (SOAPElement) soapBody.getChildElements().next();
			NtlinkResponseModel response = unmarshallError(responseSoap);
			return response;
		} catch (IOException | SOAPException | JAXBException e) {
			e.printStackTrace();
			throw new NtlinkClientException(new NtlinkErrorMessage("Error parsing Error Response", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	public <T> T getResponse(String stringResponse, Class<T> clazz) throws NtlinkClientException {
		try {
			stringResponse = stringResponse.replace("a:", "");
			stringResponse = stringResponse.replace("i:nil=\"true\"", "");
			stringResponse = stringResponse.replace("i:nil=\"false\"", "");
			stringResponse = stringResponse.replace("xmlns=\"https://ntlink.com.mx/IServicioTimbrado\"", "");
			stringResponse = stringResponse.replace("xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
			stringResponse = stringResponse
					.replace("xmlns:a=\"http://schemas.datacontract.org/2004/07/CertificadorWs\"", "");
			stringResponse = stringResponse.replace("i:nil=\"false\"", "");
			InputStream is = new ByteArrayInputStream(stringResponse.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			SOAPBody soapBody = request.getSOAPBody();
			SOAPElement responseTimbrar = (SOAPElement) soapBody.getChildElements().next();
			SOAPElement responsefull = (SOAPElement) responseTimbrar.getChildElements().next();
			return unmarshall(responsefull, clazz);
		} catch (IOException | SOAPException | JAXBException e) {
			e.printStackTrace();
			throw new NtlinkClientException(new NtlinkErrorMessage("Error parsing Response", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}
	
	public <T> T getResponseCancel(String stringResponse, Class<T> clazz) throws NtlinkClientException {
		try {
			stringResponse = stringResponse.replace("a:", "");
			stringResponse = stringResponse.replace("i:nil=\"true\"", "");
			stringResponse = stringResponse.replace("i:nil=\"false\"", "");
			stringResponse = stringResponse.replace("xmlns=\"https://ntlink.com.mx/IServicioTimbrado\"", "");
			stringResponse = stringResponse
					.replace("xmlns:a=\"http://schemas.datacontract.org/2004/07/CertificadorWs\"", "");
			stringResponse = stringResponse.replace("i:nil=\"false\"", "");
			InputStream is = new ByteArrayInputStream(stringResponse.getBytes());
			SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			SOAPBody soapBody = request.getSOAPBody();
			SOAPElement responseTimbrar = (SOAPElement) soapBody.getChildElements().next();
			return unmarshall(responseTimbrar, clazz);
		} catch (IOException | SOAPException | JAXBException e) {
			e.printStackTrace();
			throw new NtlinkClientException(new NtlinkErrorMessage("Error parsing Response", e.getMessage()),
					HttpStatus.SC_CONFLICT);
		}
	}

	protected NtlinkResponseModel unmarshallError(SOAPElement xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(NtlinkResponseModel.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		NtlinkResponseModel obj = unmarshaller.unmarshal(xml, NtlinkResponseModel.class).getValue();
		return obj;
	}

	protected <T> T unmarshall(SOAPElement xml, Class<T> clazz) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		T obj = clazz.cast(unmarshaller.unmarshal(xml));
		return obj;
	}

}
