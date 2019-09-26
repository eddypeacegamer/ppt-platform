/**
 * 
 */
package com.business.unknow.model;

import java.util.Arrays;
import java.util.Date;

/**
 * @author hha0009
 *
 */
public class EmpresaDto {

	private Integer id;
	private String rfc;
	private String referencia;
	private String regimenFiscal;
	private String web;
	private String contactoAdmin;
	private String sucursal;
	private String lugarExpedicion;
	private byte[] logotipo;
	private byte[] llavePrivada;
	private byte[] certificado;
	private String pw;
	private String encabezado;
	private String piePagina;
	private Boolean activo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", rfc=" + rfc + ", referencia=" + referencia + ", regimenFiscal="
				+ regimenFiscal + ", web=" + web + ", contactoAdmin=" + contactoAdmin + ", sucursal=" + sucursal
				+ ", lugarExpedicion=" + lugarExpedicion + ", logotipo=" + Arrays.toString(logotipo) + ", llavePrivada="
				+ Arrays.toString(llavePrivada) + ", certificado=" + Arrays.toString(certificado) + ", pw=" + pw
				+ ", encabezado=" + encabezado + ", piePagina=" + piePagina + ", activo=" + activo + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
