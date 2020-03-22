
/*Schema creation and selection
CREATE SCHEMA `invoice_manager` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE invoice_manager;
*/

CREATE TABLE IF NOT EXISTS USERS (
  ID_USER INT(11) NOT NULL AUTO_INCREMENT,
  ACTIVO TINYINT(1) NOT NULL, 
  CORREO VARCHAR(100) NOT NULL UNIQUE,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID_USER)
);



CREATE TABLE IF NOT EXISTS ROLES(
  ID_ROLE INT(11) NOT NULL AUTO_INCREMENT,
  ROLE VARCHAR(50) NOT NULL,
  ROLE_DESC VARCHAR(250),
  ID_USER INT(11) NOT NULL,
  PRIMARY KEY (ID_ROLE),
    CONSTRAINT FK_USER_ID_ROLE
    FOREIGN KEY (ID_USER)
    REFERENCES USERS (ID_USER)
);


CREATE TABLE IF NOT EXISTS CONTRIBUYENTES (
  ID_CONTRIBUYENTE INT(11) NOT NULL AUTO_INCREMENT,
  RFC VARCHAR(15) NOT NULL UNIQUE,
  GIRO VARCHAR(50),
  NOMBRE VARCHAR(100),
  CURP VARCHAR(20),
  MORAL TINYINT(1) NOT NULL,
  RAZON_SOCIAL VARCHAR(150),
  REGIMEN_FISCAL VARCHAR(50),
  CALLE VARCHAR(150),
  NO_EXTERIOR VARCHAR(50),
  NO_INTERIOR VARCHAR(50),
  COLONIA VARCHAR(50),
  MUNICIPIO VARCHAR(50),
  LOCALIDAD VARCHAR(50),
  ESTADO VARCHAR(50),
  PAIS VARCHAR(50) NOT NULL,
  COO VARCHAR(50),
  CODIGO_POSTAL VARCHAR(50) NOT NULL,
  CORREO VARCHAR(100),
  TELEFONO VARCHAR(15),
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY  KEY (ID_CONTRIBUYENTE)
);


CREATE TABLE IF NOT EXISTS EMPRESAS (
  ID_EMPRESA INT(11) NOT NULL AUTO_INCREMENT,
  LINEA VARCHAR(5) NOT NULL,
  GIRO_ID INT(10) NOT NULL,
  ACTIVO TINYINT(1) DEFAULT 0,
  RFC VARCHAR(15) NOT NULL UNIQUE,
  REGIMEN_FISCAL VARCHAR(15) NOT NULL,
  CONTACTO_ADMIN VARCHAR(50),
  REFERENCIA VARCHAR(200),
  SUCURSAL VARCHAR(50),
  LUGAR_EXPEDICION VARCHAR(100),
  PW_CORREO VARCHAR(50),
  WEB VARCHAR(150),
  CORREO VARCHAR(50),
  ENCABEZADO VARCHAR(250) ,
  PIE_DE_PAGINA VARCHAR(250) ,
  NO_CERTIFICADO VARCHAR(30),
  PW_SAT VARCHAR(50) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID_EMPRESA),
  CONSTRAINT FK_EMPRESA_CONTRIBUYENTE
    FOREIGN KEY (RFC)
    REFERENCES CONTRIBUYENTES (RFC)
);


CREATE TABLE IF NOT EXISTS CLIENTES (
  ID_CLIENTE INT(11) NOT NULL AUTO_INCREMENT,
  RFC VARCHAR(50) UNIQUE NOT NULL,
  CORREO_PROMOTOR VARCHAR(50) NOT NULL,
  CORREO_CONTACTO VARCHAR(50),
  ACTIVO TINYINT(1) DEFAULT 0,
  PORCENTAJE_PROMOTOR DECIMAL(4,2) NOT NULL,
  PORCENTAJE_CLIENTE  DECIMAL(4,2) NOT NULL,
  PORCENTAJE_CONTACTO DECIMAL(4,2) NOT NULL,
  PORCENTAJE_DESPACHO DECIMAL(4,2) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID_CLIENTE),
  CONSTRAINT FK_CLIENTE_CONTRIBUYENTE
    FOREIGN KEY (RFC)
    REFERENCES CONTRIBUYENTES (RFC)
);


CREATE TABLE IF NOT EXISTS PAGOS(
  `ID_PAGO` int NOT NULL AUTO_INCREMENT,
  `FOLIO` VARCHAR(40) NOT NULL,
  `FOLIO_PADRE` VARCHAR(50) NULL,
  `MONEDA` VARCHAR(5) NOT NULL,
  `BANCO` VARCHAR(40) NOT NULL,
  `CUENTA` VARCHAR(30) NOT NULL,
  `TIPO_CAMBIO` decimal(12,2) NOT NULL,
  `FORMA_PAGO` VARCHAR(40) NOT NULL,
  `MONTO` decimal(20,2) unsigned NOT NULL,
  `TOTAL` decimal(20,2) unsigned DEFAULT NULL,
  `ACREDOR` VARCHAR(20) NOT NULL,
  `DEUDOR` VARCHAR(20) NOT NULL,
  `STATUS_PAGO` VARCHAR(20) NOT NULL,
  `COMENTARIO_PAGO` VARCHAR(250) NULL,
  `SOLICITANTE` VARCHAR(50) NOT NULL,
  `REVISION_1` tinyint DEFAULT NULL,
  `REVISION_2` tinyint DEFAULT NULL,
  `REVISOR_1` VARCHAR(50) DEFAULT NULL,
  `REVISOR_2` VARCHAR(50) DEFAULT NULL,
  `FECHA_CREACION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_PAGO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_PAGO`),
  UNIQUE KEY `FOLIO_UNIQUE` (`FOLIO`,`FORMA_PAGO`)
);

CREATE TABLE PAGO_DEVOLUCION (
  `ID_PAGO_DEVOLUCION` INT(11) NOT NULL AUTO_INCREMENT,
  `MONEDA` VARCHAR(10) NOT NULL,
  `TIPO_CAMBIO` DECIMAL(5,2) NOT NULL,
  `MONTO` DECIMAL(12,2) NOT NULL,
  `BENEFICIARIO` VARCHAR(60) NOT NULL,
  `FORMA_PAGO` VARCHAR(45) NOT NULL,
  `BANCO` VARCHAR(45) DEFAULT NULL,
  `TIPO_REFERENCIA` VARCHAR(200) DEFAULT NULL,
  `REFERENCIA` VARCHAR(200) DEFAULT NULL,
  `FECHA_PAGO` TIMESTAMP NULL DEFAULT NULL,
  `STATUS` VARCHAR(45) NOT NULL,
  `TIPO_RECEPTOR` VARCHAR(45) NOT NULL,
  `SOLICITANTE` VARCHAR(100) NOT NULL,
  `CUENTA_PAGO` VARCHAR(45) DEFAULT NULL,
  `RFC_EMPRESA` VARCHAR(45) DEFAULT NULL,
  `AUTORIZADOR` VARCHAR(45) DEFAULT NULL,
  `FECHA_ACTUALIZACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_CREACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RECEPTOR` VARCHAR(200) DEFAULT NULL,
  `ID_DEVOLUCION` INT(10) unsigned NOT NULL,
  `COMENTARIOS` VARCHAR(500) DEFAULT NULL,
  `FOLIO_FACT` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`ID_PAGO_DEVOLUCION`)
);

CREATE TABLE DEVOLUCIONES (
  `ID_DEVOLUCION` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_PAGO` INT(11) DEFAULT NULL,
  `TIPO` VARCHAR(4) NOT NULL,
  `FOLIO` VARCHAR(40) DEFAULT NULL,
  `PAGO_MONTO` DECIMAL(20,2) DEFAULT NULL,
  `IMPUESTO` DECIMAL(20,2) DEFAULT NULL,
  `PORCENTAJE` DECIMAL(5,2) DEFAULT NULL,
  `MONTO` DECIMAL(20,2) NOT NULL,
  `ID_RECEPTOR` VARCHAR(40) NOT NULL,
  `TIPO_RECEPTOR` VARCHAR(40) NOT NULL,
  `FECHA_CREACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_ACTUALIZACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_DEVOLUCION`)
);

CREATE TABLE IF NOT EXISTS CAT_CLAVE_UNIDAD(
	CLAVE VARCHAR(5) NOT NULL UNIQUE,
	TIPO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(100),
	NOMBRE VARCHAR(50) NOT NULL,
  PRIMARY KEY (CLAVE) 
);

CREATE TABLE IF NOT EXISTS CAT_REGIMEN_FISCAL(
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(100) NOT NULL,
	P_MORAL TINYINT(1) NOT NULL,
	P_FISICA TINYINT(1) NOT NULL,
	INICIO_VIGENCIA TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (CLAVE) 
);


CREATE TABLE IF NOT EXISTS CAT_USO_CFDI(
	CLAVE VARCHAR(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(100) NOT NULL,
	P_MORAL TINYINT(1) NOT NULL,
	P_FISICA TINYINT(1) NOT NULL,
	INICIO_VIGENCIA TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (CLAVE) 
);

CREATE TABLE IF NOT EXISTS CAT_CLAVE_PROD_SERV(
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(500) NOT NULL,
	SIMILARES VARCHAR(750),
	INICIO_VIGENCIA TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (CLAVE) 
);

CREATE TABLE IF NOT EXISTS  CAT_FORMA_PAGO (
  CLAVE varchar(4) NOT NULL,
  DESCRIPCION varchar(100) NOT NULL,
  SHORT_DESC varchar(45) NOT NULL,
  PRIMARY KEY (CLAVE)
);

CREATE TABLE IF NOT EXISTS CAT_GIROS(
	ID_GIRO INT(10) NOT NULL UNIQUE,
	NOMBRE VARCHAR(50) NOT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS CAT_STATUS_PAGO(
	ID_STATUS_PAGO INT(10) NOT NULL UNIQUE,
	VALUE VARCHAR(50) NOT NULL,
  PRIMARY KEY (VALUE) 
);

CREATE TABLE IF NOT EXISTS CAT_STATUS_EVENTO(
	ID_STATUS_EVENTO INT(10) NOT NULL UNIQUE,
	VALUE VARCHAR(50) NOT NULL,
  PRIMARY KEY (VALUE) 
);
CREATE TABLE IF NOT EXISTS CAT_STATUS_DEVOLUCION(
	ID_STATUS_DEVOLUCION INT(10) NOT NULL UNIQUE,
	VALUE VARCHAR(50) NOT NULL,
  PRIMARY KEY (VALUE) 
);

CREATE TABLE IF NOT EXISTS CAT_CODIGO_POSTAL (
   CODIGO_POSTAL VARCHAR(10) NOT NULL,
   ESTADO varchar(200) DEFAULT NULL,
   MUNICIPIO varchar(200) DEFAULT NULL,
   CIUDAD varchar(200) DEFAULT NULL,
   COLONIA varchar(200) DEFAULT NULL,
   ID int(8) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `IDX_CP` (`CODIGO_POSTAL`)
);

CREATE TABLE IF NOT EXISTS CAT_BANCOS(
  ID_BANCO varchar(3) NOT NULL,
  NOMBRE varchar(45) NOT NULL UNIQUE,
  PRIMARY KEY (`ID_BANCO`)
);

CREATE TABLE IF NOT EXISTS CUENTAS_BANCARIAS (
  ID_CUENTA_BANCARIA INT(11) NOT NULL AUTO_INCREMENT,
  BANCO VARCHAR(45) NOT NULL,
  EMPRESA VARCHAR(45) NOT NULL,
  NO_CUENTA VARCHAR(45) DEFAULT NULL,
  CLABE VARCHAR(45) DEFAULT NULL,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FECHA_ACTUALIZACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_CUENTA_BANCARIA`)
);

CREATE TABLE IF NOT EXISTS RESOURCE_FILES (
  FILE_ID INT(11) NOT NULL AUTO_INCREMENT,
  REFERENCIA VARCHAR(40) NOT NULL,
  TIPO_ARCHIVO VARCHAR(10) NOT NULL,
  TIPO_RECURSO VARCHAR(10) NOT NULL,
  DATA LONGBLOB,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FILE_ID`),
  KEY REFERENCIA (`REFERENCIA`)
);

CREATE TABLE IF NOT EXISTS FACTURA_FILES (
  FILE_ID INT(11) NOT NULL AUTO_INCREMENT,
  FOLIO VARCHAR(40) NOT NULL,
  TIPO_ARCHIVO VARCHAR(10) NOT NULL,
  DATA LONGBLOB,
  FECHA_CREACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FILE_ID`)
);

CREATE TABLE IF NOT EXISTS CFDI (
  `ID_CFDI` INT(11) NOT NULL AUTO_INCREMENT,
  `VERSION` VARCHAR(10) NOT NULL,
  `SERIE` VARCHAR(40) NOT NULL,
  `FOLIO` VARCHAR(40) NOT NULL UNIQUE,
  `SELLO` VARCHAR(1000) DEFAULT NULL,
  `NO_CERTIFICADO` VARCHAR(30) DEFAULT NULL,
  `CERTIFICADO` VARCHAR(1000) DEFAULT NULL,
  `MONEDA` VARCHAR(10) NOT NULL DEFAULT 'MXN',
  `SUB_TOTAL` DECIMAL(20,2) DEFAULT NULL,
  `DESCUENTO` DECIMAL(20,2) DEFAULT NULL,
  `TOTAL` DECIMAL(20,2) DEFAULT NULL,
  `TIPO_COMPROBANTE` VARCHAR(50) DEFAULT NULL,
  `METODO_PAGO` VARCHAR(10) DEFAULT NULL,
  `FORMA_PAGO` VARCHAR(10) DEFAULT NULL,
  `CONDICIONES_PAGO` VARCHAR(45) DEFAULT NULL,
  `LUGAR_EXPEDICION` VARCHAR(10) DEFAULT NULL,
  `FECHA` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_CFDI`)
);

CREATE TABLE IF NOT EXISTS FACTURAS (
  `ID_FACTURA` INT(11) NOT NULL AUTO_INCREMENT,
  `RFC_EMISOR` VARCHAR(15) DEFAULT NULL,
  `RFC_REMITENTE` VARCHAR(15) DEFAULT NULL,
  `RAZON_SOCIAL_EMISOR` VARCHAR(100) DEFAULT NULL,
  `RAZON_SOCIAL_REMITENTE` VARCHAR(100) DEFAULT NULL,
  `LINEA_EMISOR` VARCHAR(10) NOT NULL DEFAULT 'A',
  `LINEA_REMITENTE` VARCHAR(10) NOT NULL DEFAULT 'CLIENTE',
  `TIPO_DOCUMENTO` VARCHAR(50) NOT NULL DEFAULT 'FACTURA',
  `SOLICITANTE` VARCHAR(70) DEFAULT NULL,
  `FOLIO` VARCHAR(40) NOT NULL UNIQUE,
  `FOLIO_PADRE` VARCHAR(40) DEFAULT NULL,
  `UUID` VARCHAR(40) DEFAULT NULL,
  `STATUS_PAGO` INT NOT NULL,
  `STATUS_DEVOLUCION` INT(5) NOT NULL,
  `STATUS_FACTURA` INT(5) NOT NULL,
  `STATUS_DETAIL` VARCHAR(500) DEFAULT NULL,
  `STATUS_CANCELADO` INT(5) DEFAULT NULL,
  `METODO_PAGO` VARCHAR(10) NOT NULL,
  `PACK_FACTURACION` VARCHAR(20) NOT NULL DEFAULT 'NTLINK',
  `NOTAS` VARCHAR(400) DEFAULT NULL,
  `FECHA_CREACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_ACTUALIZACION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FECHA_CANCELADO` TIMESTAMP NULL DEFAULT NULL,
  `ID_CFDI` INT(11) DEFAULT NULL,
  PRIMARY KEY (`ID_FACTURA`),
  CONSTRAINT FK_FACTURA_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);

CREATE TABLE IF NOT EXISTS CFDI_EMISORES (
  `ID_CFDI_EMISOR` INT(11) NOT NULL AUTO_INCREMENT,
  `RFC` VARCHAR(20) NOT NULL,
  `NOMBRE` VARCHAR(100) DEFAULT NULL,
  `REGIMEN_FISCAL` VARCHAR(45) NOT NULL,
  `ID_CFDI` int(11) NOT NULL,
  PRIMARY KEY (`ID_CFDI_EMISOR`),
  CONSTRAINT FK_EMISOR_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);

CREATE TABLE IF NOT EXISTS CFDI_RECEPTORES (
  `ID_CFDI_RECEPTOR` INT(11) NOT NULL AUTO_INCREMENT,
  `RFC` VARCHAR(20) NOT NULL,
  `NOMBRE` VARCHAR(100) DEFAULT NULL,
  `USO_CFDI` VARCHAR(5) NOT NULL,
  `ID_CFDI` INT(11) NOT NULL,
  PRIMARY KEY (`ID_CFDI_RECEPTOR`),
  CONSTRAINT FK_RECEPTOR_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);

CREATE TABLE IF NOT EXISTS CFDI_TIMBRE_FISCAL_DIGITAL (
  `ID_COMPLEMENTO` INT(11) NOT NULL AUTO_INCREMENT,
  `VERSION` VARCHAR(10) DEFAULT NULL,
  `UUID` VARCHAR(45) DEFAULT NULL,
  `FECHA_TIMBRADO` TIMESTAMP NULL DEFAULT NULL,
  `RFC_PROV_CERTIF` VARCHAR(45) DEFAULT NULL,
  `SELLO_CFD` VARCHAR(2000) DEFAULT NULL,
  `NO_CERTIFICADO_SAT` VARCHAR(25) DEFAULT NULL,
  `SELLO_SAT` VARCHAR(1500) DEFAULT NULL,
  `CADENA_ORIGINAL` VARCHAR(1500) DEFAULT NULL,
  `ID_CFDI` INT(11) NOT NULL,
  PRIMARY KEY (`ID_COMPLEMENTO`),
  CONSTRAINT FK_TFD_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);

CREATE TABLE IF NOT EXISTS CFDI_PAGOS (
  `ID_CFDI_PAGO` INT(11) NOT NULL AUTO_INCREMENT,
  `VERSION` VARCHAR(5) NOT NULL,
  `FECHA_PAGO` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FORMA_PAGO` VARCHAR(5) NOT NULL,
  `MONEDA` VARCHAR(5) DEFAULT NULL,
  `MONTO` DECIMAL(12,2) DEFAULT NULL,
  `FOLIO` VARCHAR(30) NOT NULL,
  `ID_DOCUMENTO` VARCHAR(45) NOT NULL,
  `IMPORTE_PAGADO` DECIMAL(12,2) DEFAULT NULL,
  `IMPORTE_SALDO_ANTERIOR` DECIMAL(12,2) DEFAULT NULL,
  `IMPORTE_SALDO_INSOLUTO` DECIMAL(12,2) DEFAULT NULL,
  `METODO_PAGO` VARCHAR(5) DEFAULT NULL,
  `MONEDA_DR` VARCHAR(5) DEFAULT NULL,
  `NUM_PARCIALIDAD` TINYINT(3) DEFAULT NULL,
  `SERIE` VARCHAR(10) DEFAULT NULL,
  `ID_CFDI` INT(11) NOT NULL,
  PRIMARY KEY (`ID_CFDI_PAGO`),
  CONSTRAINT FK_PAGOS_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);

CREATE TABLE CFDI_CONCEPTOS (
  `ID_CONCEPTO` INT(11) NOT NULL AUTO_INCREMENT,
  `CLAVE_PROD_SERV` VARCHAR(10) DEFAULT NULL,
  `DESCRIPCION_CLAVE_UNIDAD` VARCHAR(100) DEFAULT NULL,
  `NO_IDENTIFICACION` VARCHAR(10) DEFAULT NULL,
  `CANTIDAD` decimal(20,6) DEFAULT NULL,
  `CLAVE_UNIDAD` VARCHAR(5) DEFAULT NULL,
  `UNIDAD` VARCHAR(100) DEFAULT NULL,
  `DESCRIPCION` VARCHAR(500) DEFAULT NULL,
  `VALOR_UNITARIO` DECIMAL(20,6) DEFAULT NULL,
  `IMPORTE` DECIMAL(20,6) DEFAULT NULL,
  `DESCUENTO` DECIMAL(20,6) DEFAULT NULL,
  `ID_CFDI` INT(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CONCEPTO`),
  CONSTRAINT FK_CONCEPTOS_CFDI
    FOREIGN KEY (ID_CFDI)
    REFERENCES CFDI (ID_CFDI)
);


CREATE TABLE CFDI_IMPUESTOS (
  ID_IMPUESTO INT(11) NOT NULL AUTO_INCREMENT,
  BASE DECIMAL(20,6) DEFAULT NULL,
  IMPUESTO VARCHAR(10) DEFAULT NULL,
  TIPO_FACTOR VARCHAR(10) DEFAULT NULL,
  TASA_CUOTA DECIMAL(9,6) DEFAULT NULL,
  IMPORTE DECIMAL(20,6) DEFAULT NULL,
  ID_CONCEPTO INT(11) NOT NULL,
  PRIMARY KEY (`ID_IMPUESTO`),
  CONSTRAINT FK_IMPUESTOS_CONCEPTO
    FOREIGN KEY (ID_CONCEPTO)
    REFERENCES CONCEPTOS (ID_CONCEPTO)
);

CREATE TABLE CFDI_RETENCIONES (
  `ID_RETENCION` INT(11) NOT NULL AUTO_INCREMENT,
  `BASE` DECIMAL(20,6) DEFAULT NULL,
  `IMPUESTO` varchar(10) DEFAULT NULL,
  `TIPO_FACTOR` varchar(10) DEFAULT NULL,
  `TASA_CUOTA` DECIMAL(9,6) DEFAULT NULL,
  `IMPORTE` DECIMAL(20,6) DEFAULT NULL,
  `ID_CONCEPTO` INT(11) DEFAULT NULL,
  PRIMARY KEY (`ID_RETENCION`),
  CONSTRAINT FK_RETENCIONES_CONCEPTO
    FOREIGN KEY (ID_CONCEPTO)
    REFERENCES CONCEPTOS (ID_CONCEPTO)
);




  












