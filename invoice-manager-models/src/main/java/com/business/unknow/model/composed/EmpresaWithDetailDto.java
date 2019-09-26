package com.business.unknow.model.composed;

import com.business.unknow.model.ContribuyenteDto;
import com.business.unknow.model.EmpresaDto;

public class EmpresaWithDetailDto {

	private EmpresaDto empresa;
	private ContribuyenteDto detail;

	public EmpresaDto getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDto empresa) {
		this.empresa = empresa;
	}

	public ContribuyenteDto getDetail() {
		return detail;
	}

	public void setDetail(ContribuyenteDto detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "EmpresaWithDetailDto [empresa=" + empresa + ", detail=" + detail + "]";
	}

}
