package com.business.unknow.services.util;

import java.util.Date;

import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;
import com.business.unknow.client.model.swsapiens.SwSapiensVersionEnum;
import com.business.unknow.commons.factura.CdfiHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.services.client.SwSapiensClient;

public class StampService {

	public static void main(String[] args) {
		SwSapiensClient swSapiensClient = new SwSapiensClient();
		try {
			FileHelper fh = new FileHelper();
			String xml = fh.readFile("src/main/resources/facturas/", "factura2.xml");
			String cert = fh.readFile("src/main/resources/empresas/", "empresaPw.txt");
			CdfiHelper utils = new CdfiHelper();
			EmpresaDto empresaDto = new EmpresaDto(cert);
			String xml_sellado = utils.signXML(xml, new Date(), empresaDto);
			SwSapiensConfig swSapiensConfig = swSapiensClient.getSwSapiensClient().stamp(xml_sellado,
					SwSapiensVersionEnum.V4.getValue());
			System.out.println(swSapiensConfig.getData().getCfdi());
			System.out.println(swSapiensConfig.getData().getQrCode());
		} catch (InvoiceCommonException | SwSapiensClientException e1) {
			e1.printStackTrace();
		}
	}
}
