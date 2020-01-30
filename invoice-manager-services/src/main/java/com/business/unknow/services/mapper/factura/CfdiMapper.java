package com.business.unknow.services.mapper.factura;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.TimbradoFiscalDigitialDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.EmisorDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.ReceptorDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;
import com.business.unknow.model.dto.services.ClientDto;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.TimbradoFiscalDigitial;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Emisor;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.entities.cfdi.Receptor;
import com.business.unknow.services.entities.cfdi.Retencion;
import com.business.unknow.services.mapper.IgnoreUnmappedMapperConfig;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface CfdiMapper {

	CfdiDto getCfdiDtoFromEntity(Cfdi entity);
	@Mappings({ @Mapping(target = "conceptos", ignore = true), @Mapping(target = "complemento", ignore = true),
		@Mapping(target = "emisor", ignore = true), @Mapping(target = "receptor", ignore = true) })
	Cfdi getEntityFromCfdiDto(CfdiDto dto);

	TimbradoFiscalDigitialDto getComplementoDtoFromEntity(TimbradoFiscalDigitial entity);

	@Mappings({ @Mapping(target = "cfdi", ignore = true) })
	TimbradoFiscalDigitial getEntityFromComplementoDto(TimbradoFiscalDigitialDto dto);

	EmisorDto getEmisorDtoFromEntity(Emisor entity);

	Emisor getEntityFromEmisorDto(EmisorDto dto);

	ReceptorDto getEmisorDtoFromEntity(Receptor entity);

	Receptor getEntityFromEmisorDto(ReceptorDto dto);

	ConceptoDto getConceptoDtoFromEntity(Concepto entity);

	Concepto getEntityFromConceptoDto(ConceptoDto dto);

	List<ClientDto> getconceptoDtosFromEntities(List<Concepto> entities);

	List<Concepto> getEntitiesFromConceptoDtos(List<ConceptoDto> dto);

	ImpuestoDto getImpuestoDtoFromEntity(Impuesto entity);

	Impuesto getEntityFromImpuestoDto(ImpuestoDto dto);

	List<ImpuestoDto> getImpuestosDtosFromEntities(List<Impuesto> entities);

	List<Impuesto> getEntitiesFromImpuestoDtos(List<ImpuestoDto> dto);

	RetencionDto getRetencionDtoFromEntity(Retencion entity);

	Retencion getEntityFromRetencionDto(RetencionDto dto);

	List<RetencionDto> getRetencionDtosFromEntities(List<Retencion> entities);

	List<Retencion> getEntitiesFromRetencionDtos(List<RetencionDto> dto);
}
