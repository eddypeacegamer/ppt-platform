package com.business.unknow.services.services.translators;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.LineaEmpresaEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.RelacionadoDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;

@Service
public class SustitucionTranslator {

	public FacturaDto sustitucionComplemento(FacturaDto facturaDto) {
		if (!facturaDto.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el pre-folio %s no esta cancelada y no se puede sustituir",
							facturaDto.getPreFolio()));
		}
		updateBaseInfo(facturaDto);
		facturaDto.setValidacionTeso(true);
		return facturaDto;
	}

	public FacturaDto sustitucionFactura(FacturaDto facturaDto) {
		if (!facturaDto.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el pre-folio %s no esta cancelada y no se puede sustituir",
							facturaDto.getPreFolio()));
		}
		updateBaseInfo(facturaDto);
		return facturaDto;
	}

	
	private FacturaDto updateBaseInfo(FacturaDto facturaDto) {
		facturaDto.setCadenaOriginalTimbrado(null);
		facturaDto.setFechaTimbrado(null);
		facturaDto.setFolio(null);
		facturaDto.setValidacionOper(false);
		facturaDto.setValidacionTeso(false);
		facturaDto.setNotas("");
		facturaDto.setPreFolio("");
		facturaDto.setSelloCfd(null);
		if(facturaDto.getLineaEmisor().equals(LineaEmpresaEnum.A.name())) {
			facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_TESORERIA.getValor());
		}else {
			facturaDto.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR_CONTABILIDAD.getValor());
		}
		facturaDto.setId(0);
		if (facturaDto.getCfdi() != null) {
			facturaDto.getCfdi().setId(null);
			if (facturaDto.getCfdi().getEmisor() != null) {
				facturaDto.getCfdi().getEmisor().setId(null);
			}
			if (facturaDto.getCfdi().getReceptor() != null) {
				facturaDto.getCfdi().getReceptor().setId(null);
			}
			if (facturaDto.getCfdi().getConceptos() != null) {
				for (ConceptoDto conceptoDto : facturaDto.getCfdi().getConceptos()) {
					conceptoDto.setId(null);

					if (conceptoDto.getImpuestos() != null) {
						for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
							impuestoDto.setId(null);
						}
					}
					if (conceptoDto.getRetenciones() != null) {
						for (RetencionDto retencionDto : conceptoDto.getRetenciones()) {
							retencionDto.setId(null);
						}
					}
				}
			}
			if (facturaDto.getCfdi().getComplemento() != null
					&& facturaDto.getCfdi().getComplemento().getPagos() != null) {
				for (CfdiPagoDto cfdiPagoDto : facturaDto.getCfdi().getComplemento().getPagos()) {
					cfdiPagoDto.setId(0);
				}
			}

			RelacionadoDto relacionadoDto = new RelacionadoDto();
			relacionadoDto.setRelacion(facturaDto.getUuid());
			relacionadoDto.setTipoRelacion("04");
			facturaDto.getCfdi().setRelacionado(relacionadoDto);
			facturaDto.setUuid(null);
		}
		return facturaDto;
	}
}
