package com.business.unknow.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.business.unknow.model.cfdi.Cfdi;

@XmlRootElement(name = "FacturaPdfModel")
@XmlAccessorType(XmlAccessType.NONE)
public class FacturaPdfModelDto implements Serializable {

	private static final long serialVersionUID = -5413904708136266306L;
	@XmlElement(name = "Qr")
	private String qr;
	@XmlElement(name = "CadenaOriginal")
	private String cadenaOriginal;
	@XmlElement(name = "folioPadre")
	private String folioPadre;
	@XmlElement(name = "TotalDesc")
	private String totalDesc;
	@XmlElement(name = "SubTotalDesc")
	private String subTotalDesc;
	@XmlElement(name = "UsoCfdiDesc")
	private String usoCfdiDesc;
	@XmlElement(name = "RegimenFiscalDesc")
	private String regimenFiscalDesc;
	@XmlElement(name = "FormaPagoDesc")
	private String formaPagoDesc;
	@XmlElement(name = "MetodoPagoDesc")
	private String metodoPagoDesc;
	@XmlElement(name = "DireccionEmisor")
	private String direccionEmisor;
	@XmlElement(name = "DireccionReceptor")
	private String direccionReceptor;
	@XmlElement(name = "TipoDeComprobanteDesc")
	private String tipoDeComprobanteDesc;
	@XmlElement(name = "Logotipo")
	private String logotipo;
	@XmlElement(name = "factura")
	private Cfdi factura;
	@XmlElement(name = "montoTotalDesc")
	private String montoTotalDesc;
	@XmlElement(name = "montoTotal")
	private BigDecimal montoTotal;
	@XmlElement(name = "TipoRelacion")
	private String tipoRelacion;
	@XmlElement(name = "Relacion")
	private String relacion;

	public FacturaPdfModelDto() {
		super();
	}

	public FacturaPdfModelDto(String qr, String logotipo, Cfdi factura) {
		super();
		this.qr = qr;
		this.logotipo = logotipo;
		this.factura = factura;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public Cfdi getFactura() {
		return factura;
	}

	public void setFactura(Cfdi factura) {
		this.factura = factura;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getDireccionEmisor() {
		return direccionEmisor;
	}

	public void setDireccionEmisor(String direccionEmisor) {
		this.direccionEmisor = direccionEmisor;
	}

	public String getDireccionReceptor() {
		return direccionReceptor;
	}

	public void setDireccionReceptor(String direccionReceptor) {
		this.direccionReceptor = direccionReceptor;
	}

	public String getTipoDeComprobanteDesc() {
		return tipoDeComprobanteDesc;
	}

	public void setTipoDeComprobanteDesc(String tipoDeComprobanteDesc) {
		this.tipoDeComprobanteDesc = tipoDeComprobanteDesc;
	}

	public String getUsoCfdiDesc() {
		return usoCfdiDesc;
	}

	public void setUsoCfdiDesc(String usoCfdiDesc) {
		this.usoCfdiDesc = usoCfdiDesc;
	}

	public String getRegimenFiscalDesc() {
		return regimenFiscalDesc;
	}

	public void setRegimenFiscalDesc(String regimenFiscalDesc) {
		this.regimenFiscalDesc = regimenFiscalDesc;
	}

	public String getFormaPagoDesc() {
		return formaPagoDesc;
	}

	public void setFormaPagoDesc(String formaPagoDesc) {
		this.formaPagoDesc = formaPagoDesc;
	}

	public String getMetodoPagoDesc() {
		return metodoPagoDesc;
	}

	public void setMetodoPagoDesc(String metodoPagoDesc) {
		this.metodoPagoDesc = metodoPagoDesc;
	}

	public String getTotalDesc() {
		return totalDesc;
	}

	public void setTotalDesc(String totalDesc) {
		this.totalDesc = totalDesc;
	}

	public String getSubTotalDesc() {
		return subTotalDesc;
	}

	public void setSubTotalDesc(String subTotalDesc) {
		this.subTotalDesc = subTotalDesc;
	}

	public String getFolioPadre() {
		return folioPadre;
	}

	public void setFolioPadre(String folioPadre) {
		this.folioPadre = folioPadre;
	}

	public String getMontoTotalDesc() {
		return montoTotalDesc;
	}

	public void setMontoTotalDesc(String montoTotalDesc) {
		this.montoTotalDesc = montoTotalDesc;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	@Override
	public String toString() {
		return "FacturaPdfModelDto [qr=" + qr + ", cadenaOriginal=" + cadenaOriginal + ", folioPadre=" + folioPadre
				+ ", totalDesc=" + totalDesc + ", subTotalDesc=" + subTotalDesc + ", usoCfdiDesc=" + usoCfdiDesc
				+ ", regimenFiscalDesc=" + regimenFiscalDesc + ", formaPagoDesc=" + formaPagoDesc + ", metodoPagoDesc="
				+ metodoPagoDesc + ", direccionEmisor=" + direccionEmisor + ", direccionReceptor=" + direccionReceptor
				+ ", tipoDeComprobanteDesc=" + tipoDeComprobanteDesc + ", logotipo=" + logotipo + ", factura=" + factura
				+ ", montoTotalDesc=" + montoTotalDesc + ", montoTotal=" + montoTotal + ", tipoRelacion=" + tipoRelacion
				+ ", relacion=" + relacion + "]";
	}

}
