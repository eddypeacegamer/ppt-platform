package com.business.unknow.services.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.services.entities.catalogs.FormaPago;
import com.business.unknow.services.entities.catalogs.RegimenFiscal;
import com.business.unknow.services.entities.catalogs.UsoCfdi;
import com.business.unknow.services.repositories.catalogs.FormaPagoRepository;
import com.business.unknow.services.repositories.catalogs.RegimanFiscalRepository;
import com.business.unknow.services.repositories.catalogs.UsoCfdiRepository;

@Service
public class CatalogCacheService {

	private Map<String, UsoCfdi> usoCfdiMappings;

	private Map<String, FormaPago> formaPagoMappings;

	private Map<String, RegimenFiscal> regimenFiscalPagoMappings;

	@Autowired
	private UsoCfdiRepository usoCfdiRepository;

	@Autowired
	private FormaPagoRepository formaPagoRepository;

	@Autowired
	private RegimanFiscalRepository regimanFiscalRepository;

	private static final Logger log = LoggerFactory.getLogger(CatalogCacheService.class);

	@PostConstruct
	public void init() {
		log.info("Loading mappings");
		usoCfdiMappings = createUsoCfdiMap();
		log.info("Mappings usoCfdiMappings loaded {}", usoCfdiMappings.size());
		formaPagoMappings = createFormaPagoMappings();
		log.info("Mappings formaPagoMappings loaded {}", formaPagoMappings.size());
		regimenFiscalPagoMappings = createRegimenFiscalPagoMappings();
		log.info("Mappings regimenFiscalPagoMappings loaded {}", regimenFiscalPagoMappings.size());
	}

	public Map<String, UsoCfdi> createUsoCfdiMap() {
		Map<String, UsoCfdi> mapping = new HashMap<>();
		for (UsoCfdi uso : usoCfdiRepository.findAll()) {
			mapping.put(uso.getClave(), uso);
		}
		return mapping;
	}

	public Map<String, FormaPago> createFormaPagoMappings() {
		Map<String, FormaPago> mapping = new HashMap<>();
		for (FormaPago uso : formaPagoRepository.findAll()) {
			mapping.put(uso.getId(), uso);
		}
		return mapping;
	}

	public Map<String, RegimenFiscal> createRegimenFiscalPagoMappings() {
		Map<String, RegimenFiscal> mapping = new HashMap<>();
		for (RegimenFiscal uso : regimanFiscalRepository.findAll()) {
			mapping.put(uso.getClave().toString(), uso);
		}
		return mapping;
	}

	public Map<String, UsoCfdi> getUsoCfdiMappings() {
		return usoCfdiMappings;
	}

	public Map<String, FormaPago> getFormaPagoMappings() {
		return formaPagoMappings;
	}

	public Map<String, RegimenFiscal> getRegimenFiscalPagoMappings() {
		return regimenFiscalPagoMappings;
	}

}
