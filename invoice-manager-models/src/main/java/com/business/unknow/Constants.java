package com.business.unknow;

public class Constants {
	
	public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String SUCCESS = "success";
	
	
	public static final Integer INTERNAL_ERROR = 500;
	public static final Integer BAD_REQUEST = 400;
	public static final Long MILISECONDS_PER_DAY=86400000L;
	public static final int DEFAULT_SCALE=2;
	
	public class FacturaConstants{
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
	
	public class CfdiConstants{
		public static final String FACTURA_VERSION = "3.3";
		public static final String SCHEMA_URL = "http://www.w3.org/2001/XMLSchema-instance";
		public static final String SAT_URL = "http://www.sat.gob.mx/cfd/3";
		public static final String SCHEMA_LOCATION = "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd";
	}
	
		
}
