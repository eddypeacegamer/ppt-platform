package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.factura.FacturaFileDto;
import com.business.unknow.model.factura.OLD.FacturaDto;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.entities.factura.FacturaFile;

@Mapper
public interface FacturaMapper {
	
	FacturaDto getFacturaDtoFromEntity(Factura entity);
	List<FacturaDto> getFacturaDtosFromEntities(List<Factura> entities);

	Factura getEntityFromFacturaDto(FacturaDto dto);
	List<Factura> getEntitiesFromFacturaDtos(List<FacturaDto> dto);
	
	FacturaFile getEntityFromFacturaFileDto(FacturaFileDto dto);
	FacturaFileDto getFacturaFileDtoFromEntity(FacturaFile dto);

}
