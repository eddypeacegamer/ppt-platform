package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ralfdemoledor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaDto implements Serializable {

	private static final long serialVersionUID = -5354660274346579595L;
	private int id;
	private String referencia;
	private String regimenFiscal;
	private String web;
	private String contactoAdmin;
	private String sucursal;
	private String lugarExpedicion;
	private String logotipo;
	private String llavePrivada;
	private String certificado;
	private String noCertificado;
	private String pwSat;
	private String pwCorreo;
	private String correo;
	private String encabezado;
	private String piePagina;
	private Boolean activo;
	private String dominioCorreo;
	private String tipo;

	private Integer giro;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaActualizacion;
	private ContribuyenteDto informacionFiscal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
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

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public String getLlavePrivada() {
		return llavePrivada;
	}

	public void setLlavePrivada(String llavePrivada) {
		this.llavePrivada = llavePrivada;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	public String getPwSat() {
		return pwSat;
	}

	public void setPwSat(String pwSat) {
		this.pwSat = pwSat;
	}

	public String getPwCorreo() {
		return pwCorreo;
	}

	public void setPwCorreo(String pwCorreo) {
		this.pwCorreo = pwCorreo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getGiro() {
		return giro;
	}

	public void setGiro(Integer giro) {
		this.giro = giro;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public ContribuyenteDto getInformacionFiscal() {
		return informacionFiscal;
	}

	public void setInformacionFiscal(ContribuyenteDto informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}

	public String getDominioCorreo() {
		return dominioCorreo;
	}

	public void setDominioCorreo(String dominioCorreo) {
		this.dominioCorreo = dominioCorreo;
	}

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", referencia=" + referencia + ", regimenFiscal=" + regimenFiscal + ", web="
				+ web + ", contactoAdmin=" + contactoAdmin + ", sucursal=" + sucursal + ", lugarExpedicion="
				+ lugarExpedicion + ", logotipo=" + logotipo + ", llavePrivada=" + llavePrivada + ", certificado="
				+ certificado + ", noCertificado=" + noCertificado + ", pwSat=" + pwSat + ", pwCorreo=" + pwCorreo
				+ ", correo=" + correo + ", encabezado=" + encabezado + ", piePagina=" + piePagina + ", activo="
				+ activo + ", tipo=" + tipo + ", giro=" + giro + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + ", informacionFiscal=" + informacionFiscal + "]";
	}

}
