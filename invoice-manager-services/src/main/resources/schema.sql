
----------------
---ROLE
----------------
CREATE TABLE IF NOT EXISTS ROLES(
  ID_ROLE INT(11) NOT NULL AUTO_INCREMENT,
  NOMBRE VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (ID_ROLE)
);

----------------
---USER
----------------
CREATE TABLE IF NOT EXISTS USERS (
  ID_USER INT(11) NOT NULL AUTO_INCREMENT,
  CORREO VARCHAR(50) NOT NULL,
  PW VARCHAR(50) NOT NULL ,
  NOMBRE VARCHAR(50) NOT NULL ,
  ID_ROLE INT(11) NOT NULL,
  PRIMARY KEY (ID_USER),
  CONSTRAINT FK_USER_ID_ROLE
    FOREIGN KEY (ID_ROLE)
    REFERENCES ROLES (ID_ROLE)
);

----------------
---CONTRIBUYENTES
----------------
CREATE TABLE IF NOT EXISTS CONTRIBUYENTES (
  ID_CONTRIBUYENTE INT(11) NOT NULL AUTO_INCREMENT,
  RFC VARCHAR(15) NOT NULL,
  GIRO VARCHAR(50) NOT NULL ,
  NOMBRE VARCHAR(50) NOT NULL ,
  CURP VARCHAR(20),
  RAZON_SOCIAL VARCHAR(100) ,
  REGIMEN_FISCAL VARCHAR(500) ,
  CALLE VARCHAR(100) ,
  NO_EXTERIOR VARCHAR(50) ,
  NO_INTERIOR VARCHAR(50) ,
  COLONIA VARCHAR(50) ,
  MUNICIPIO VARCHAR(50) ,
  LOCALIDAD VARCHAR(50) ,
  ESTADO VARCHAR(50) ,
  PAIS VARCHAR(50) ,
  COO VARCHAR(50) ,
  CODIGO_POSTAL VARCHAR(50) ,
  CORREO VARCHAR(100) ,
  TELEFONO VARCHAR(15) ,
  FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY  KEY (RFC)
);

----------------
---EMPRESAS
----------------
CREATE TABLE IF NOT EXISTS EMPRESAS (
  ID_EMPRESA INT(11) NOT NULL AUTO_INCREMENT,
  LINEA VARCHAR(5) NOT NULL,
  RFC VARCHAR(15) NOT NULL,
  REGIMEN_FISCAL VARCHAR(15) NOT NULL,
  REFERENCIA VARCHAR(200) ,
  WEB VARCHAR(150),
  CONTACTO_ADMIN VARCHAR(50) ,
  SUCURSAL VARCHAR(50) ,
  LUGAR_EXPEDICION VARCHAR(100) ,
  LOGOTIPO BLOB,
  CERTIFICADO BLOB,
  LLAVE_PRIVADA BLOB,
  PW VARCHAR(50) ,
  ENCABEZADO VARCHAR(250) ,
  PIE_DE_PAGINA VARCHAR(250) ,
  PW_CORREO VARCHAR(50) ,
  ACTIVO TINYINT(1),
  FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (RFC)
);

----------------
---CLIENT
----------------
CREATE TABLE IF NOT EXISTS CLIENTES (
  ID_CLIENT INT(11) NOT NULL AUTO_INCREMENT,
  RFC VARCHAR(50) UNIQUE NOT NULL,
  ACTIVO TINYINT(1),
  FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (RFC)
);

----------------
---FACTURA
----------------
CREATE TABLE IF NOT EXISTS FACTURAS(
  ID_FACTURA INT(11) NOT NULL AUTO_INCREMENT,
  RFC_EMISOR VARCHAR(15),
  RFC_REMITENTE VARCHAR(15),
  FOLIO VARCHAR(40) NOT NULL UNIQUE,
  FOLIO_PADRE VARCHAR(40) ,
  UUID VARCHAR(40),
  ID_STATUS_FACTURA INT(1) NOT NULL,
  STATUS_DETAIL VARCHAR(100),
  TIPO_DOCUMENTO VARCHAR(50) NOT NULL,
  FORMA_PAGO VARCHAR(10) NOT NULL,
  METODO_PAGO VARCHAR(10) NOT NULL,
  NOTAS VARCHAR(400) NOT NULL,
  TOTAL DOUBLE,
  FECHA_TIMBRADO  TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (FOLIO),  
);

CREATE TABLE IF NOT EXISTS FACTURA_ARCHIVO(
  ID_FACTURA_ARCHIVO INT(11) NOT NULL AUTO_INCREMENT,
  FOLIO VARCHAR(40) NOT NULL UNIQUE,
  ARCHIVO_XML VARCHAR(MAX),
  ARCHIVO_PDF VARCHAR(MAX),
  ARCHIVO_QR VARCHAR(MAX),
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (FOLIO) 
);

CREATE TABLE IF NOT EXISTS PAGOS(
  ID_PAGO INT(11) NOT NULL AUTO_INCREMENT,
  FOLIO VARCHAR(40) NOT NULL UNIQUE,
  TIPO_DOCUMENTO VARCHAR(40),
  DOCUMENTO VARCHAR(MAX),
  CANTIDAD DOUBLE,
  TIPO_PAGO VARCHAR(40),
  FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (FOLIO)
);

----------------
---CFDI
----------------
CREATE TABLE IF NOT EXISTS CFDI(
  ID_CDFI INT(11) NOT NULL AUTO_INCREMENT,
  VERSION VARCHAR(10) NOT NULL,
  SERIE VARCHAR(40) NOT NULL UNIQUE,
  FOLIO VARCHAR(40) NOT NULL UNIQUE,
  FECHA_SOLICITUD TIMESTAMP,
  SELLO VARCHAR(1000),
  FORMA_PAGO VARCHAR(10) NOT NULL,
  NO_CERTIFICADO VARCHAR(30),
  CERTIFICADO VARCHAR(1000),
  SUBTOTAL DOUBLE,
  DESCUENTO DOUBLE,
  MONEDA VARCHAR(10),
  TOTAL DOUBLE,
  TIPO_COMPROBANTE VARCHAR(200),
  LUGAR_EXPEDICION VARCHAR(100),
  NOMBRE_EMISOR VARCHAR(100),
  RFC_EMISOR VARCHAR(15),
  NOMBRE_RECEPTOR VARCHAR(100),
  RFC_REMITENTE VARCHAR(15),
  REGIMEN_FISCAL VARCHAR(10),
  USO_CDFI VARCHAR(15),
  RFC_PROV_CERTIF VARCHAR(50),
  UUID VARCHAR(40),
  SELLO_CFD VARCHAR(1000),
  FECHA_TIMBRADO  TIMESTAMP,
  NO_CERTIFICADO_SAT VARCHAR(30),
  SELLO_SAT VARCHAR(1000),
  FECHA_ACTUALIZACION TIMESTAMP,
  PRIMARY KEY (FOLIO) 
);

----------------
---CATALOGOS
----------------

CREATE TABLE IF NOT EXISTS CLAVE_UNIDAD(
	CLAVE VARCHAR(5) NOT NULL UNIQUE,
	TIPO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(100),
	NOMBRE VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS REGIMEN_FISCAL(
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(100) NOT NULL,
	P_MORAL TINYINT(1) NOT NULL,
	P_FISICA TINYINT(1) NOT NULL,
	INICIO_VIGENCIA TIMESTAMP
);

CREATE TABLE IF NOT EXISTS USO_CFDI(
	CLAVE VARCHAR(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(100) NOT NULL,
	P_MORAL TINYINT(1) NOT NULL,
	P_FISICA TINYINT(1) NOT NULL,
	INICIO_VIGENCIA TIMESTAMP
);

CREATE TABLE IF NOT EXISTS CLAVE_PROD_SERV(
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(500) NOT NULL,
	SIMILARES VARCHAR(500),
	INICIO_VIGENCIA TIMESTAMP
);

CREATE TABLE IF NOT EXISTS STATUS_FACTURAS(
	ID_STATUS_FACTURA INT(10) NOT NULL UNIQUE,
	STATUS_VALIDACION VARCHAR(100) NOT NULL,
  STATUS_PAGO VARCHAR(100) NOT NULL,
  STATUS_DEVOLUCION VARCHAR(100) NOT NULL,
	FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP
);


CREATE TABLE IF NOT EXISTS GIROS(
	ID_GIRO INT(10) NOT NULL UNIQUE,
	NOMBRE VARCHAR(50) NOT NULL,
  FECHA_CREACION TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP
);