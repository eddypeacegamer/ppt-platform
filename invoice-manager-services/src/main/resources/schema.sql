----------------
---CATALOGOS
----------------

CREATE TABLE IF NOT EXISTS CLAVE_UNIDAD(
	CLAVE VARCHAR(5) NOT NULL UNIQUE,
	TIPO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(100) NOT NULL,
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
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(100) NOT NULL,
	P_MORAL TINYINT(1) NOT NULL,
	P_FISICA TINYINT(1) NOT NULL,
	INICIO_VIGENCIA TIMESTAMP
);

CREATE TABLE IF NOT EXISTS CLAVE_PROD_SERV(
	CLAVE INT(10) NOT NULL UNIQUE,
	DESCRIPCION VARCHAR(250) NOT NULL,
	SIMILARES VARCHAR(250),
	INICIO_VIGENCIA TIMESTAMP
);


----------------
---ROLE
----------------
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id_role`)
);

----------------
---USER
----------------
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `pw` varchar(50) NOT NULL ,
  `name` varchar(50) NOT NULL ,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `fk_user_id_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `role` (`id_role`)
);

----------------
---PROMOTOR
----------------
CREATE TABLE IF NOT EXISTS `promotor` (
  `id_promotor` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_promotor`),
  CONSTRAINT `fk_promotor_id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `user` (`id_user`)
);

----------------
---CLIENT
----------------
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `rfc` varchar(50) NOT NULL,
  `razon_social` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `calle` varchar(50) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `coo` varchar(50) NOT NULL,
  `no_interior` varchar(50) NOT NULL,
  `no_exterior` varchar(50) NOT NULL,
  `municipio` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `codigo_postal` varchar(50) NOT NULL,
  PRIMARY KEY (`rfc`),
);

CREATE TABLE IF NOT EXISTS `cat_producto_servicio` (
  `id_producto_servicio` int(11) NOT NULL AUTO_INCREMENT,
  `id_parent` int(11) ,
  `value` varchar(250) NOT NULL,
  PRIMARY KEY (`value`),
);

----------------
---INVOICE STATUS
----------------
/*CREATE TABLE IF NOT EXISTS `invoice_status` (
  `id_invoice_status` int(11) NOT NULL AUTO_INCREMENT,
  `event_status` varchar(50) NOT NULL,
  `pay_status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_invoice_status`,`event_status`,`pay_status`),
);

----------------
---INVOICE CDFI
----------------
CREATE TABLE IF NOT EXISTS `invoice_cdfi` (
  `id_invoice_cffi` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_invoice_status` int(11) NOT NULL,
  `amount` float(11) NOT NULL,
  PRIMARY KEY (`id_invoice_cffi`),
  CONSTRAINT `fk_invoice_cdfi_id_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `client` (`id_client`),
  CONSTRAINT `fk_invoice_cdfi_id_invoice_status`
    FOREIGN KEY (`id_invoice_status`)
    REFERENCES `invoice_status` (`id_invoice_status`)

);*/