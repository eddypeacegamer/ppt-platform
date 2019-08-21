package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.PromotorDto;
import com.business.unknow.services.entities.Promotor;

/**
 * @author eej000f
 *
 */
@Mapper
public interface PromotorMapper {

	PromotorDto getPromotortoFromentity(Promotor entity);

	Promotor getEntityFromPromotorDto(PromotorDto dto);

	List<PromotorDto> getPromotorDtosFromEntities(List<Promotor> entities);

	List<Promotor> getEntitiesFromPromotorDtos(List<PromotorDto> dto);
}
