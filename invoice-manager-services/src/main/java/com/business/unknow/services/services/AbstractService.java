package com.business.unknow.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.business.unknow.services.entities.files.ResourceFile;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.files.FacturaFileRepository;
import com.business.unknow.services.repositories.files.ResourceFileRepository;

public class AbstractService {
	
	@Autowired
	protected FacturaRepository repository;

	@Autowired
	protected FacturaFileRepository facturaFileRepository;

	@Autowired
	protected ResourceFileRepository resourceFileRepository;

	@Autowired
	protected FacturaMapper mapper;
	
	protected void createResourceFile(String data, String referncia, String tipoRecurso, String tipoArchivo) {
		if (data != null) {
			ResourceFile resource = new ResourceFile();
			resource.setData(data.getBytes());
			resource.setReferencia(referncia);
			resource.setTipoRecurso(tipoRecurso);
			resource.setTipoArchivo(tipoArchivo);
			resourceFileRepository.save(resource);
		}
	}
	
	protected void deleteAllResourceFileByFolio(String folio) {
		if (folio != null) {
			List<ResourceFile>resources=resourceFileRepository.findByReferencia(folio);
			resources.stream().forEach(a->resourceFileRepository.delete(a));
		}
	}
}
