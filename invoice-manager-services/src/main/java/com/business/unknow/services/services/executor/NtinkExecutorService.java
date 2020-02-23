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
import com.business.unknow.client.ntlink.model.NtlinkCancelRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkClientException;
import com.business.unknow.client.ntlink.model.NtlinkRequestModel;
import com.business.unknow.client.ntlink.model.NtlinkResponseModel;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.client.NtlinkClient;

@Service
public class NtinkExecutorService {

	@Autowired
	private NtlinkClient client;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private FileHelper fileHelper;

	@Autowired
	private DateHelper dateHelper;

	private static final String USR = "edcgamer@gmail.com";
	private static final String PW = "Factura.2020";

	public FacturaContext cancelarFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			NtlinkCancelRequestModel requestModel = new NtlinkCancelRequestModel(USR, PW,
					context.getFacturaDto().getUuid(), context.getFacturaDto().getRfcEmisor(),
					context.getFacturaDto().getRfcRemitente());
			client.getNtlinkClient().cancelar(requestModel);
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.CANCELADA.getValor());
			context.getFacturaDto().setFechaCancelacion(new Date());
			return context;
		} catch (NtlinkClientException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(
					String.format("Error durante el Cancelado de :%s", context.getFacturaDto().getUuid()),
					e.getMessage(), e.getHttpStatus());
		}
	}

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		try {
			NtlinkRequestModel requestModel = new NtlinkRequestModel(USR, PW, context.getXml());
			NtlinkResponseModel response = client.getNtlinkClient().stamp(requestModel);
			String cfdi = response.getCfdi();
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.TIMBRADA.getValor());
			Cfdi currentCfdi = facturaHelper.getFacturaFromString(cfdi);
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setFechaTimbrado(dateHelper.getDateFromString(
							currentCfdi.getComplemento().getTimbreFiscalDigital().getFechaTimbrado(),
							FacturaConstants.FACTURA_DATE_FORMAT));
			context.getFacturaDto().setUuid(currentCfdi.getComplemento().getTimbreFiscalDigital().getUuid());
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setUuid(currentCfdi.getComplemento().getTimbreFiscalDigital().getUuid());
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setSelloSat(currentCfdi.getComplemento().getTimbreFiscalDigital().getSelloSAT());
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setNoCertificadoSat(currentCfdi.getComplemento().getTimbreFiscalDigital().getNoCertificadoSAT());
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setSelloCFD(currentCfdi.getComplemento().getTimbreFiscalDigital().getSelloCFD());
			context.getFacturaDto().getCfdi().setSello(currentCfdi.getSello());
			context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal()
					.setRfcProvCertif(currentCfdi.getComplemento().getTimbreFiscalDigital().getRfcProvCertif());
			List<FacturaFileDto> files = new ArrayList<>();
			if (response.getCfdi() != null) {
				FacturaFileDto xml = new FacturaFileDto();
				xml.setFolio(context.getFacturaDto().getFolio());
				xml.setTipoArchivo(TipoArchivoEnum.XML.name());
				xml.setData(fileHelper.stringEncodeBase64(response.getCfdi()));
				files.add(xml);
			}
			FacturaFileDto pdf = new FacturaFileDto();
			pdf.setFolio(context.getFacturaDto().getFolio());
			pdf.setTipoArchivo(TipoArchivoEnum.PDF.name());
			pdf.setData(new String(Files.readAllBytes(Paths.get(FacturaConstants.FACTURA_DUMMY))));
			files.add(pdf);
			context.setFacturaFilesDto(files);
		} catch (NtlinkClientException | InvoiceCommonException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(e.getMessage(),
					String.format("Error Stamping in facturacion moderna: %s", e.getLocalizedMessage()),
					HttpStatus.SC_CONFLICT);
		} catch (IOException e2) {
			throw new InvoiceManagerException(e2.getMessage(),
					String.format("Error setting file: %s", e2.getLocalizedMessage()), HttpStatus.SC_CONFLICT);
		}
		return context;
	}

}
