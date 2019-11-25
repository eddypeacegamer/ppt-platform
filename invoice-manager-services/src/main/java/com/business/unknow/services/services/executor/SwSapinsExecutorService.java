package com.business.unknow.services.services.executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;
import com.business.unknow.client.model.swsapiens.SwSapiensVersionEnum;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.files.FacturaFileDto;
import com.business.unknow.services.client.SwSapiensClient;

@Service
public class SwSapinsExecutorService {

	@Autowired
	private SwSapiensClient swSapiensClient;

	@Autowired
	private FileHelper fileHelper;

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		SwSapiensClient swSapiensClient = new SwSapiensClient();
		try {
			SwSapiensConfig swSapiensConfig = swSapiensClient.getSwSapiensClient().stamp(context.getXml(),
					SwSapiensVersionEnum.V4.getValue());
			context.getFacturaDto().setFechaTimbrado(swSapiensConfig.getData().getFechaTimbrado());
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.TIMBRADA.getValor());
			context.getFacturaDto().setUuid(swSapiensConfig.getData().getUuid());
			context.getFacturaDto().getCfdi().setSelloSat(swSapiensConfig.getData().getSelloSAT());
			context.getFacturaDto().getCfdi().setNoCertificadoSat(swSapiensConfig.getData().getNoCertificadoSAT());
			context.getFacturaDto().getCfdi().setSelloCfd(swSapiensConfig.getData().getSelloCFDI());
			context.getFacturaDto().getCfdi().setSello(swSapiensConfig.getData().getSelloCFDI());
			List<FacturaFileDto> files = new ArrayList<>();
			FacturaFileDto qr = new FacturaFileDto();
			qr.setFolio(context.getFacturaDto().getFolio());
			qr.setTipoArchivo(TipoArchivoEnum.QR.getDescripcion());
			qr.setData(swSapiensConfig.getData().getQrCode());

			FacturaFileDto xml = new FacturaFileDto();
			xml.setFolio(context.getFacturaDto().getFolio());
			xml.setTipoArchivo(TipoArchivoEnum.XML.getDescripcion());
			xml.setData(fileHelper.stringToBase64(swSapiensConfig.getData().getCfdi()));
			FacturaFileDto pdf = new FacturaFileDto();
			pdf.setFolio(context.getFacturaDto().getFolio());
			pdf.setTipoArchivo(TipoArchivoEnum.PDF.getDescripcion());
			pdf.setData(new String(Files.readAllBytes(Paths.get(FacturaConstants.FACTURA_DUMMY))));

			files.add(qr);
			files.add(xml);
			files.add(pdf);
			context.setFacturaFilesDto(files);
		} catch (SwSapiensClientException e) {
			throw new InvoiceManagerException("Error durante el timbrado", e.getMessage(), HttpStatus.SC_CONFLICT);
		} catch (IOException e) {
			throw new InvoiceManagerException("Error durante la creacion de archivos", e.getMessage(),
					HttpStatus.SC_CONFLICT);
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
