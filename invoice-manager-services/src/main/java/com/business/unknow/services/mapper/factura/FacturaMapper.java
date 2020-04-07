package com.business.unknow.services.mapper.factura;

import java.util.List;
import org.mapstruct.Mapper;

import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.IgnoreUnmappedMapperConfig;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface FacturaMapper {
	
	FacturaDto getFacturaDtoFromEntity(Factura entity);
	
	List<FacturaDto> getFacturaDtosFromEntities(List<Factura> entities);

	Factura getEntityFromFacturaDto(FacturaDto dto);
	List<Factura> getEntitiesFromFacturaDtos(List<FacturaDto> dto);
	
	Pago getEntityFromPagoDto(PagoDto dto);
	
	PagoDto getPagoDtoFromEntity(Pago dto);
	List<PagoDto> getPagosDtoFromEntity(List<Pago> dto);


}
