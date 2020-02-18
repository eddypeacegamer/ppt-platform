package com.business.unknow.services.services.executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaConstants;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaRequestModel;
import com.business.unknow.client.facturacionmoderna.model.FacturaModernaResponseModel;
import com.business.unknow.client.facturacionmoderna.util.FacturaModernaClientException;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.client.FacturacionModernaClient;

@Service
public class FacturacionModernaExecutor {

	@Autowired
	private FacturacionModernaClient client;

	@Autowired
	private FileHelper fileHelper;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private DateHelper dateHelper;

	private static final String USR = "UsuarioPruebasWS";
	private static final String PW = "b9ec2afa3361a59af4b4d102d3f704eabdf097d4";

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		try {
			if(context.getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())) {
				context.setXml(context.getXml().replace("xmlns:pago10=\"http://www.sat.gob.mx/Pagos\"", ""));
			}
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(USR, PW,
					context.getFacturaDto().getRfcEmisor(), fileHelper.stringEncodeBase64(context.getXml()), true, true,
					true);
			FacturaModernaResponseModel response = client.getFacturacionModernaClient().stamp(requestModel);
			String cfdi=fileHelper.stringDecodeBase64(response.getXml());
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
			if (response.getPng() != null) {
				FacturaFileDto qr = new FacturaFileDto();
				qr.setFolio(context.getFacturaDto().getFolio());
				qr.setTipoArchivo(TipoArchivoEnum.QR.name());
				qr.setData(response.getPng());
				files.add(qr);
			}
			if (response.getXml() != null) {
				FacturaFileDto xml = new FacturaFileDto();
				xml.setFolio(context.getFacturaDto().getFolio());
				xml.setTipoArchivo(TipoArchivoEnum.XML.name());
				xml.setData(response.getXml());
				files.add(xml);
			}
			if (response.getPdf() != null) {
				FacturaFileDto pdf = new FacturaFileDto();
				pdf.setFolio(context.getFacturaDto().getFolio());
				pdf.setTipoArchivo(TipoArchivoEnum.PDF.name());
				pdf.setData(response.getPdf());
				files.add(pdf);
			}
			context.setFacturaFilesDto(files);
		} catch (FacturaModernaClientException | InvoiceCommonException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(e.getMessage(),String.format("Error Stamping in facturacion moderna: %s",e.getLocalizedMessage()),
					HttpStatus.SC_CONFLICT);
		}
		return context;
	}

	public FacturaContext cancelarFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(USR, PW,
					context.getFacturaDto().getRfcEmisor(), context.getFacturaDto().getUuid());
			client.getFacturacionModernaClient().cancelar(requestModel);
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.CANCELADA.getValor());
			context.getFacturaDto().setFechaCancelacion(new Date());
			return context;
		} catch (FacturaModernaClientException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(
					String.format("Error durante el Cancelado de :%s", context.getFacturaDto().getUuid()),
					e.getMessage(), e.getHttpStatus());
		}
	}
}