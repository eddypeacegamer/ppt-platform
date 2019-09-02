/**
 * 
 */
package com.business.unknow.model;

/**
 * @author hha0009
 *
 */
public class EmpresaDto {
	
	
	private String rfc;
	private String giro;
	private String razonSocial;
	private String regFiscal;
	
	private String linea;
	
	private String curp;
	private String calle;
	private Integer noInterior;
	private Integer noExterior;
	private String colonia;
	private String municipio;
	private String estado;
	private String pais;
	private String referencia;
	private String codigoPostal;
	private String email;
	private String telefono;
	private String web;
	private String contacto;
	
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRegFiscal() {
		return regFiscal;
	}
	public void setRegFiscal(String regFiscal) {
		this.regFiscal = regFiscal;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
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
	public Integer getNoInterior() {
		return noInterior;
	}
	public void setNoInterior(Integer noInterior) {
		this.noInterior = noInterior;
	}
	public Integer getNoExterior() {
		return noExterior;
	}
	public void setNoExterior(Integer noExterior) {
		this.noExterior = noExterior;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	@Override
	public String toString() {
		return "EmpresaDto [rfc=" + rfc + ", giro=" + giro + ", razonSocial=" + razonSocial + ", regFiscal=" + regFiscal
				+ ", linea=" + linea + ", curp=" + curp + ", calle=" + calle + ", noInterior=" + noInterior
				+ ", noExterior=" + noExterior + ", colonia=" + colonia + ", municipio=" + municipio + ", estado="
				+ estado + ", pais=" + pais + ", referencia=" + referencia + ", codigoPostal=" + codigoPostal
				+ ", email=" + email + ", telefono=" + telefono + ", web=" + web + ", contacto=" + contacto + "]";
	}
}
