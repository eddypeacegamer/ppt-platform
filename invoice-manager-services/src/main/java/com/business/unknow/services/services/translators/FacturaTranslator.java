package com.business.unknow.services.services.translators;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.factura.CdfiHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;

@Service
public class FacturaTranslator {

	@Autowired
	private CdfiHelper cdfiHelper;

	@Autowired
	private FacturaHelper facturaHelper;

	public FacturaContext translateFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			String xml = facturaHelper.facturaCfdiToXml(context.getCfdi());
			context.setXml(
					cdfiHelper.signXML(xml, context.getFacturaDto().getFechaActualizacion(), context.getEmpresaDto()));
			return context;
		} catch (InvoiceCommonException e) {
			throw new InvoiceManagerException("Error generating the xml", e.getMessage(), HttpStatus.SC_CONFLICT);
		}
	}

}
