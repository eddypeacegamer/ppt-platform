package com.business.unknow.services.services.evaluations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;

@Service
public class CfdiEvaluatorService extends AbstractEvaluatorService{

	
	public CfdiDto insertNewCfdi(String folio, CfdiDto dto) {
		Cfdi cfdiTemp = cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(cfdiTemp);
		for (ConceptoDto conceptoDto : dto.getConceptos()) {
			Concepto tempConcepto = cfdiMapper.getEntityFromConceptoDto(conceptoDto);
			tempConcepto.setCfdi(cfdiTemp);
			tempConcepto = conceptoRepository.save(tempConcepto);
			cfdiDto.getConceptos().add(cfdiMapper.getConceptoDtoFromEntity(tempConcepto));
			List<ImpuestoDto> impuestos = new ArrayList<>();
			for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
				Impuesto impuestoTemp = cfdiMapper.getEntityFromImpuestoDto(impuestoDto);
				impuestoTemp.setConcepto(tempConcepto);
				impuestos.add(cfdiMapper.getImpuestoDtoFromEntity(impuestoRepository.save(impuestoTemp)));
			}
			conceptoDto.setImpuestos(impuestos);
		}
		return cfdiDto;
	}
}
