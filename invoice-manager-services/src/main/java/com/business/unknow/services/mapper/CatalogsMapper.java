/**
 * 
 */
package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.StatusCatalogoDto;
import com.business.unknow.model.catalogs.ClaveProductoServicioDto;
import com.business.unknow.model.catalogs.ClaveUnidadDto;
import com.business.unknow.model.catalogs.GiroDto;
import com.business.unknow.model.catalogs.RegimenFiscalDto;
import com.business.unknow.model.catalogs.StatusFacturaDto;
import com.business.unknow.model.catalogs.UsoCfdiDto;
import com.business.unknow.services.entities.catalogs.ClaveProductoServicio;
import com.business.unknow.services.entities.catalogs.ClaveUnidad;
import com.business.unknow.services.entities.catalogs.Giro;
import com.business.unknow.services.entities.catalogs.RegimenFiscal;
import com.business.unknow.services.entities.catalogs.StatusDevolucion;
import com.business.unknow.services.entities.catalogs.StatusEvento;
import com.business.unknow.services.entities.catalogs.StatusFactura;
import com.business.unknow.services.entities.catalogs.StatusPago;
import com.business.unknow.services.entities.catalogs.StatusRevision;
import com.business.unknow.services.entities.catalogs.UsoCfdi;

/**
 * @author ralfdemoledor
 *
 */
@Mapper
public interface CatalogsMapper {

	ClaveProductoServicioDto getClaveProdServDtoFromEntity(ClaveProductoServicio prodServicio);

	List<ClaveProductoServicioDto> getClaveProdServDtosFromEntities(List<ClaveProductoServicio> prodServicio);

	UsoCfdiDto getUsoCfdiDtoFromEntity(UsoCfdi usodCfdi);

	List<UsoCfdiDto> getUsoCfdiDtosFromEntities(List<UsoCfdi> usodCfdi);

	RegimenFiscalDto getRegimenFiscalDtoFromEntity(RegimenFiscal regimenFiscal);

	List<RegimenFiscalDto> getRegimenFiscalDtosFromEntities(List<RegimenFiscal> regimenFiscal);

	ClaveUnidadDto getClaveUnidadDtoFromEntity(ClaveUnidad claveUnidad);

	List<ClaveUnidadDto> getClaveUnidadDtosFromEntities(List<ClaveUnidad> claveUnidad);

	StatusFactura getStatusFacturaDtoFromEntity(StatusFacturaDto claveUnidad);

	List<StatusFacturaDto> getStatusFacturaDtosFromEntities(List<StatusFactura> claveUnidad);

	GiroDto getGiroDtoFromEntity(Giro giro);

	List<GiroDto> getGiroDtosFromEntities(List<Giro> prodServicio);
	
	List<StatusCatalogoDto> getStatusPagoDtosFromEntities(List<StatusPago> entities);
	
	List<StatusCatalogoDto> getStatusEventoDtosFromEntities(List<StatusEvento> entities);
	
	List<StatusCatalogoDto> getStatusDevolucionDtosFromEntities(List<StatusDevolucion> entities);

	List<StatusCatalogoDto> getStatusRevisionDtosFromEntities(List<StatusRevision> entities);
}
