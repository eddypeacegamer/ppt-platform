/**
 * 
 */
package com.business.unknow.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.business.unknow.model.dto.catalogs.CatalogDto;
import com.business.unknow.model.dto.catalogs.ClaveProductoServicioDto;
import com.business.unknow.model.dto.catalogs.ClaveUnidadDto;
import com.business.unknow.model.dto.catalogs.CodigoPostalDto;
import com.business.unknow.model.dto.catalogs.RegimenFiscalDto;
import com.business.unknow.model.dto.catalogs.StatusFacturaDto;
import com.business.unknow.model.dto.catalogs.UsoCfdiDto;
import com.business.unknow.services.entities.catalogs.Banco;
import com.business.unknow.services.entities.catalogs.ClaveProductoServicio;
import com.business.unknow.services.entities.catalogs.ClaveUnidad;
import com.business.unknow.services.entities.catalogs.CodigoPostal;
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

	List<CatalogDto> getBancoDtoFromEntities(List<Banco> bancos);
	CatalogDto getBancoDtoFromEntity(Banco banco);
	
	List<CatalogDto> getGirosDtoFromEntities(List<Giro> giro);
	CatalogDto getGirosDtoFromEntity(Giro giro);
	
	List<CodigoPostalDto> getCodigoPostalUiDtoFromEntity(List<CodigoPostal> codigoPostales);

	CodigoPostalDto getCodigoPostalDtoFromEntity(CodigoPostal codigoPostal);

	CodigoPostal getCodigoPostalFromDto(CodigoPostalDto codigoPostalDto);

	List<CodigoPostalDto> getCodigoPostalDtosFromEntities(List<CodigoPostal> codigoPostalDto);

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

	List<CatalogDto> getStatusPagoDtosFromEntities(List<StatusPago> entities);

	List<CatalogDto> getStatusEventoDtosFromEntities(List<StatusEvento> entities);

	List<CatalogDto> getStatusDevolucionDtosFromEntities(List<StatusDevolucion> entities);

	List<CatalogDto> getStatusRevisionDtosFromEntities(List<StatusRevision> entities);
}
