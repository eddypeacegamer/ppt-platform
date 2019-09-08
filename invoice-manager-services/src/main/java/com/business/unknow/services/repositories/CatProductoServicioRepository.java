package com.business.unknow.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.business.unknow.services.entities.CatProductoServicio;

public interface CatProductoServicioRepository extends CrudRepository<CatProductoServicio, Integer> {

	@Query("SELECT t FROM CatProductoServicio t WHERE t.parent is null  order by t.id asc")
	public List<CatProductoServicio> findRoots();
	
	@Query("SELECT t FROM CatProductoServicio t WHERE t.parent.id=:id   order by t.id asc")
	public List<CatProductoServicio> findChildByRootId(@Param("id") Integer id);
}
