package com.business.unknow.services.services.executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.client.model.facturacionmoderna.FacturaModernaCancelResponseModel;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaClientException;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaRequestModel;
import com.business.unknow.client.model.facturacionmoderna.FacturaModernaResponseModel;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.TipoArchivoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.files.FacturaFileDto;
import com.business.unknow.services.client.FacturacionModernaClient;

@Service
public class FacturacionModernaExecutor {

	@Autowired
	private FacturacionModernaClient client;

	private static final String USR = "UsuarioPruebasWS";
	private static final String PW = "b9ec2afa3361a59af4b4d102d3f704eabdf097d4";

	public FacturaContext stamp(FacturaContext context) throws InvoiceManagerException {
		try {
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(USR, PW,
					context.getFacturaDto().getRfcEmisor(), context.getXml(), true, true, true);
			FacturaModernaResponseModel response = client.getFacturacionModernaClient().stamp(requestModel);
//			context.getFacturaDto().setFechaTimbrado(response.getData().getFechaTimbrado());
			context.getFacturaDto().setStatusFactura(FacturaStatusEnum.TIMBRADA.getValor());
//			context.getFacturaDto().setUuid(swSapiensConfig.getData().getUuid());
//			context.getFacturaDto().getCfdi().setSelloSat(swSapiensConfig.getData().getSelloSAT());
//			context.getFacturaDto().getCfdi().setNoCertificadoSat(swSapiensConfig.getData().getNoCertificadoSAT());
//			context.getFacturaDto().getCfdi().setSelloCfd(swSapiensConfig.getData().getSelloCFDI());
//			context.getFacturaDto().getCfdi().setSello(swSapiensConfig.getData().getSelloCFDI());
			List<FacturaFileDto> files = new ArrayList<>();
			if (response.getPng() != null) {
				FacturaFileDto qr = new FacturaFileDto();
				qr.setFolio(context.getFacturaDto().getFolio());
				qr.setTipoArchivo(TipoArchivoEnum.QR.getDescripcion());
				qr.setData(response.getPng());
				files.add(qr);
			}
			if (response.getXml() != null) {
				FacturaFileDto xml = new FacturaFileDto();
				xml.setFolio(context.getFacturaDto().getFolio());
				xml.setTipoArchivo(TipoArchivoEnum.XML.getDescripcion());
				xml.setData(response.getXml());
				files.add(xml);
			}
			if (response.getXml() != null) {
				FacturaFileDto pdf = new FacturaFileDto();
				pdf.setFolio(context.getFacturaDto().getFolio());
				pdf.setTipoArchivo(TipoArchivoEnum.PDF.getDescripcion());
				pdf.setData(response.getPdf());
				files.add(pdf);
			}
			context.setFacturaFilesDto(files);
		} catch (FacturaModernaClientException e) {
			e.printStackTrace();
			throw new InvoiceManagerException("Error Stamping in facturacion moderna", e.getMessage(),
					e.getHttpStatus());
		}
		return context;
	}

	public FacturaContext cancelarFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			FacturaModernaRequestModel requestModel = new FacturaModernaRequestModel(USR, PW,
					context.getFacturaDto().getRfcEmisor(), context.getFacturaDto().getUuid());
			FacturaModernaCancelResponseModel response = client.getFacturacionModernaClient().cancelar(requestModel);
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