package com.business.unknow.model.dto.cfdi;

import java.util.ArrayList;
import java.util.List;

public class ComplementoDto {
	
	private TimbradoFiscalDigitialDto timbreFiscal;
	private List<CfdiPagoDto> pagos;
	
	public ComplementoDto() {
		this.timbreFiscal = new TimbradoFiscalDigitialDto();
		this.pagos = new ArrayList<CfdiPagoDto>();
	}

	public TimbradoFiscalDigitialDto getTimbreFiscal() {
		return timbreFiscal;
	}

	public void setTimbreFiscal(TimbradoFiscalDigitialDto timbreFiscal) {
		this.timbreFiscal = timbreFiscal;
	}

	public List<CfdiPagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<CfdiPagoDto> pagos) {
		this.pagos = pagos;
	}

	@Override
	public String toString() {
		return "ComplementoDto [timbreFiscal=" + timbreFiscal + ", pagos=" + pagos + "]";
	}

}
