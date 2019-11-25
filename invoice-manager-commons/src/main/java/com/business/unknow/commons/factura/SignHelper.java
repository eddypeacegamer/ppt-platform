package com.business.unknow.commons.factura;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.ssl.PKCS8Key;
import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.model.error.InvoiceCommonException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class SignHelper {

	private String getKey(String key) throws InvoiceCommonException {
		byte[] decode =Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
		return DatatypeConverter.printBase64Binary(decode);
	}

	public String getSign(String cadena, String keyWord,String key) throws InvoiceCommonException {
		try {
			String archivoLlavePrivada = this.getKey(key);
			InputStream myInputStream = new ByteArrayInputStream(
					DatatypeConverter.parseBase64Binary(archivoLlavePrivada));
			PKCS8Key pkcs8 = new PKCS8Key(myInputStream, keyWord.toCharArray());
			PrivateKey pk = pkcs8.getPrivateKey();
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initSign(pk);
			signature.update(cadena.getBytes("UTF-8"));
			return new String(DatatypeConverter.printBase64Binary(signature.sign()));
		} catch (GeneralSecurityException | IOException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}

	public String getCadena(String xml) throws InvoiceCommonException {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Source xslt = new StreamSource(new File(FacturaConstants.CADENA_ORIGINAL));
			Transformer transformer = transformerFactory.newTransformer(xslt);
			Source xmlSource = new StreamSource(new StringReader(xml));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Result out = new StreamResult(baos);
			transformer.transform(xmlSource, out);
			byte[] cadenaOriginalArray = baos.toByteArray();
			String cadOrig = new String(cadenaOriginalArray, FacturaConstants.SYSTEM_CODIFICATION);
			return cadOrig;
		} catch (UnsupportedEncodingException | TransformerException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}
}