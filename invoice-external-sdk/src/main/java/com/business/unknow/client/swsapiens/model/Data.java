package com.business.unknow.client.swsapiens.model;

import java.util.Date;

public class Data {
	private String token;
	private Integer expires_in;
	private String tokeny_type;
	private String contribuyenteRFC;
	private String cadenaOriginalSAT;
	private String noCertificadoSAT;
	private String noCertificadoCFDI;
	private String uuid;
	private String acuse;
	private String selloSAT;
	private String selloCFDI;
	private Date fechaTimbrado;
	private String qrCode;
	private String cfdi;
	private Boolean sncf;
	private Boolean subcontratacion;
	private String noCertificado;
	private String rfc;
	private String validezObligaciones;
	private String estatusCertificado;
	private Date fechaFinal;
	private Date fechaInicio;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getTokeny_type() {
		return tokeny_type;
	}

	public void setTokeny_type(String tokeny_type) {
		this.tokeny_type = tokeny_type;
	}

	public String getContribuyenteRFC() {
		return contribuyenteRFC;
	}

	public void setContribuyenteRFC(String contribuyenteRFC) {
		this.contribuyenteRFC = contribuyenteRFC;
	}

	public Boolean getSncf() {
		return sncf;
	}

	public void setSncf(Boolean sncf) {
		this.sncf = sncf;
	}

	public Boolean getSubcontratacion() {
		return subcontratacion;
	}

	public void setSubcontratacion(Boolean subcontratacion) {
		this.subcontratacion = subcontratacion;
	}

	public String getCadenaOriginalSAT() {
		return cadenaOriginalSAT;
	}

	public void setCadenaOriginalSAT(String cadenaOriginalSAT) {
		this.cadenaOriginalSAT = cadenaOriginalSAT;
	}

	public String getNoCertificadoSAT() {
		return noCertificadoSAT;
	}

	public void setNoCertificadoSAT(String noCertificadoSAT) {
		this.noCertificadoSAT = noCertificadoSAT;
	}

	public String getNoCertificadoCFDI() {
		return noCertificadoCFDI;
	}

	public void setNoCertificadoCFDI(String noCertificadoCFDI) {
		this.noCertificadoCFDI = noCertificadoCFDI;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSelloSAT() {
		return selloSAT;
	}

	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}

	public String getSelloCFDI() {
		return selloCFDI;
	}

	public void setSelloCFDI(String selloCFDI) {
		this.selloCFDI = selloCFDI;
	}

	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getCfdi() {
		return cfdi;
	}

	public void setCfdi(String cfdi) {
		this.cfdi = cfdi;
	}

	public String getAcuse() {
		return acuse;
	}

	public void setAcuse(String acuse) {
		this.acuse = acuse;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getValidezObligaciones() {
		return validezObligaciones;
	}

	public void setValidezObligaciones(String validezObligaciones) {
		this.validezObligaciones = validezObligaciones;
	}

	public String getEstatusCertificado() {
		return estatusCertificado;
	}

	public void setEstatusCertificado(String estatusCertificado) {
		this.estatusCertificado = estatusCertificado;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public String toString() {
		return "Data [token=" + token + ", expires_in=" + expires_in + ", tokeny_type=" + tokeny_type
				+ ", contribuyenteRFC=" + contribuyenteRFC + ", cadenaOriginalSAT=" + cadenaOriginalSAT
				+ ", noCertificadoSAT=" + noCertificadoSAT + ", noCertificadoCFDI=" + noCertificadoCFDI + ", uuid="
				+ uuid + ", acuse=" + acuse + ", selloSAT=" + selloSAT + ", selloCFDI=" + selloCFDI + ", fechaTimbrado="
				+ fechaTimbrado + ", qrCode=" + qrCode + ", cfdi=" + cfdi + ", sncf=" + sncf + ", subcontratacion="
				+ subcontratacion + ", noCertificado=" + noCertificado + ", rfc=" + rfc + ", validezObligaciones="
				+ validezObligaciones + ", estatusCertificado=" + estatusCertificado + ", fechaFinal=" + fechaFinal
				+ ", fechaInicio=" + fechaInicio + "]";
	}

}
