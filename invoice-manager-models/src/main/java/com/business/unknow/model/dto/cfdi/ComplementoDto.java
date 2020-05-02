package com.business.unknow.model.dto.cfdi;

import java.util.ArrayList;
import java.util.List;

public class ComplementoDto {

	private List<CfdiPagoDto> pagos;

	public ComplementoDto() {
		this.pagos = new ArrayList<CfdiPagoDto>();
	}

	public List<CfdiPagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<CfdiPagoDto> pagos) {
		this.pagos = pagos;
	}

	@Override
	public String toString() {
		return "ComplementoDto [pagos=" + pagos + "]";
	}

}
