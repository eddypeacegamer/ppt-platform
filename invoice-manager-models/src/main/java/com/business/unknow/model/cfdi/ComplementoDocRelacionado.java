package com.business.unknow.model.cfdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DoctoRelacionado", namespace = "http://www.sat.gob.mx/Pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComplementoDocRelacionado {

	@XmlAttribute(name = "Folio")
	private String folio;
	@XmlAttribute(name = "IdDocumento")
	private String idDocumento;
	@XmlAttribute(name = "ImpPagado")
	private String impPagado;
	@XmlAttribute(name = "ImpSaldoAnt")
	private String impSaldoAnt;
	@XmlAttribute(name = "ImpSaldoInsoluto")
	private String impSaldoInsoluto;
	@XmlAttribute(name = "MetodoDePagoDR")
	private String metodoDePagoDR;
	@XmlAttribute(name = "MonedaDR")
	private String monedaDR;
	@XmlAttribute(name = "NumParcialidad")
	private int numParcialidad;
	@XmlAttribute(name = "Serie")
	private String serie;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getImpPagado() {
		return impPagado;
	}

	public void setImpPagado(String impPagado) {
		this.impPagado = impPagado;
	}

	public String getImpSaldoAnt() {
		return impSaldoAnt;
	}

	public void setImpSaldoAnt(String impSaldoAnt) {
		this.impSaldoAnt = impSaldoAnt;
	}

	public String getImpSaldoInsoluto() {
		return impSaldoInsoluto;
	}

	public void setImpSaldoInsoluto(String impSaldoInsoluto) {
		this.impSaldoInsoluto = impSaldoInsoluto;
	}

	public String getMetodoDePagoDR() {
		return metodoDePagoDR;
	}

	public void setMetodoDePagoDR(String metodoDePagoDR) {
		this.metodoDePagoDR = metodoDePagoDR;
	}

	public String getMonedaDR() {
		return monedaDR;
	}

	public void setMonedaDR(String monedaDR) {
		this.monedaDR = monedaDR;
	}

	public int getNumParcialidad() {
		return numParcialidad;
	}

	public void setNumParcialidad(int numParcialidad) {
		this.numParcialidad = numParcialidad;
	}

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
