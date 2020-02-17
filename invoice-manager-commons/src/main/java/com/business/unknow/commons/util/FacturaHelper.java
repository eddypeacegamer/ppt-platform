package com.business.unknow.commons.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.error.InvoiceCommonException;

public class FacturaHelper {

	public String facturaCfdiToXml(Cfdi cfdi) throws InvoiceCommonException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Cfdi.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(cfdi, sw);
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new InvoiceCommonException(e.getMessage());
		}
		
	}

	public Cfdi getFacturaFromString(String xml) throws InvoiceCommonException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Cfdi.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			return (Cfdi) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new InvoiceCommonException(e.getMessage());
		}
	}

	public static void main(String[] args) throws InvoiceCommonException {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
				"<cfdi:Comprobante\n" + 
				"	xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\"\n" + 
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" + 
				"	xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\" \n" + 
				"	Certificado=\"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\" Fecha=\"2019-10-08T19:10:54\" Folio=\"18563\" FormaPago=\"99\" LugarExpedicion=\"52778\" MetodoPago=\"PPD\" Moneda=\"MXN\" NoCertificado=\"00001000000404250937\" Sello=\"Tvp19vXKsGHY15iA3KcOvfQ6QnKzE5qWLPlPAVwR6vZv7IwjeheZISIqGYwZpne4/7eQECc7NtRtVG8FG1uCNhsOfpqws3lULDVmFnbdVhZxb8OYvKaVwEDY163tD02TCUMul1Yw6lt977c0Pvcl8mN2w/ZmwseIub5wjnpgpskD7IVtstaJLBXQFoy9VSZNChnMNXaVqKk7rm2futwRNSqsA4FXgJaXgbSlj8wRLMc1gkd7AfaqcW5HZkSDzDYCNBOUGHKf7X1Pshn/LvteQdBNQBSjrEOLTI4gfaXMjzLj9NYBvxlhoGojWDtoMNCBkkD29KQoc4r1p0Hk5W70Vg==\" Serie=\"PFP\" SubTotal=\"21551.72\" TipoDeComprobante=\"I\" Total=\"25000.00\" Version=\"3.3\">\n" + 
				"	<cfdi:Emisor Nombre=\"PREVISION FINAL SA DE CV\" RegimenFiscal=\"601\" Rfc=\"PFI810716QEA\"></cfdi:Emisor>\n" + 
				"	<cfdi:Receptor Nombre=\"EDGAR JOSE EDUARDO RAMOS SILVEYRA\" Rfc=\"RASE8810158B9\" UsoCFDI=\"D03\"></cfdi:Receptor>\n" + 
				"	<cfdi:Conceptos>\n" + 
				"		<cfdi:Concepto Cantidad=\"1.00000\" ClaveProdServ=\"85171500\" ClaveUnidad=\"E48\" Descripcion=\"Servicio Funerario\" Importe=\"21551.72\" NoIdentificacion=\"14SR0104\" ValorUnitario=\"21551.72000\">\n" + 
				"			<cfdi:Impuestos>\n" + 
				"				<cfdi:Traslados>\n" + 
				"					<cfdi:Traslado Base=\"21551.72\" Importe=\"3448.28\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"></cfdi:Traslado>\n" + 
				"				</cfdi:Traslados>\n" + 
				"			</cfdi:Impuestos>\n" + 
				"		</cfdi:Concepto>\n" + 
				"	</cfdi:Conceptos>\n" + 
				"	<cfdi:Impuestos TotalImpuestosTrasladados=\"3448.28\">\n" + 
				"		<cfdi:Traslados>\n" + 
				"			<cfdi:Traslado Importe=\"3448.28\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"></cfdi:Traslado>\n" + 
				"		</cfdi:Traslados>\n" + 
				"	</cfdi:Impuestos>\n" + 
				"	<cfdi:Complemento>\n" + 
				"		<tfd:TimbreFiscalDigital\n" + 
				"			xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\" xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/timbrefiscaldigital/TimbreFiscalDigitalv11.xsd\" Version=\"1.1\" UUID=\"2EFE9F7D-34F8-4CFC-993E-34A7888F8BBF\" FechaTimbrado=\"2019-10-08T19:10:59\" RfcProvCertif=\"MAS0810247C0\" SelloCFD=\"Tvp19vXKsGHY15iA3KcOvfQ6QnKzE5qWLPlPAVwR6vZv7IwjeheZISIqGYwZpne4/7eQECc7NtRtVG8FG1uCNhsOfpqws3lULDVmFnbdVhZxb8OYvKaVwEDY163tD02TCUMul1Yw6lt977c0Pvcl8mN2w/ZmwseIub5wjnpgpskD7IVtstaJLBXQFoy9VSZNChnMNXaVqKk7rm2futwRNSqsA4FXgJaXgbSlj8wRLMc1gkd7AfaqcW5HZkSDzDYCNBOUGHKf7X1Pshn/LvteQdBNQBSjrEOLTI4gfaXMjzLj9NYBvxlhoGojWDtoMNCBkkD29KQoc4r1p0Hk5W70Vg==\" NoCertificadoSAT=\"00001000000404486074\" SelloSAT=\"gPoqgCZOxG+KrtrwiC/LilhBBD3Lnq3vC7WQVxoTEMBZXqwvJSlxDLYV0UMkimA2cLUM7PJXNzkQ3XWVeH8FCFeqJGeJpRcFQkEiS3QvvvTWPhOmYR31IjtrpheQqgX1ny+i3p+Qg5hTHAamBtfUPDhoCdNFkzVyMXRKE8lhPdgw6c8+XKYvFr0cDgPppBjCf1LXwu13JWgrs1Q+lKpE25iDuH2TRLi3VmC8FYrHs3PC5ZyUU80sioErpVlSRSxeUMuZn6dr+LIbzs1EyQ0rpAv1+Znfr0AHzrfUze+j5dbyXRiQjq0gTFtmmgEVEX6kx1SIdl/wiattTI9+YymmxA==\" />\n" + 
				"		</cfdi:Complemento>\n" + 
				"	</cfdi:Comprobante>";

		FacturaHelper helpser = new FacturaHelper();
		Cfdi cfdi = helpser.getFacturaFromString(xml);
		System.out.println(cfdi.getComplemento().getTimbreFiscalDigital());
	}
}
