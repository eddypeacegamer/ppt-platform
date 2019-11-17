package com.business.unknow.commons.factura;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.ssl.PKCS8Key;
import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.model.error.InvoiceCommonException;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;

public class SignHelper {

	private String getKey() throws InvoiceCommonException {
		try {
			File file = new File(FacturaConstants.EMPRESA_CERT_PRUEBAS);
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] fileBytes = new byte[fileInputStream.available()];
			fileInputStream.read(fileBytes);
			fileInputStream.close();
			return DatatypeConverter.printBase64Binary(fileBytes);
		} catch (IOException e) {
			throw new InvoiceCommonException(e.getMessage());
		}
	}

	public String getSign(String cadena, String keyWord) throws InvoiceCommonException {
		try {
			String archivoLlavePrivada = this.getKey();
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