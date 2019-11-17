package com.business.unknow.services.services.executor;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;
import com.business.unknow.client.model.swsapiens.SwSapiensVersionEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.client.SwSapiensClient;

@Service
public class SwSapinsExecutorService {

	@Autowired
	private SwSapiensClient swSapiensClient;

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		SwSapiensClient swSapiensClient = new SwSapiensClient();
		try {
			SwSapiensConfig swSapiensConfig = swSapiensClient.getSwSapiensClient().stamp(context.getXml(),
					SwSapiensVersionEnum.V4.getValue());
			context.getFacturaDto().setFechaTimbrado(swSapiensConfig.getData().getFechaTimbrado());
			context.getFacturaDto().setUuid(swSapiensConfig.getData().getUuid());
			context.getFacturaDto().getCfdi().setSelloSat(swSapiensConfig.getData().getSelloSAT());
			context.getFacturaDto().getCfdi().setNoCertificadoSat(swSapiensConfig.getData().getNoCertificadoSAT());
			context.getFacturaDto().getCfdi().setSelloCfd(swSapiensConfig.getData().getSelloCFDI());
			context.getFacturaDto().getCfdi().setSello(swSapiensConfig.getData().getSelloCFDI());
			context.setQr(swSapiensConfig.getData().getQrCode());
		} catch (SwSapiensClientException e) {
			throw new InvoiceManagerException("Error durante el timbrado", e.getMessage(), HttpStatus.SC_CONFLICT);
		}
		return context;
	}

	public SwSapiensConfig validateRfc(String rfc) throws SwSapiensClientException {
		return swSapiensClient.getSwSapiensClient().validateRfc(rfc);
	}

	public FacturaContext timbraComplemento(FacturaContext context) {
		context.getFacturaDto().setUuid(generateRandomUuid(14));
		context.getFacturaDto().setFechaTimbrado(new Date());
		context.getFacturaDto().setStatusFactura(3);
		return context;
	}

	public FacturaContext cancelarFactura(FacturaContext context) {
		context.getFacturaDto().setStatusFactura(6);
		return context;
	}

	private String generateRandomUuid(int count) {
		String cadenaAplha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * cadenaAplha.length());
			builder.append(cadenaAplha.charAt(character));
		}
		return builder.toString();
	}
}
