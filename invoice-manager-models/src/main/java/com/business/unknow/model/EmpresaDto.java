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
	private String name;
	private String giro;
	private String linea;
	private String razonSocial;
	private String regimenFiscal;
	private String curp;
	private String calle;
	private String noExterior;
	private String noInterior;
	private String municipio;
	private String localidad;
	private String estado;
	private String cp;
	private String referencia;
	private String correo;
	private String telefono;
	private String web;
	private String contactoAdmin;
	private String sucursal;
	private String lugarExpedicion;
	private String colonia;
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

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", name=" + name + ", giro=" + giro + ", linea=" + linea + ", razonSocial="
				+ razonSocial + ", regimenFiscal=" + regimenFiscal + ", curp=" + curp + ", calle=" + calle
				+ ", noExterior=" + noExterior + ", noInterior=" + noInterior + ", municipio=" + municipio
				+ ", localidad=" + localidad + ", estado=" + estado + ", cp=" + cp + ", referencia=" + referencia
				+ ", correo=" + correo + ", telefono=" + telefono + ", web=" + web + ", contactoAdmin=" + contactoAdmin
				+ ", sucursal=" + sucursal + ", lugarExpedicion=" + lugarExpedicion + ", colonia=" + colonia
				+ ", logotipo=" + Arrays.toString(logotipo) + ", llavePrivada=" + Arrays.toString(llavePrivada)
				+ ", certificado=" + Arrays.toString(certificado) + ", pw=" + pw + ", encabezado=" + encabezado
				+ ", piePagina=" + piePagina + ", activo=" + activo + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}
