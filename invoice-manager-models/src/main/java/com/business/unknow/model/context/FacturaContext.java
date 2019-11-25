package com.business.unknow.model.context;

import java.io.Serializable;
import java.util.List;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.FacturaFileDto;

public class FacturaContext implements Serializable {

	private static final long serialVersionUID = 661221684069242273L;
	private String tipoFactura;
	private String tipoDocumento;
	private FacturaDto facturaDto;
	private FacturaDto facturaPadreDto;
	private List<FacturaDto> complementos;
	private List<PagoDto> pagos;
	private boolean valid;
	private String ruleErrorDesc;
	private String suiteError;
	private EmpresaDto empresaDto;
	private Cfdi cfdi;
	private FacturaFileDto facturaFileDto;
	private String xml;

	public FacturaContext() {
		valid = true;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public FacturaDto getFacturaDto() {
		return facturaDto;
	}

	public void setFacturaDto(FacturaDto facturaDto) {
		this.facturaDto = facturaDto;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<FacturaDto> getComplementos() {
		return complementos;
	}

	public void setComplementos(List<FacturaDto> complementos) {
		this.complementos = complementos;
	}

	public String getRuleErrorDesc() {
		return ruleErrorDesc;
	}

	public void setRuleErrorDesc(String ruleErrorDesc) {
		this.ruleErrorDesc = ruleErrorDesc;
	}

	public String getSuiteError() {
		return suiteError;
	}

	public void setSuiteError(String suiteError) {
		this.suiteError = suiteError;
	}

	public FacturaDto getFacturaPadreDto() {
		return facturaPadreDto;
	}

	public void setFacturaPadreDto(FacturaDto facturaPadreDto) {
		this.facturaPadreDto = facturaPadreDto;
	}

	public List<PagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoDto> pagos) {
		this.pagos = pagos;
	}

	public EmpresaDto getEmpresaDto() {
		return empresaDto;
	}

	public void setEmpresaDto(EmpresaDto empresaDto) {
		this.empresaDto = empresaDto;
	}

	public Cfdi getCfdi() {
		return cfdi;
	}

	public void setCfdi(Cfdi cfdi) {
		this.cfdi = cfdi;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public FacturaFileDto getFacturaFileDto() {
		return facturaFileDto;
	}

	public void setFacturaFileDto(FacturaFileDto facturaFileDto) {
		this.facturaFileDto = facturaFileDto;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Override
	public String toString() {
		return "FacturaContext [tipoFactura=" + tipoFactura + ", tipoDocumento=" + tipoDocumento + ", facturaDto="
				+ facturaDto + ", facturaPadreDto=" + facturaPadreDto + ", complementos=" + complementos + ", pagos="
				+ pagos + ", valid=" + valid + ", ruleErrorDesc=" + ruleErrorDesc + ", suiteError=" + suiteError
				+ ", empresaDto=" + empresaDto + ", cfdi=" + cfdi + ", facturaFileDto=" + facturaFileDto + ", xml="
				+ xml + "]";
	}

}
