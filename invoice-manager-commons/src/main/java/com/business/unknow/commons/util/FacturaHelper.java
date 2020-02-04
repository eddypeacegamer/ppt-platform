package com.business.unknow.commons.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.error.InvoiceCommonException;

public class FacturaHelper {

	public String facturaCfdiToXml(Cfdi cfdi) throws InvoiceCommonException {
		try {
			Map<String, String> pref =new HashMap<>();
			pref.put("http://www.w3.org/2001/XMLSchema-instance","xsi");
			pref.put("http://www.sat.gob.mx/cfd/3", "cfdi");
			pref.put("http://www.sat.gob.mx/TimbreFiscalDigital", "tfd");

			JAXBContext jaxbContext = JAXBContext.newInstance(Cfdi.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
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
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" + "<cfdi:Comprobante\n"
				+ "	xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\"\n" + "	xmlns:pago10=\"http://www.sat.gob.mx/Pagos\"\n"
				+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" Descuento=\"0.0\" Fecha=\"2019-11-28T22:19:52\" Folio=\"ce603c78-fb59-48fc-b10e-cb4af22e5975k\" FormaPago=\"01\" LugarExpedicion=\"08630\" MetodoPago=\"PUE\" Moneda=\"MXN\" NoCertificado=\"20001000000300022815\" Sello=\"gmeOOm8tygbOW3593/fwCrRghS2b7GMN+gIdbKjyoIhc4xouFe6Z9c6CpKif4gIiF4IML1+ZtHdiWNXWxW9U7z7KDGwtUpIeF/qfpgMzAlvUM2sl71lTVX/3hoCnbuipyYuJBnvHNT4KroezAwtYT/ki/pevbr+SikZRAmf0pr87GNDN2hdJo1VyOgjQBsIOfHgcwaALL4Z6PB1uMqUY9qzXPM4eUgV2LrdSufW4BRl9qd+kfG88CylO1x3efoUTakhUmYpWFZQw+5UAWnACA+JHXv+maz9FYqy6n710L/IxTWgd1KnaagtmgufVPTe8aaK/P36a5iA9ESbR8XlrMQ==\" SubTotal=\"121.0\" TipoDeComprobante=\"I\" Total=\"140.36\" Version=\"3.3\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd  http://www.sat.gob.mx/Pagos http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd\">\n"
				+ "	<cfdi:Emisor Nombre=\"LAN7008173R5\" RegimenFiscal=\"601\" Rfc=\"LAN7008173R5\" />\n"
				+ "	<cfdi:Receptor Nombre=\"RASE8810158B9\" Rfc=\"RASE8810158B9\" UsoCFDI=\"D02\" />\n"
				+ "	<cfdi:Conceptos>\n"
				+ "		<cfdi:Concepto Cantidad=\"11\" ClaveProdServ=\"60123502\" ClaveUnidad=\"10\" Descripcion=\"a\" Descuento=\"0.0\" Importe=\"121.0\" Unidad=\"Grupos\" ValorUnitario=\"11.0\">\n"
				+ "			<cfdi:Impuestos>\n" + "				<cfdi:Traslados>\n"
				+ "					<cfdi:Traslado Base=\"121.0\" Importe=\"19.36\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\" />\n"
				+ "				</cfdi:Traslados>\n" + "			</cfdi:Impuestos>\n" + "		</cfdi:Concepto>\n"
				+ "	</cfdi:Conceptos>\n" + "	<cfdi:Impuestos TotalImpuestosTrasladados=\"19.36\">\n"
				+ "		<cfdi:Traslados>\n"
				+ "			<cfdi:Traslado Importe=\"19.36\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\" />\n"
				+ "		</cfdi:Traslados>\n" + "	</cfdi:Impuestos>\n" + "	<cfdi:Complemento>\n"
				+ "		<tfd:TimbreFiscalDigital xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\" Version=\"1.1\" UUID=\"72f8ba4a-a013-4624-aafd-a8bb22b421a7\" FechaTimbrado=\"2019-11-28T22:20:04\" RfcProvCertif=\"AAA010101AAA\" SelloCFD=\"gmeOOm8tygbOW3593/fwCrRghS2b7GMN+gIdbKjyoIhc4xouFe6Z9c6CpKif4gIiF4IML1+ZtHdiWNXWxW9U7z7KDGwtUpIeF/qfpgMzAlvUM2sl71lTVX/3hoCnbuipyYuJBnvHNT4KroezAwtYT/ki/pevbr+SikZRAmf0pr87GNDN2hdJo1VyOgjQBsIOfHgcwaALL4Z6PB1uMqUY9qzXPM4eUgV2LrdSufW4BRl9qd+kfG88CylO1x3efoUTakhUmYpWFZQw+5UAWnACA+JHXv+maz9FYqy6n710L/IxTWgd1KnaagtmgufVPTe8aaK/P36a5iA9ESbR8XlrMQ==\" NoCertificadoSAT=\"20001000000300022323\" SelloSAT=\"bJEDyHxks3E5lHigqYBL76O8jdp6q51pIFE42+jjYqSJIZmnVd8sGyhMshB/SNyfb2DG9zkhQIvH2sOKN6mlQykkWsoYNl6tGZdXlEX8ahn9jJRloUP/JF+cnIQXmAaePFLd+RUJrMI8lG/fLDo3pad0fzqmJ1qS+UFxJ4Sn1xtQ+Ew/I0WSlRbtT2dM3QPmUgEXer5+vJJjnD3YaICd87aC5nqZoZVP/jxuzlUObwB/Qo8uxJgsCgQkVHPx/tTpBYOLygyGgdAxjx4k8gPyv92G4YMkFZ1J82B8Zh9572JwlbZacQ8R50gTpXEvaYX0AySbKr2+B3cKjrlJf/F1GQ==\"\n"
				+ "			xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\"\n"
				+ "			xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" />\n"
				+ "		</cfdi:Complemento>\n" + "	</cfdi:Comprobante>";

		FacturaHelper helpser = new FacturaHelper();
		Cfdi cfdi = helpser.getFacturaFromString(xml);
		System.out.println(cfdi.getComplemento().getTimbreFiscalDigital());
	}
}
