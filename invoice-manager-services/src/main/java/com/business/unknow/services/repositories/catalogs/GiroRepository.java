package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.Giro;

@Repository
public interface GiroRepository extends CrudRepository<Giro, Integer> {

	public List<Giro> findAll();
}
