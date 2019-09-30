package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.ClaveUnidad;

@Repository
public interface ClaveUnidadRepository extends JpaRepository<ClaveUnidad, String> {

	public Page<ClaveUnidad> findAll(Pageable pageable);
	
	public List<ClaveUnidad> findByNombreContainingIgnoreCase(String nombre);
}
