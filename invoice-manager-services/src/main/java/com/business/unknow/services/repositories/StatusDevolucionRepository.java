package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.catalogs.StatusDevolucion;

public interface StatusDevolucionRepository extends JpaRepository<StatusDevolucion, Integer> {
	List<StatusDevolucion> findAll();
}
