/**
 * 
 */
package com.business.unknow.model.factura.pdf;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author hha0009
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacturaPdfDto implements Serializable{
	
	private static final long serialVersionUID = -1055842064036067192L;
	private String folio;
	private String folioFiscal;
	private String fechaEmision;
	
	private String formaPago;
	private String condicionesPago;
	private String metodoPago;
	private String tipoComprobante;
	private String csdEmisor;
	private String csdSat;
	private String fechaCertificacion;
	private String moneda;
	private String selloDigitalEmisor;
	private String selloSat;
	private String cadenaOriginal;
	
	
	//Info Empresa
	private String razonSocialEmisor;
	private String rfcEmisor;
	private String regimenFiscalEmisor;
	private String lugarExpedicion;
	//Info cliente
	private String razonSocialReceptor;
	private String rfcReceptor;
	private String usoCfdi;
	
	private List<ConceptoPdfDto> conceptos;
	private String subtotal;
	private String iva;
	private String total;
	
	private String logo;
	private String qr;
	
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFolioFiscal() {
		return folioFiscal;
	}
	public void setFolioFiscal(String folioFiscal) {
		this.folioFiscal = folioFiscal;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCondicionesPago() {
		return condicionesPago;
	}
	public void setCondicionesPago(String condicionesPago) {
		this.condicionesPago = condicionesPago;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getCsdEmisor() {
		return csdEmisor;
	}
	public void setCsdEmisor(String csdEmisor) {
		this.csdEmisor = csdEmisor;
	}
	public String getCsdSat() {
		return csdSat;
	}
	public void setCsdSat(String csdSat) {
		this.csdSat = csdSat;
	}
	public String getFechaCertificacion() {
		return fechaCertificacion;
	}
	public void setFechaCertificacion(String fechaCertificacion) {
		this.fechaCertificacion = fechaCertificacion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getSelloDigitalEmisor() {
		return selloDigitalEmisor;
	}
	public void setSelloDigitalEmisor(String selloDigitalEmisor) {
		this.selloDigitalEmisor = selloDigitalEmisor;
	}
	public String getSelloSat() {
		return selloSat;
	}
	public void setSelloSat(String selloSat) {
		this.selloSat = selloSat;
	}
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}
	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
	}
	public String getRfcEmisor() {
		return rfcEmisor;
	}
	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}
	public String getRegimenFiscalEmisor() {
		return regimenFiscalEmisor;
	}
	public void setRegimenFiscalEmisor(String regimenFiscalEmisor) {
		this.regimenFiscalEmisor = regimenFiscalEmisor;
	}
	public String getLugarExpedicion() {
		return lugarExpedicion;
	}
	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}
	public String getRazonSocialReceptor() {
		return razonSocialReceptor;
	}
	public void setRazonSocialReceptor(String razonSocialReceptor) {
		this.razonSocialReceptor = razonSocialReceptor;
	}
	public String getRfcReceptor() {
		return rfcReceptor;
	}
	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}
	public String getUsoCfdi() {
		return usoCfdi;
	}
	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}
	public List<ConceptoPdfDto> getConceptos() {
		return conceptos;
	}
	public void setConceptos(List<ConceptoPdfDto> conceptos) {
		this.conceptos = conceptos;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	@Override
	public String toString() {
		return "FacturaPdfDto [folio=" + folio + ", folioFiscal=" + folioFiscal + ", fechaEmision=" + fechaEmision
				+ ", formaPago=" + formaPago + ", condicionesPago=" + condicionesPago + ", metodoPago=" + metodoPago
				+ ", tipoComprobante=" + tipoComprobante + ", csdEmisor=" + csdEmisor + ", csdSat=" + csdSat
				+ ", fechaCertificacion=" + fechaCertificacion + ", moneda=" + moneda + ", selloDigitalEmisor="
				+ selloDigitalEmisor + ", selloSat=" + selloSat + ", cadenaOriginal=" + cadenaOriginal
				+ ", razonSocialEmisor=" + razonSocialEmisor + ", rfcEmisor=" + rfcEmisor + ", regimenFiscalEmisor="
				+ regimenFiscalEmisor + ", lugarExpedicion=" + lugarExpedicion + ", razonSocialReceptor="
				+ razonSocialReceptor + ", rfcReceptor=" + rfcReceptor + ", usoCfdi=" + usoCfdi + ", conceptos="
				+ conceptos + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + ", logo=" + logo
				+ ", qr=" + qr + "]";
	}
	
}
