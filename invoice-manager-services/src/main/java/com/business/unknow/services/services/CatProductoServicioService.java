package com.business.unknow.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.CatProductoServicioDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.CatProductoServicio;
import com.business.unknow.services.mapper.CatProductoServicioMapper;
import com.business.unknow.services.repositories.CatProductoServicioRepository;

@Service
public class CatProductoServicioService {

	@Autowired
	private CatProductoServicioRepository repository;

	@Autowired
	private CatProductoServicioMapper mapper;

	public List<CatProductoServicioDto> getRoots() throws InvoiceManagerException {
		List<CatProductoServicio> entities = repository.findRoots();
		if (entities.isEmpty()) {
			throw new InvoiceManagerException("Product service catalog roots not found", "not found",
					HttpStatus.NOT_FOUND.value());
		} else {
			return mapper.getCatProductoServicioDtosFromEntities(entities);
		}
	}

	public List<CatProductoServicioDto> getChildsByRootId(Integer id) throws InvoiceManagerException {
		List<CatProductoServicio> entities = repository.findChildByRootId(id);
		if (entities.isEmpty()) {
			throw new InvoiceManagerException("Product service catalog not found",
					String.format("not found for the parent id %d", id), HttpStatus.NOT_FOUND.value());
		} else {
			return mapper.getCatProductoServicioDtosFromEntities(entities);
		}
	}
}
