package com.business.unknow.model.factura.cfdi.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CfdiDto implements Serializable {

	private static final long serialVersionUID = -303198243726456894L;
	private Integer id;
	private String version;
	private String serie;
	private String folio;
	private String sello;
	private String noCertificado;
	private String certificado;
	private String moneda;
	private String tipoDeComprobante;
	private String usoCfdi;
	private String regimenFiscal;
	private String rfcProvCertif;
	private String selloCfd;
	private String noCertificadoSat;
	private String selloSat;
	private List<ConceptoDto> conceptos = new ArrayList<>();

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}

	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}

	public String getUsoCfdi() {
		return usoCfdi;
	}

	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getRfcProvCertif() {
		return rfcProvCertif;
	}

	public void setRfcProvCertif(String rfcProvCertif) {
		this.rfcProvCertif = rfcProvCertif;
	}

	public String getSelloCfd() {
		return selloCfd;
	}

	public void setSelloCfd(String selloCfd) {
		this.selloCfd = selloCfd;
	}

	public String getNoCertificadoSat() {
		return noCertificadoSat;
	}

	public void setNoCertificadoSat(String noCertificadoSat) {
		this.noCertificadoSat = noCertificadoSat;
	}

	public String getSelloSat() {
		return selloSat;
	}

	public void setSelloSat(String selloSat) {
		this.selloSat = selloSat;
	}

	public List<ConceptoDto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<ConceptoDto> conceptos) {
		this.conceptos = conceptos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CfdiDto [version=" + version + ", serie=" + serie + ", folio=" + folio + ", sello=" + sello
				+ ", noCertificado=" + noCertificado + ", certificado=" + certificado + ", moneda=" + moneda
				+ ", tipoDeComprobante=" + tipoDeComprobante + ", usoCfdi=" + usoCfdi + ", regimenFiscal="
				+ regimenFiscal + ", rfcProvCertif=" + rfcProvCertif + ", selloCfd=" + selloCfd + ", noCertificadoSat="
				+ noCertificadoSat + ", selloSat=" + selloSat + ", conceptos=" + conceptos + "]";
	}

}
