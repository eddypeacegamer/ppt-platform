package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.ClaveProductoServicio;

@Repository
public interface ClaveProductoServicioRepository extends JpaRepository<ClaveProductoServicio, Integer> {
	
	public Page<ClaveProductoServicio> findAll(Pageable pageable);
	
	public List<ClaveProductoServicio> findByDescripcionContainingIgnoreCase(String description);

}
