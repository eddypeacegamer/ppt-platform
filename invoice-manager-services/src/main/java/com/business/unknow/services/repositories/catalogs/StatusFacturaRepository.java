package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.StatusFactura;

@Repository
public interface StatusFacturaRepository extends JpaRepository<StatusFactura, String> {

	public List<StatusFactura> findAll();

}
