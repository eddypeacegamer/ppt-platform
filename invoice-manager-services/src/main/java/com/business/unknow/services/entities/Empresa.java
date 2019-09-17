package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESAS")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 8251482734350985993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPRESA")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String name;

	@Basic(optional = false)
	@Column(name = "GIRO")
	private String giro;

	@Basic(optional = false)
	@Column(name = "LINEA")
	private String linea;

	@Basic(optional = false)
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	@Basic(optional = false)
	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@Basic(optional = false)
	@Column(name = "CURP")
	private String curp;

	@Basic(optional = false)
	@Column(name = "CALLE")
	private String calle;

	@Basic(optional = false)
	@Column(name = "NO_EXTERIOR")
	private String noExterior;

	@Basic(optional = false)
	@Column(name = "NO_INTERIOR")
	private String noInterior;

	@Basic(optional = false)
	@Column(name = "MUNICIPIO")
	private String municipio;

	@Basic(optional = false)
	@Column(name = "LOCALIDAD")
	private String localidad;

	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;

	@Basic(optional = false)
	@Column(name = "CP")
	private String cp;

	@Basic(optional = false)
	@Column(name = "REFERENCIA")
	private String referencia;

	@Basic(optional = false) 
	@Column(name = "CORREO")
	private String correo;

	@Column(name = "TELEFONO")
	private String telefono;

	@Basic(optional = false)
	@Column(name = "WEB")
	private String web;

	@Basic(optional = false)
	@Column(name = "CONTACTO_ADMIN")
	private String contactoAdmin;

	@Basic(optional = false)
	@Column(name = "SUCURSAL")
	private String sucursal;

	@Basic(optional = false)
	@Column(name = "LUGAR_EXPEDICION")
	private String lugarExpedicion;

	@Basic(optional = false)
	@Column(name = "COLONIA")
	private String colonia;

	@Basic(optional = false)
	@Column(name = "LOGOTIPO")
	private byte[] logotipo;

	@Basic(optional = false)
	@Column(name = "LLAVE_PRIVADA")
	private byte[] llavePrivada;

	@Basic(optional = false)
	@Column(name = "CERTIFICADO_DIGITAL")
	private byte[] certificado;

	@Basic(optional = false)
	@Column(name = "PW")
	private String pw;

	@Basic(optional = false)
	@Column(name = "ENCABEZADO")
	private String encabezado;

	@Basic(optional = false)
	@Column(name = "PIE_DE_PAGINA")
	private String piePagina;

	@Basic(optional = false)
	@Column(name = "ACTIVO")
	private Boolean activo;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getContactoAdmin() {
		return contactoAdmin;
	}

	public void setContactoAdmin(String contactoAdmin) {
		this.contactoAdmin = contactoAdmin;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public byte[] getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}

	public byte[] getLlavePrivada() {
		return llavePrivada;
	}

	public void setLlavePrivada(byte[] llavePrivada) {
		this.llavePrivada = llavePrivada;
	}

	public byte[] getCertificado() {
		return certificado;
	}

	public void setCertificado(byte[] certificado) {
		this.certificado = certificado;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public String getPiePagina() {
		return piePagina;
	}

	public void setPiePagina(String piePagina) {
		this.piePagina = piePagina;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", name=" + name + ", giro=" + giro + ", linea=" + linea + ", razonSocial="
				+ razonSocial + ", regimenFiscal=" + regimenFiscal + ", curp=" + curp + ", calle=" + calle
				+ ", noExterior=" + noExterior + ", noInterior=" + noInterior + ", municipio=" + municipio
				+ ", localidad=" + localidad + ", estado=" + estado + ", cp=" + cp + ", referencia=" + referencia
				+ ", correo=" + correo + ", telefono=" + telefono + ", web=" + web + ", contactoAdmin=" + contactoAdmin
				+ ", sucursal=" + sucursal + ", lugarExpedicion=" + lugarExpedicion + ", colonia=" + colonia
				+ ", logotipo=" + Arrays.toString(logotipo) + ", llavePrivada=" + Arrays.toString(llavePrivada)
				+ ", certificado=" + Arrays.toString(certificado) + ", pw=" + pw + ", encabezado=" + encabezado
				+ ", piePagina=" + piePagina + ", activo=" + activo + "]";
	}

}
