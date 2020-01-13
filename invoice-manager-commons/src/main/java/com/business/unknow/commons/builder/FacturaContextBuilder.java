package com.business.unknow.commons.builder;

import java.util.List;

import com.business.unknow.model.ContribuyenteDto;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;

public class FacturaContextBuilder extends AbstractBuilder<FacturaContext> {

	public FacturaContextBuilder() {
		super(new FacturaContext());
	}

	public FacturaContextBuilder setTipoFactura(String tipoFactura) {
		instance.setTipoFactura(tipoFactura);
		return this;
	}

	public FacturaContextBuilder setFacturaDto(FacturaDto factura) {
		instance.setFacturaDto(factura);
		return this;
	}
	
	public FacturaContextBuilder setCfdi(CfdiDto cfdiDto) {
		instance.getFacturaDto().setCfdi(cfdiDto);
		return this;
	}

	public FacturaContextBuilder setFacturaPadreDto(FacturaDto facturaPadre) {
		instance.setFacturaPadreDto(facturaPadre);
		return this;
	}

	public FacturaContextBuilder setComplementos(List<FacturaDto> complementos) {
		instance.setComplementos(complementos);
		return this;
	}

	public FacturaContextBuilder setEmpresaDto(EmpresaDto empresaDto) {
		instance.setEmpresaDto(empresaDto);
		return this;
	}
	
	public FacturaContextBuilder setContribuyenteDto(ContribuyenteDto contribuyenteDto) {
		instance.setContribuyenteDto(contribuyenteDto);
		return this;
	}
	
	public FacturaContextBuilder setTipoDocumento(String tipoDocumento) {
		instance.setTipoDocumento(tipoDocumento);
		return this;
	}
	
	public FacturaContextBuilder setCtdadComplementos(int ctdadComplementos) {
		instance.setCtdadComplementos(ctdadComplementos);
		return this;
	}
	
	public FacturaContextBuilder setPagos(List<PagoDto> pagos) {
		instance.setPagos(pagos);
		return this;
	}
	
	public FacturaContextBuilder setPagoCredito(PagoDto pagoCredito) {
		instance.setPagoCredito(pagoCredito);
		return this;
	}
	
	public FacturaContextBuilder setCurrentPago(PagoDto currentPago) {
		instance.setCurrentPago(currentPago);
		return this;
	}

}