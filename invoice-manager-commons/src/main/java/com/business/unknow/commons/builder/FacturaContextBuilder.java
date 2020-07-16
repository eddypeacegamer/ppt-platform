package com.business.unknow.commons.builder;

import java.util.List;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.files.FacturaFileDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.pagos.PagoFacturaDto;
import com.business.unknow.model.dto.services.ContribuyenteDto;
import com.business.unknow.model.dto.services.EmpresaDto;

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
	
	public FacturaContextBuilder setPagos(List<PagoFacturaDto> pagos) {
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

	public FacturaContextBuilder setFacturaFilesDto(List<FacturaFileDto> facturaFilesDto) {
		instance.setFacturaFilesDto(facturaFilesDto);
		return this;
	}

}
