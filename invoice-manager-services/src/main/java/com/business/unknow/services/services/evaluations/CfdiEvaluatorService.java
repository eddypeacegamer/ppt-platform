package com.business.unknow.services.services.evaluations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;

@Service
public class CfdiEvaluatorService extends AbstractFacturaServiceEvaluator{

	
	public CfdiDto insertNewCfdi(String folio, CfdiDto dto) {
		Cfdi cfdiTemp = cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(cfdiTemp);
		for (ConceptoDto conceptoDto : dto.getConceptos()) {
			Concepto tempConcepto = conceptoMapper.getEntityFromClientDto(conceptoDto);
			tempConcepto.setCfdi(cfdiTemp);
			tempConcepto = conceptoRepository.save(tempConcepto);
			cfdiDto.getConceptos().add(conceptoMapper.getClientDtoFromEntity(tempConcepto));
			List<ImpuestoDto> impuestos = new ArrayList<>();
			for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
				Impuesto impuestoTemp = impuestoMapper.getEntityFromClientDto(impuestoDto);
				impuestoTemp.setConcepto(tempConcepto);
				impuestos.add(impuestoMapper.getClientDtoFromEntity(impuestoRepository.save(impuestoTemp)));
			}
			conceptoDto.setImpuestos(impuestos);
		}
		return cfdiDto;
	}
}
