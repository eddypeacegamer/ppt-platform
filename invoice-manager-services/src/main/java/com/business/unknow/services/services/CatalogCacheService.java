package com.business.unknow.services.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.services.entities.catalogs.ClaveUnidad;
import com.business.unknow.services.entities.catalogs.FormaPago;
import com.business.unknow.services.entities.catalogs.RegimenFiscal;
import com.business.unknow.services.entities.catalogs.UsoCfdi;
import com.business.unknow.services.repositories.catalogs.ClaveUnidadRepository;
import com.business.unknow.services.repositories.catalogs.FormaPagoRepository;
import com.business.unknow.services.repositories.catalogs.RegimanFiscalRepository;
import com.business.unknow.services.repositories.catalogs.UsoCfdiRepository;

@Service
public class CatalogCacheService {

	private Map<String, UsoCfdi> usoCfdiMappings;
	
	private Map<String, ClaveUnidad> claveUnidadMappings;

	private Map<String, FormaPago> formaPagoMappings;

	private Map<String, RegimenFiscal> regimenFiscalPagoMappings;

	@Autowired
	private UsoCfdiRepository usoCfdiRepository;

	@Autowired
	private FormaPagoRepository formaPagoRepository;

	@Autowired
	private RegimanFiscalRepository regimanFiscalRepository;
	
	@Autowired
	private ClaveUnidadRepository claveUnidadReppository;

	private static final Logger log = LoggerFactory.getLogger(CatalogCacheService.class);

	@PostConstruct
	public void init() {
		log.info("Loading mappings");
		loadUsoCfdiMappings();
		log.info("Mappings usoCfdiMappings loaded {}", usoCfdiMappings.size());
		loadFormaPagoMappings();
		log.info("Mappings formaPagoMappings loaded {}", formaPagoMappings.size());
		loadRegimenFiscalPagoMappings();
		log.info("Mappings regimenFiscalPagoMappings loaded {}", regimenFiscalPagoMappings.size());
		loadClaveUnidadMappings();
		log.info("Mappings claveUnidad loaded {}", claveUnidadMappings.size());
	}
	
	
	private void loadClaveUnidadMappings() {
		claveUnidadMappings = new HashMap<>();
		for (ClaveUnidad clave : claveUnidadReppository.findAll()) {
			claveUnidadMappings.put(clave.getClave(), clave);
		}
	}

	private void loadUsoCfdiMappings() {
		usoCfdiMappings = new HashMap<>();
		for (UsoCfdi uso : usoCfdiRepository.findAll()) {
			usoCfdiMappings.put(uso.getClave(), uso);
		}
	}

	private void loadFormaPagoMappings() {
		formaPagoMappings = new HashMap<>();
		for (FormaPago uso : formaPagoRepository.findAll()) {
			formaPagoMappings.put(uso.getId(), uso);
		}
	}

	private void loadRegimenFiscalPagoMappings() {
		regimenFiscalPagoMappings = new HashMap<>();
		for (RegimenFiscal uso : regimanFiscalRepository.findAll()) {
			regimenFiscalPagoMappings.put(uso.getClave().toString(), uso);
		}
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
	
	public Map<String, ClaveUnidad> getClaveUniMappings() {
		return claveUnidadMappings;
	}
	
	public Optional<String> getUsoCfdi(String clave){
		if(usoCfdiMappings.containsKey(clave)) {
			return Optional.of(usoCfdiMappings.get(clave).getDescripcion());
		}else {
			return Optional.empty();
		}
	}
	
	public Optional<String> getClaveUnidad(String clave){
		return claveUnidadMappings.containsKey(clave)? Optional.of(claveUnidadMappings.get(clave).getDescripcion()):Optional.empty();
	}
	
	public Optional<String> getFormaPago(String clave){
		return formaPagoMappings.containsKey(clave)? Optional.of(formaPagoMappings.get(clave).getDescripcion()):Optional.empty();
	}
	
	public Optional<String> getRegimenFiscal(String clave){
		return regimenFiscalPagoMappings.containsKey(clave)? Optional.of(regimenFiscalPagoMappings.get(clave).getDescripcion()):Optional.empty();
	}

}
