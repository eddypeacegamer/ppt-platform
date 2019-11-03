package com.business.unknow.model.context;

import java.io.Serializable;
import java.util.List;

import com.business.unknow.model.PagoDto;
import com.business.unknow.model.factura.FacturaDto;

public class FacturaContext implements Serializable {

	private static final long serialVersionUID = 661221684069242273L;
	private String tipoFactura;
	private FacturaDto facturaDto;
	private FacturaDto comlpemento;
	private List<FacturaDto> complementos;
	private List<PagoDto> pagos;
	private FacturaDto complementoActual;
	private boolean valid;
	private String ruleErrorDesc;
	private String suiteError;

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

	public FacturaDto getComplementoActual() {
		return complementoActual;
	}

	public void setComplementoActual(FacturaDto complementoActual) {
		this.complementoActual = complementoActual;
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

	public List<PagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoDto> pagos) {
		this.pagos = pagos;
	}

	public FacturaDto getComlpemento() {
		return comlpemento;
	}

	public void setComlpemento(FacturaDto comlpemento) {
		this.comlpemento = comlpemento;
	}

	@Override
	public String toString() {
		return "FacturaContext [tipoFactura=" + tipoFactura + ", facturaDto=" + facturaDto + ", comlpemento="
				+ comlpemento + ", complementos=" + complementos + ", pagos=" + pagos + ", complementoActual="
				+ complementoActual + ", valid=" + valid + ", ruleErrorDesc=" + ruleErrorDesc + ", suiteError="
				+ suiteError + "]";
	}

}
