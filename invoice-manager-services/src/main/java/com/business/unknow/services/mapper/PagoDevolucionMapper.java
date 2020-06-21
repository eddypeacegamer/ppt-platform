package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.pagos.PagoDevolucionDto;
import com.business.unknow.services.entities.PagoDevolucion;

@Mapper
public interface PagoDevolucionMapper {

	public PagoDevolucionDto getPagoDevolucionDtoFromEntity(PagoDevolucion pagoDevolucion);

	public List<PagoDevolucionDto> getPagoDevolucionesDtoFromEntities(List<PagoDevolucion> pagoDevoluciones);

	public PagoDevolucion getEntityFromPagoDevolucionDto(PagoDevolucionDto pagoDevolucion);

	public List<PagoDevolucion> getEntitiesFromDtos(List<PagoDevolucionDto> pagoDevoluciones);
}
