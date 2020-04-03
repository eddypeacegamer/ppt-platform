package com.business.unknow;

public class Constants {

	public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_STANDAR_FORMAT = "yyyy-MM-dd-hh:mm:ss";
	public static final String DATE_FOLIO_FORMAT = "yyyyMMddhhmmss";
	public static final String SUCCESS = "success";

	public static final Integer HTTP_SSTATUS_CONFLICT = 409;
	public static final Integer INTERNAL_ERROR = 500;
	public static final Integer MILISECONDS = 1000;
	public static final Integer BAD_REQUEST = 400;
	public static final Long MILISECONDS_PER_DAY = 86400000L;
	public static final int DEFAULT_SCALE = 2;

	public class FacturaConstants {
		public static final String FACTURA_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
		public static final String REPLACE_CHARACTER = "-";
		public static final String FECHA_ATTRIBUTE = "Fecha";
		public static final String FOLIO_ATTRIBUTE = "Folio";
		public static final String SELLO_ATTRIBUTE = "Sello";
		public static final String UNIX_CONSTANT = "k";
		public static final String SYSTEM_CODIFICATION = "UTF-8";

		public static final String CADENA_ORIGINAL = "src/main/resources/factura-xslt/cadenaoriginal_3_3.xslt";
		public static final String FACTURA_DUMMY = "src/main/resources/factura-xslt/factura_dummy.txt";
	}

	public class CfdiConstants {
		public static final String FACTURA_VERSION = "3.3";
		public static final String FACTURA_COMPLEMENTO_VERSION = "1.0";
		public static final String SCHEMA_URL = "http://www.w3.org/2001/XMLSchema-instance";
		public static final String SAT_URL = "http://www.sat.gob.mx/cfd/3";
		public static final String TFD_URL = "http://www.sat.gob.mx/TimbreFiscalDigital";
		public static final String SCHEMA_LOCATION = "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd  http://www.sat.gob.mx/Pagos http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd";
		public static final String PAGO_LOCATION = "http://www.sat.gob.mx/Pagos";
	}
	
	public class NtlinkModernaRequest{
		public static final String ISER = "iser";
		public static final String TIMBRA_CFDI = "TimbraCfdiQr";
		public static final String USER = "userName";
		public static final String PASS = "password";
		public static final String COMPROBANTE = "comprobante";
		public static final String RFC_EMISOR = "rfc";
		public static final String RFC_RECEPTOR = "rfcReceptor";
		public static final String UUID = "uuid";
		
		public static final String SOAP_ENV = "soapenv";
		public static final String SOAP_ENV_URL = "http://schemas.xmlsoap.org/soap/envelope/";
		
		public static final String ISER_URL = "https://ntlink.com.mx/IServicioTimbrado";
	}

	public class FacturacionModernaRequest{
		public static final String USER_PASS_PARAMETER = "UserPass";
		public static final String USER_ID_PARAMETER = "UserID";
		public static final String EMISOR_PARAMETER = "emisorRFC";
		public static final String UUID_PARAMETER = "uuid";
		public static final String TEXT_PARAMETER = "text2CFDI";
		public static final String GENERAR_TEXT_PARAMETER = "generarTXT";
		public static final String GENERAR_PDF_PARAMETER = "generarPDF";
		public static final String GENERAR_CBB_PARAMETER = "generarCBB";
		public static final String TYPE_PARAMETER = "xsi:type";
		public static final String STRING_TYPE_PARAMETER = "xsd:string";
		public static final String BOOLEAN_TYPE_PARAMETER = "xsd:boolean";
		
		public static final String REQUEST = "request";
		public static final String NS1 = "ns1";
		public static final String XSD = "xsd";
		public static final String XSI = "xsi";
		public static final String SOAP_ENC = "SOAP-ENC";
		public static final String SOAP_ENV = "SOAP-ENV";
		public static final String SOAP_ENV_STRUCT="SOAP-ENC:Struct";
		
		
		public static final String URL_TIMBRADO = "https://t1demo.facturacionmoderna.com/timbrado/soap";
		public static final String URL_SCHEMA = "http://www.w3.org/2001/XMLSchema";
		public static final String URL_SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";
		public static final String URL_ENCODING = "http://schemas.xmlsoap.org/soap/envelope/";
	
		public static final String REQUEST_CANCELADO="requestCancelarCFDI";
		public static final String REQUEST_TIMBRADO="requestTimbrarCFDI";
	}
	
	public class PagoPpdCreditoDefaults{
		public static final String BANCO = "N/A";
		public static final String USER = "Sistema";
		public static final String COMENTARIO ="Pago Automatico por sistema";
		public static final String FORMA_PAGO ="CREDITO";
		public static final String MONEDA ="MXN";
		public static final String TIPO_CAMBIO ="1.0";
		public static final String STATUS_PAGO ="ACEPTADO";
		public static final String CUENTA = "CreditoPPD";
	}
	
	public class ComplementoPpdDefaults{
		public static final String VERSION = "1.0";
		public static final String VERSION_CFDI = "3.3";
		public static final String MONEDA = "XXX";
		public static final String TOTAL = "0";
		public static final String SUB_TOTAL = "0";
		public static final String SERIE = "PFPC";
		public static final String COMPROBANTE = "P";
		public static final String USO_CFDI = "P01";
		
		public static final int CANTIDAD = 1;
		public static final String CLAVE_PROD = "84111506";
		public static final String CLAVE="ACT";
		public static final String DESCRIPCION="Pago";
		public static final String IMPORTE="0";
		public static final String VALOR_UNITARIO="0";
		public static final String METODO_PAGO="PPD";
		public static final String SERIE_PAGO="PFP";
		
		
	}
	
	public class FacturaComplemento {
		public static final String PROD_SERV_DEF = "Servicios de facturación";
		public static final String CLAVE_UNIDAD_DEF = "ACT";
		public static final String DESCRIPCION_DEF = "Pago";
		public static final String FORMA_PAGO = "CREDITO";
		public static final String PAGO_COMENTARIO = "Pago Automatico por sistema";
		public static final String TOTAL = "Total=\"0.0\"";
		public static final String TOTAL_FINAL = "Total=\"0\"";
		public static final String SUB_TOTAL = "SubTotal=\"0.0\"";
		public static final String SUB_TOTAL_FINAL = "SubTotal=\"0\"";
	}
}