package com.business.unknow.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.business.unknow.services.entities.Promotor;

public interface PromotorRepository extends CrudRepository<Promotor, Integer> {

	public List<Promotor> findAll();
	public Optional<Promotor> findByName(String name);

}
