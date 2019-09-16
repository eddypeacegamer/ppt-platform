/**
 * 
 */
package com.business.unknow.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.catalogs.ClaveProductoServicioDto;
import com.business.unknow.model.catalogs.ClaveUnidadDto;
import com.business.unknow.model.catalogs.RegimenFiscalDto;
import com.business.unknow.model.catalogs.StatusFacturaDto;
import com.business.unknow.model.catalogs.UsoCfdiDto;
import com.business.unknow.services.entities.catalogs.ClaveProductoServicio;
import com.business.unknow.services.entities.catalogs.ClaveUnidad;
import com.business.unknow.services.mapper.CatalogsMapper;
import com.business.unknow.services.repositories.catalogs.ClaveProductoServicioRepository;
import com.business.unknow.services.repositories.catalogs.ClaveUnidadRepository;
import com.business.unknow.services.repositories.catalogs.RegimanFiscalRepository;
import com.business.unknow.services.repositories.catalogs.StatusFacturaRepository;
import com.business.unknow.services.repositories.catalogs.UsoCfdiRepository;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class CatalogsService {

	@Autowired
	private ClaveProductoServicioRepository productorServicioRepo;

	@Autowired
	private ClaveUnidadRepository unidadRepo;

	@Autowired
	private RegimanFiscalRepository regimenFiscalRepo;

	@Autowired
	private UsoCfdiRepository usoCfdiRepo;
	
	@Autowired
	private StatusFacturaRepository statusFacturaRepo;

	@Autowired
	private CatalogsMapper mapper;

	public Page<ClaveProductoServicioDto> getAllProductoServicioClaves(int page, int size) {
		Page<ClaveProductoServicio> result = productorServicioRepo.findAll(PageRequest.of(page, size));
		return new PageImpl<ClaveProductoServicioDto>(mapper.getClaveProdServDtosFromEntities(result.getContent()),
				result.getPageable(), result.getTotalElements());
	}

	public Page<ClaveUnidadDto> getAllClaveUnidad(int page, int size) {
		Page<ClaveUnidad> result = unidadRepo.findAll(PageRequest.of(page, size));
		return new PageImpl<ClaveUnidadDto>(mapper.getClaveUnidadDtosFromEntities(result.getContent()),
				result.getPageable(), result.getTotalElements());
	}

	public List<ClaveUnidadDto> getAllClaveUnidad() {
		return mapper.getClaveUnidadDtosFromEntities(unidadRepo.findAll());
	}

	public List<RegimenFiscalDto> getAllRegimenFiscal() {
		return mapper.getRegimenFiscalDtosFromEntities(regimenFiscalRepo.findAll());
	}

	public List<UsoCfdiDto> getAllUsoCfdi() {
		return mapper.getUsoCfdiDtosFromEntities(usoCfdiRepo.findAll());
	}
	
	public List<StatusFacturaDto> getAllStatusFactura() {
		return mapper.getStatusFacturaDtosFromEntities(statusFacturaRepo.findAll());
	}

}
