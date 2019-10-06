package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.catalogs.StatusPago;


public interface StatusPagoRepository extends JpaRepository<StatusPago, Integer> {
	List<StatusPago> findAll();
}
