/**
 * 
 */
package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.services.entities.Devolucion;

/**
 *@author ralfdemoledor
 *
 */
@Mapper
public interface DevolucionMapper {
	
	public DevolucionDto getDevolucionDtoFromEntity(Devolucion devolucion);
	public List<DevolucionDto> getDevolucionesDtoFromEntities(List<Devolucion> devoluciones);
	
	public Devolucion getEntityFromDevolucionDto(DevolucionDto devolucion);
	public List<Devolucion> getEntitiesFromDtos(List<DevolucionDto> devoluciones);

}
