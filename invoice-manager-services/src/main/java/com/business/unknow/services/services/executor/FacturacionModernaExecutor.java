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
import com.business.unknow.services.config.properties.FacturacionModernaProperties;

@Service
public class FacturacionModernaExecutor extends AbstractPackExecutor {

	@Autowired
	private FacturacionModernaClient client;

	@Autowired
	private FileHelper fileHelper;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private DateHelper dateHelper;

	@Autowired
	private FacturacionModernaProperties fmProperties;

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		try {
			if (context.getTipoDocumento().equals(TipoDocumentoEnum.FACTURA.getDescripcion())) {
				context.setXml(context.getXml().replace("xmlns:pago10=\"http://www.sat.gob.mx/Pagos\"", ""));
			}
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(fmProperties.getUser(),
					fmProperties.getPassword(), context.getFacturaDto().getRfcEmisor(),
					fileHelper.stringEncodeBase64(context.getXml()), true, true, true);
			FacturaModernaResponseModel response = client.getFacturacionModernaClient(fmProperties.getHost(), "")
					.stamp(requestModel);
			String cfdi = fileHelper.stringDecodeBase64(response.getXml());
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.TIMBRADA.getValor());
			Cfdi currentCfdi = facturaHelper.getFacturaFromString(cfdi);
			context.getFacturaDto().setUuid(currentCfdi.getComplemento().getTimbreFiscalDigital().getUuid());
			context.getFacturaDto()
					.setFechaTimbrado(dateHelper.getDateFromString(
							currentCfdi.getComplemento().getTimbreFiscalDigital().getFechaTimbrado(),
							FacturaConstants.FACTURA_DATE_FORMAT));
			context.getFacturaDto().setCadenaOriginalTimbrado(getCadenaOriginalTimbrado(currentCfdi));
			context.getFacturaDto().getCfdi().setSello(currentCfdi.getSello());
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
			context.setFacturaFilesDto(files);
		} catch (FacturaModernaClientException | InvoiceCommonException e) {
			e.printStackTrace();
			throw new InvoiceManagerException(e.getMessage(),
					String.format("Error Stamping in facturacion moderna: %s", e.getLocalizedMessage()),
					HttpStatus.SC_CONFLICT);
		}
		return context;
	}

	public FacturaContext cancelarFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(fmProperties.getUser(),
					fmProperties.getPassword(), context.getFacturaDto().getRfcEmisor(),
					context.getFacturaDto().getUuid());
			client.getFacturacionModernaClient(fmProperties.getHost(), "").cancelar(requestModel);
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