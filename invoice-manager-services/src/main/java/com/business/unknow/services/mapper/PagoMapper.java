/**
 * 
 */
package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.PagoDto;
import com.business.unknow.services.entities.Pago;

/**
 *@author ralfdemoledor
 *
 */
@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface PagoMapper {
	
	
	public PagoDto getPagoDtoFromEntity(Pago pago);
	public List<PagoDto> getPagosDtoFromEntities(List<Pago> pagos);
	
	public Pago getEntityFromPagoDto(PagoDto pago);
	public List<Pago> getEntitiesFromDtos(List<PagoDto> pagos);

}
