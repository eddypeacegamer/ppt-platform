package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.catalogs.Giro;

public interface GiroRepository extends CrudRepository<Giro, Integer> {

	public List<Giro> findAll();
}
