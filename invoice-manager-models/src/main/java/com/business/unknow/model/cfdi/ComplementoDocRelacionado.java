package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAttribute;

public class ComplementoDocRelacionado {

	private String folio;
	private String idDocumento;
	private Double impPagado;
	private Double impSaldoAnt;
	private Double impSaldoInsoluto;
	private String metodoDePagoDR;
	private String monedaDR;
	private int numParcialidad;
	private String serie;
	
	@XmlAttribute(name = "Folio")
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlAttribute(name = "IdDocumento")
	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	@XmlAttribute(name = "ImpPagado")
	public Double getImpPagado() {
		return impPagado;
	}

	public void setImpPagado(Double impPagado) {
		this.impPagado = impPagado;
	}

	@XmlAttribute(name = "ImpSaldoAnt")
	public Double getImpSaldoAnt() {
		return impSaldoAnt;
	}

	public void setImpSaldoAnt(Double impSaldoAnt) {
		this.impSaldoAnt = impSaldoAnt;
	}

	@XmlAttribute(name = "ImpSaldoInsoluto")
	public Double getImpSaldoInsoluto() {
		return impSaldoInsoluto;
	}

	public void setImpSaldoInsoluto(Double impSaldoInsoluto) {
		this.impSaldoInsoluto = impSaldoInsoluto;
	}

	@XmlAttribute(name = "MetodoDePagoDR")
	public String getMetodoDePagoDR() {
		return metodoDePagoDR;
	}

	public void setMetodoDePagoDR(String metodoDePagoDR) {
		this.metodoDePagoDR = metodoDePagoDR;
	}

	@XmlAttribute(name = "MonedaDR")
	public String getMonedaDR() {
		return monedaDR;
	}

	public void setMonedaDR(String monedaDR) {
		this.monedaDR = monedaDR;
	}

	@XmlAttribute(name = "NumParcialidad")
	public int getNumParcialidad() {
		return numParcialidad;
	}

	public void setNumParcialidad(int numParcialidad) {
		this.numParcialidad = numParcialidad;
	}

	@XmlAttribute(name = "Serie")
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "ComplementoDocRelacionado [folio=" + folio + ", idDocumento=" + idDocumento + ", impPagado=" + impPagado
				+ ", impSaldoAnt=" + impSaldoAnt + ", impSaldoInsoluto=" + impSaldoInsoluto + ", metodoDePagoDR="
				+ metodoDePagoDR + ", monedaDR=" + monedaDR + ", numParcialidad=" + numParcialidad + ", serie=" + serie
				+ "]";
	}

}
