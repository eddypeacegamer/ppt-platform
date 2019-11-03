package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.StatusPago;


@Repository
public interface StatusPagoRepository extends JpaRepository<StatusPago, Integer> {
	List<StatusPago> findAll();
}
