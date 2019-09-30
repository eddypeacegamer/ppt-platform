package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.factura.Factura;

@Mapper
public interface FacturaMapper {

	@Mappings({
		@Mapping(target = "conceptos", ignore = true),
		@Mapping(target = "pagos", ignore = true)
	})
	FacturaDto getFacturaDtoFromEntity(Factura entity);
	List<FacturaDto> getFacturaDtosFromEntities(List<Factura> entities);

	Factura getEntityFromFacturaDto(FacturaDto dto);
	List<Factura> getEntitiesFromFacturaDtos(List<FacturaDto> dto);

}
