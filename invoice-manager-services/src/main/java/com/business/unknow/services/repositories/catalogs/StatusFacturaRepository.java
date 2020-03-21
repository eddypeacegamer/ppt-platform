package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.StatusFactura;

//@Repository
@Deprecated
public interface StatusFacturaRepository{

	public List<StatusFactura> findAll();

}
