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
import com.business.unknow.client.swsapiens.model.SwSapiensVersionEnum;
import com.business.unknow.client.swsapiens.util.SwSapiensClientException;
import com.business.unknow.client.swsapiens.util.SwSapiensConfig;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.error.InvoiceManagerException;
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
			context.getFacturaDto().getCfdi().getComplemento()
					.setFechaTimbrado(swSapiensConfig.getData().getFechaTimbrado());
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.TIMBRADA.getValor());
			context.getFacturaDto().setUuid(swSapiensConfig.getData().getUuid());
			context.getFacturaDto().getCfdi().getComplemento().setUuid(swSapiensConfig.getData().getUuid());
			context.getFacturaDto().getCfdi().getComplemento().setSelloSat(swSapiensConfig.getData().getSelloSAT());
			context.getFacturaDto().getCfdi().getComplemento()
					.setNoCertificadoSat(swSapiensConfig.getData().getNoCertificadoSAT());
			context.getFacturaDto().getCfdi().getComplemento().setSelloCFD(swSapiensConfig.getData().getSelloCFDI());
			context.getFacturaDto().getCfdi().setSello(swSapiensConfig.getData().getSelloCFDI());
			List<FacturaFileDto> files = new ArrayList<>();
			FacturaFileDto qr = new FacturaFileDto();
			qr.setFolio(context.getFacturaDto().getFolio());
			qr.setTipoArchivo(TipoArchivoEnum.QR.name());
			qr.setData(swSapiensConfig.getData().getQrCode());
			FacturaFileDto xml = new FacturaFileDto();
			xml.setFolio(context.getFacturaDto().getFolio());
			xml.setTipoArchivo(TipoArchivoEnum.XML.name());
			xml.setData(fileHelper.stringEncodeBase64(swSapiensConfig.getData().getCfdi()));
			FacturaFileDto pdf = new FacturaFileDto();
			pdf.setFolio(context.getFacturaDto().getFolio());
			pdf.setTipoArchivo(TipoArchivoEnum.PDF.name());
			pdf.setData(new String(Files.readAllBytes(Paths.get(FacturaConstants.FACTURA_DUMMY))));
			files.add(qr);
			files.add(xml);
			files.add(pdf);
			context.setFacturaFilesDto(files);
		} catch (SwSapiensClientException e) {
			e.printStackTrace();
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

	public SwSapiensConfig validateLco(String noCertificado) throws SwSapiensClientException {
		return swSapiensClient.getSwSapiensClient().validateLco(noCertificado);
	}

	public FacturaContext cancelarFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			swSapiensClient.getSwSapiensClient().cancel(context.getFacturaDto().getUuid(),
					context.getEmpresaDto().getPwSat(), context.getEmpresaDto().getInformacionFiscal().getRfc(),
					context.getEmpresaDto().getCertificado(), context.getEmpresaDto().getLlavePrivada());
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.CANCELADA.getValor());
			context.getFacturaDto().setFechaCancelacion(new Date());
			return context;
		} catch (SwSapiensClientException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(
					String.format("Error durante el Cancelado de :%s", context.getFacturaDto().getUuid()),
					e.getMessage(), HttpStatus.SC_CONFLICT);
		}
	}
}
