package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.factura.Factura;

@Mapper
public interface FacturaMapper {

	FacturaDto getFacturaDtoFromEntity(Factura entity);

	Factura getEntityFromFacturaDto(FacturaDto dto);

	List<FacturaDto> getFacturaDtosFromEntities(List<Factura> entities);

	List<Factura> getEntitiesFromFacturaDtos(List<FacturaDto> dto);

}
