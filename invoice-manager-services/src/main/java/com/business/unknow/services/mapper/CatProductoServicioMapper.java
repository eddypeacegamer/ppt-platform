package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.CatProductoServicioDto;
import com.business.unknow.services.entities.CatProductoServicio;

/**
 * @author eej000f
 *
 */
@Mapper
public interface CatProductoServicioMapper {

	CatProductoServicioDto getCatProductoServicioDtoFromentity(CatProductoServicio entity);

	CatProductoServicio getEntityFromCatProductoServicioDto(CatProductoServicioDto dto);

	List<CatProductoServicioDto> getCatProductoServicioDtosFromEntities(List<CatProductoServicio> entities);

	List<CatProductoServicio> getEntitiesFromCatProductoServicioDtos(List<CatProductoServicioDto> dto);

}
