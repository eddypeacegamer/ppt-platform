package com.business.unknow.services.mapper.xml;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CfdiXmlMapper {

    @Mappings({ @Mapping(target = "conceptos", ignore = true), @Mapping(target = "complemento", ignore = true) })
    Cfdi getEntityFromCfdiDto(CfdiDto dto);

    @Mappings({ @Mapping(target = "impuestos", ignore = true)})
    Concepto getEntityFromConceptoDto(ConceptoDto dto);
}
